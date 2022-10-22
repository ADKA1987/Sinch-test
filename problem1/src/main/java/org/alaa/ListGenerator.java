package org.alaa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListGenerator
{
    static List<Integer> getTheListOfNumbersToCompare(final int k)
    {
        boolean isOk = false;
        List<Integer> a = new ArrayList<Integer>();
        while (!isOk) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Please enter the array size : ");

            if(scan.hasNextInt())
            {
                int arraySize = scan.nextInt();
                if(arraySize<2 || arraySize>10e6) {
                    System.out.println("Please entered the Integer value between 2 and 10e6. ");
                    scan.nextLine();
                }else {
                    isOk= true;
                    a= getArrayNumbers(arraySize,k);
                }
            }
            else {
                System.out.println("Please entered the Integer value. ");
                scan.nextLine();
            }
            scan.reset();
        }
        return a;
    }


    private static List<Integer> getArrayNumbers(final int arraySize,final int k)
    {
        Scanner scan = new Scanner(System.in);
        List<Integer> a = new ArrayList<Integer>();
        boolean isOk = false;
        while (!isOk) {

            System.out.print("Please enter numbers : ");
            if(scan.hasNextInt())
            {
                int num = scan.nextInt();
                if (num<k) {
                    a.add(num) ;
                    if (a.size()==arraySize) isOk=true;
                }else {
                    System.out.println("Please entered integer value smaller than the compared number: "+k);
                    scan.nextLine();
                }
            }
            else {
                System.out.println("Please entered the Integer value. ");
                scan.nextLine();
            }
            scan.reset();
        }
        return a;
    }
}
