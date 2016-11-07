-module(env).
-compile(export_all).
-include("types.hrl").


-spec new()-> envType().
new() ->
    % complete
    dict:new().

-spec add(envType(),atom(),valType())-> envType().
add(Env,Key,Value) ->
    % complete
    dict:append(Key, Value, Env).

-spec lookup(envType(),atom())-> valType().
lookup(Env,Key) -> 
    Res = dict:find(Key, Env),
    case Res of
        {ok, Value} -> Value;
        error -> io:format("Value not found~n")
    end.
    
