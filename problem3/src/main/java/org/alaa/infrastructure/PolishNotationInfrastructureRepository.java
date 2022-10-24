package org.alaa.infrastructure;

import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.alaa.domain.Error;
import org.alaa.domain.ErrorResponse;
import org.alaa.domain.PolishNotationDomain;
import org.alaa.domain.RequestSystem;
import org.alaa.domain.RequestUser;
import org.alaa.domain.Result;
import org.alaa.infrastructure.domain.IPolishNotationInfrastructureRepository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Stack;

import static org.alaa.domain.ErrorCode.BUSINESS_VALIDATION_ERROR_CODE;
@Slf4j
public class PolishNotationInfrastructureRepository implements IPolishNotationInfrastructureRepository
{

    @Override
    public Mono<Either<ErrorResponse, Result>> getPolishNotation(final String transactionId,final RequestUser requestUser,final RequestSystem requestSystem,
            final PolishNotationDomain polishNotationDomain)
    {
        log.info("Request to infrastructure with parameters:\nTransactionId:"+ transactionId+",\nRequestUser: "+ requestUser.getValue()+",\nRequestSystem: "+requestSystem.getValue()+"\nPolishNotation string: "+polishNotationDomain.getValue());
        return run(polishNotationDomain.getValue());
    }
    public   Mono<Either<ErrorResponse,Result>> run(final String expression)
    {
        String[] input = expression.split("\\s+");
        final Stack stack = new Stack();

        BigDecimal result = null;
        for (int i = input.length - 1; i >= 0; i--)
        {

            boolean isNumeric= checkIfNumeric(input[i]);

            // Check if the input is not numeric and not one of the operations
            if (!isNumeric &&!input[i].equals("+")&&!input[i].equals("-")&&!input[i].equals("*")&&!input[i].equals("/"))

            {
                Error error=  Error.builder()
                        .code(BUSINESS_VALIDATION_ERROR_CODE.getValue())
                        .message("The input string should contain numbers and operations.").build();
                log.error("Error from infrastructure, result:"+error);
                return Mono.just(Either.left(new ErrorResponse(List.of(error))));
            }


            // Check if the input is numeric
            if (isNumeric)
            {
                stack.push(BigDecimal.valueOf(Double.parseDouble(String.valueOf(input[i]))));

                // check if the input is only numbers without operation
                if((stack.size()==2 && i ==0)) {
                    Error error=  Error.builder()
                            .code(BUSINESS_VALIDATION_ERROR_CODE.getValue())
                            .message("error").build();
                    log.error("Error from infrastructure, result:"+error);
                    return Mono.just(Either.left(new ErrorResponse(List.of(error))));
                }
            }
            // check if the input is only one number
            if(stack.size()==1 && !isNumeric)  {
                Error error=  Error.builder()
                        .code(BUSINESS_VALIDATION_ERROR_CODE.getValue())
                        .message("error").build();
                log.error("Error from infrastructure, result:"+error);
                return Mono.just(Either.left(new ErrorResponse(List.of(error))));
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
        // Return the result
        while(!stack.isEmpty())
            result=BigDecimal.valueOf(Double.parseDouble(String.valueOf(stack.pop()))).setScale(2, RoundingMode.HALF_DOWN);
        log.info("Response from infrastructure, result:"+result);
        return Mono.just(Either.right(new Result(result)));
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
