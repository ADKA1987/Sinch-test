import org.alaa.Main;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class MainTest
{
    String expression;
    BigDecimal value;
    @Test
    public void given_correct_input_1_when_run_then_correct_value_1(){
        given_correct_input_1();
        when_run();

        then_correct_value_1();
    }
    @Test
    public void given_correct_input_2_when_run_then_correct_value_2(){
        given_correct_input_2();
        when_run();

        then_correct_value_2();
    }


    @Test
    public void given_correct_input_3_when_run_then_correct_value_3(){
        given_correct_input_3();
        when_run();

        then_correct_value_3();
    }
    @Test
    public void given_correct_input_4_when_run_then_correct_value_4(){
        given_correct_input_4();
        when_run();

        then_correct_value_4();
    }
    @Test
    public void given_correct_input_5_when_run_then_return_error(){
        given_correct_input_5();
        try
        {
            when_run();
        }catch (RuntimeException e){
            assertEquals("error",e.getMessage());
        }
    }

    @Test
    public void given_correct_input_6_when_run_then_return_error(){
        given_correct_input_6();
        try
        {
            when_run();
        }catch (RuntimeException e){
            assertEquals("error",e.getMessage());
        }

    }


    private void when_run()
    {
        value= Main.run(expression);
    }

    private void given_correct_input_1()
    {
        expression= "+ + 0.5 1.5 * 4 10";
    }
    private void given_correct_input_2()
    {
        expression= "- 2e3 - 700 + 7 * 2 15";
    }
    private void given_correct_input_3()
    {
        expression= "- -1.5 * 3.1415 / -7 -2";
    }
    private void given_correct_input_4()
    {
        expression= "100500";
    }
    private void given_correct_input_5()
    {
        expression= "1 2";
    }
    private void given_correct_input_6()
    {
        expression= "+ 1";
    }

    private void then_correct_value_1()
    {
        assertEquals(BigDecimal.valueOf(42.00).setScale(2, RoundingMode.HALF_DOWN),value);
    }

    private void then_correct_value_2()
    {
        assertEquals(BigDecimal.valueOf(1337.00).setScale(2, RoundingMode.HALF_DOWN),value);
    }
    private void then_correct_value_3()
    {
        assertEquals(BigDecimal.valueOf(-12.50).setScale(2, RoundingMode.HALF_DOWN),value);
    }
    private void then_correct_value_4()
    {
        assertEquals(BigDecimal.valueOf(100500.00).setScale(2, RoundingMode.HALF_DOWN),value);
    }
}
