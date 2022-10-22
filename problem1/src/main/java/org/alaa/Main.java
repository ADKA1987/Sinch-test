package org.alaa;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        int k= GetComparedNumber.getTheComparedNumber();
        List<Integer> a= ListGenerator.getTheListOfNumbersToCompare(k);
        int count=GetPaired.getCounter(a,k);
        System.out.println(count);
    }



}

