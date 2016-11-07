package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Created by eric on 9/5/16.
 */
public class AssignmentOne {


    private List<Integer> tempResults;
    private List<Integer> results;

    public static void main(String[] args) {
        String keyboardInput = new String();
        Scanner keyboardReader = new Scanner(System.in);
        System.out.println("Enter an ascending and even amount of integers");
        keyboardInput = keyboardReader.nextLine();
        String tokens[] = keyboardInput.split("\\s+");
        List<Integer[]> numArray = new ArrayList<Integer[]>();
        int tok0;
        int tok1;
        for(int i = 0; i < tokens.length-1; i+=2){
            tok0 = Integer.parseInt(tokens[i]);
            tok1 = Integer.parseInt(tokens[i+1]);
            Integer[] tuple = {tok0, tok1};
            numArray.add(tuple);
        }
        AssignmentOne assignment = new AssignmentOne();
        assignment.lprimes(numArray);

    }

    public List<Integer> lprimes(List<Integer[]> intervals){
        int intervalSize = intervals.size();
        List<Integer> results = new ArrayList<Integer>();
        PrimeFinder[] pFinders = new PrimeFinder[intervalSize];
        Thread[] pThreads = new Thread[intervalSize];
        for(int i = 0; i < intervalSize; i++){
            pFinders[i] = new PrimeFinder(intervals.get(i)[0], intervals.get(i)[1]);
        }

        for(int i = 0; i < intervalSize; i++){
            pThreads[i] = new Thread(pFinders[i]);
            pThreads[i].start();
        }

        for(int i = 0; i < intervalSize; i++){
            try{
                pThreads[i].join();
                tempResults = pFinders[i].getPrimesList();
                results.addAll(tempResults);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }
        System.out.println(results);
        return results;
    }

}

