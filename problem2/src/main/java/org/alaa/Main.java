package org.alaa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.System.exit;

public class Main
{
    static Stack stack = new Stack();
    static BigDecimal num1;
    static BigDecimal num2;
    static String expression;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println("Please enter the Polish notation expression");
        expression = scanner.nextLine();
        String[] input = expression.split("\\s+");
        for (int i = input.length - 1; i >= 0; i--)
        {

             boolean isNumeric= checkIfNumeric(input[i]);

            if (isNumeric)
            {
                stack.push(BigDecimal.valueOf(Double.parseDouble(String.valueOf(input[i]))));
            }
            if((stack.size()==2 && isNumeric)||
                    (stack.size()==1 && !isNumeric) ) {
                System.out.println("error");
                exit(0);
            }
            if (input[i].equals("+"))
            {
                num1 = (BigDecimal)stack.pop();
                num2 = (BigDecimal)stack.pop();
                stack.push(num1.add(num2));
            }
            if (input[i].equals("-")){
                num1 = (BigDecimal)stack.pop();
                num2 = (BigDecimal)stack.pop();
                stack.push(num1.subtract(num2));
            }
            if (input[i].equals("*")){
                num1 = (BigDecimal)stack.pop();
                num2 =  (BigDecimal)stack.pop();
                stack.push(num1.multiply( num2));
            }
            if (input[i].equals("/")){
                num1 = (BigDecimal)stack.pop();
                num2 =  (BigDecimal)stack.pop();
                stack.push(num1.divide(num2));
            }


        }
        while(!stack.isEmpty())
                        System.out.println(BigDecimal.valueOf(Double.parseDouble(String.valueOf(stack.pop()))).setScale(2,RoundingMode.HALF_DOWN));
    }

    private static boolean checkIfNumeric(String s)
    {
        if (s == null)
        {
            return false;
        }
        try
        {
            Float.parseFloat(s);
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}

