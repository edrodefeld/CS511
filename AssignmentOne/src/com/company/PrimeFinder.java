package com.company;

/**
 * Created by eric on 9/5/16.
 */
import java.util.ArrayList;
import java.util.List;

public class PrimeFinder implements Runnable {

    private Integer start;
    private Integer end;
    private List<Integer> primes;


    PrimeFinder(Integer startNum, Integer endNum){
        start = startNum;
        end = endNum;
        primes = new ArrayList<Integer>();
    }

    public List<Integer> getPrimesList(){
        return primes;
    }

    public Boolean isPrime(int n){
        for(int i = 2; i < Math.sqrt(n) + 1; i++) {
            if(n % i==0)
                return false;
        }
        return true;
    }

    public void run(){
        for(int i = start; i <= end; i++){
            if(isPrime(i)){
                primes.add(i);
            }
        }
    }


}
