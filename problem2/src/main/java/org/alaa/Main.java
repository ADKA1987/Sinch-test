package org.alaa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.System.exit;
import static java.lang.System.in;

public class Main
{
    static Stack stack = new Stack();
    static BigDecimal num1;
    static BigDecimal num2;
   private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("Please enter the Polish notation expression");
        String expression = scanner.nextLine();
        BigDecimal value=null;
        try
        {
            value= run(expression);

        }catch (RuntimeException e){
            System.out.println("error");
            exit(0);
        }

        System.out.println(value);
    }

    public static BigDecimal run(String expression)
    {
        String[] input = expression.split("\\s+");


        BigDecimal result = null;
        for (int i = input.length - 1; i >= 0; i--)
        {

            boolean isNumeric= checkIfNumeric(input[i]);

            if (isNumeric)
            {
                if(stack.size()==2 && i ==input.length-1) {
                    throw new RuntimeException("error");
                }
                stack.push(BigDecimal.valueOf(Double.parseDouble(String.valueOf(input[i]))));
            }

            if(stack.size()==1 && !isNumeric)  {
                throw new RuntimeException("error");
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
            result=BigDecimal.valueOf(Double.parseDouble(String.valueOf(stack.pop()))).setScale(2,RoundingMode.HALF_DOWN);
        return result;
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

