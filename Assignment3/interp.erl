-module(interp).
-export([scanAndParse/1,runFile/1,runStr/1]).
-include("types.hrl").

loop(InFile,Acc) ->
    case io:request(InFile,{get_until,prompt,lexer,token,[1]}) of
        {ok,Token,_EndLine} ->
            loop(InFile,Acc ++ [Token]);
        {error,token} ->
            exit(scanning_error);
        {eof,_} ->
            Acc
    end.

scanAndParse(FileName) ->
    {ok, InFile} = file:open(FileName, [read]),
    Acc = loop(InFile,[]),
    file:close(InFile),
    {Result, AST} = parser:parse(Acc),
    case Result of 
	ok -> AST;
	_ -> io:format("Parse error~n")
    end.


-spec runFile(string()) -> valType().
runFile(FileName) ->
    valueOf(scanAndParse(FileName),env:new()).

scanAndParseString(String) ->
    {_ResultL, TKs, _L} = lexer:string(String),
    parser:parse(TKs).

-spec runStr(string()) -> valType().
runStr(String) ->
    {Result, AST} = scanAndParseString(String),
    case Result  of 
    	ok -> valueOf(AST,env:new());
    	_ -> io:format("Parse error~n")
    end.


-spec numVal2Num(numValType()) -> integer().
numVal2Num({num, N}) ->
    N.

-spec boolVal2Bool(boolValType()) -> boolean().
boolVal2Bool({bool, B}) ->
    B.

-spec valueOf(expType(),envType()) -> valType().
valueOf({numExp, Alpha}, Env) ->
    {_, _, Beta} = Alpha,
    {num, Beta};
valueOf({idExp, Alpha}, Env) ->
    {_, _, Beta} = Alpha,
    env:lookup(Env, Beta);
valueOf({plusExp, Alpha, Beta}, Env) ->
    {num, numVal2Num(valueOf(Alpha, Env)) + numVal2Num(valueOf(Beta, Env))};
valueOf({diffExp, Alpha, Beta}, Env) ->
    {num, numVal2Num(valueOf(Alpha, Env)) + numVal2Num(valueOf(Beta, Env))};
valueOf({isZeroExp, Alpha}, Env) ->
    case valueOf(Alpha, Env) =:= 0 of
        true -> {bool, true};
        false -> {bool, false}
    end;
valueOf({procExp, {_, _, Alpha}, Beta}, Env) ->
    {proc, Alpha, Beta, Env};
valueOf({letExp, Alpha, Beta, Gamma}, Env) ->
    {_, _, Delta} = Alpha,
    valueOf(Gamma, env:add(Env, Delta, valueOf(Beta, Env)));
valueOf({ifThenElseExp, Alpha, Beta, _}, Env) ->
    case boolVal2Bool(valueOf(Alpha, Env)) of 
        true -> valueOf(Beta, Env);
        false -> valueOf(Alpha, Env)
    end.
