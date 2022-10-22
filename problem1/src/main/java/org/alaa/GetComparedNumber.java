package org.alaa;

import java.util.Scanner;

public class GetComparedNumber
{

    static int getTheComparedNumber(){
        Scanner scan = new Scanner(System.in);
        boolean inputOk = false;
        int k=0;
        while (!inputOk) {
            System.out.print("Please enter TheComparedNumber value: ");
            if(scan.hasNextInt())
            {
                k = scan.nextInt();
                if (k<=0) {
                    System.out.println("Please entered an integer value greater than 0. ");
                    scan.nextLine();
                }else {
                    inputOk = true;
                }
            }
            else {
                System.out.println("Please entered the Integer value. ");
                scan.nextLine();
            }
            scan.reset();
        }
        return k;
    }
}
