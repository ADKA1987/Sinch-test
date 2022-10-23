package org.alaa.infrastructure;


import io.vavr.control.Validation;
import org.alaa.domain.PolishNotationDomain;
import org.alaa.domain.RequestSystem;
import org.alaa.domain.RequestUser;
import org.alaa.infrastructure.domain.IPolishNotationInfrastructureRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class PolishNotationInfrastructureRepository implements IPolishNotationInfrastructureRepository
{
    private final Stack stack = new Stack();

    @Override
    public Validation<String,BigDecimal> getPolishNotation(final String transactionId,final RequestUser requestUser,final RequestSystem requestSystem,
            final PolishNotationDomain polishNotationDomain)
    {

        return run(polishNotationDomain.getValue());
    }
    public  Validation<String,BigDecimal> run(String expression)
    {
        String[] input = expression.split("\\s+");


        BigDecimal result = null;
        for (int i = input.length - 1; i >= 0; i--)
        {

            boolean isNumeric= checkIfNumeric(input[i]);

            if (isNumeric)
            {
                if(stack.size()==2 && i ==input.length-1) {
                   return Validation.invalid("error");
                }
                stack.push(BigDecimal.valueOf(Double.parseDouble(String.valueOf(input[i]))));
            }

            if(stack.size()==1 && !isNumeric)  {
                return Validation.invalid("error");
            }

            BigDecimal num1;
            BigDecimal num2;
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
                stack.push(num1.multiply(num2));
            }
            if (input[i].equals("/")){
                num1 = (BigDecimal)stack.pop();
                num2 =  (BigDecimal)stack.pop();
                stack.push(num1.divide(num2));
            }


        }
        while(!stack.isEmpty())
            result=BigDecimal.valueOf(Double.parseDouble(String.valueOf(stack.pop()))).setScale(2, RoundingMode.HALF_DOWN);
        return Validation.valid(result);
    }

    private boolean checkIfNumeric(String s)
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
