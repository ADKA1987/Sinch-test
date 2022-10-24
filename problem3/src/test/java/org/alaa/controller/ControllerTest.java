package org.alaa.controller;

import org.alaa.controller.domain.Request;
import org.alaa.controller.domain.Response;
import org.alaa.domain.ErrorResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.alaa.domain.ErrorCode.BUSINESS_VALIDATION_ERROR_CODE;
import static org.alaa.domain.ErrorCode.VALIDATION_ERROR_CODE;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest
{

    @Autowired
    private Controller controller;
    private Request request;
    private Response response;
    private String requestUser="Junit";
    private String requestSystem="test";
    private ResponseEntity responseEntity;
    private ErrorResponse errorResponse;
    @Before
    public void init(){
     requestUser="Junit";
    requestSystem="test";

    }

   @Test
    public void given_request_1_when_call_then_correct_result(){
        given_request_1();
        when_call();
        then_correct_result_1();
   }

    @Test
    public void given_request_2_when_call_then_correct_result(){
        given_request_2();
        when_call();
        then_correct_result_2();
    }
    @Test
    public void given_request_3_when_call_then_correct_result(){
        given_request_3();
        when_call();
        then_correct_result_3();
    }
    @Test
    public void given_request_4_when_call_then_correct_result(){
        given_request_4();
        when_call();
        then_correct_result_4();
    }
    @Test
    public void given_request_5_when_call_then_error(){
        given_request_5();
        when_call();
        then_error();
    }
    @Test
    public void given_request_6_when_call_then_error(){
        given_request_6();
        when_call();
        then_error();
    }
    @Test
    public void given_request_7_when_call_then_error(){
        given_request_7();
        when_call();
        then_error_7();
    }
    @Test
    public void given_request_8_when_call_then_validation_error(){
        given_request_8();
        when_call();
        then_validation_error();
    }
    @Test
    public void given_null_request_System_when_call_then_validation_error(){
        given_null_request_System();
        when_call();
        then_request_system_validation_error();
    }

    @Test
    public void given_null_request_user_when_call_then_validation_error(){
        given_null_request_User();
        when_call();
        then_request_user_validation_error();
    }
    private void when_call()
    {
        responseEntity=controller.getPolishNotation(request,requestUser,requestSystem);
    }

    private void given_request_1()
    {
        request = new Request("+ + 0.5 1.5 * 4 10");
    }
    private void given_request_2()
    {
        request = new Request("- 2e3 - 700 + 7 * 2 15");
    }
    private void given_request_3()
    {
        request = new Request("- -1.5 * 3.1415 / -7 -2");
    }
    private void given_request_4()
    {
        request = new Request("100500");
    }
    private void given_request_5()
    {
        request = new Request("1 2");
    }
    private void given_request_6()
    {
        request = new Request("+ 1");
    }
    private void given_request_7()
    {
        request = new Request("string");
    }

    private void given_request_8()
    {
        request = new Request("");
    }
    private void given_null_request_System()
    {
        requestSystem=null;
        request = new Request("- -1.5 * 3.1415 / -7 -2");
    }
    private void given_null_request_User()
    {
        requestUser=null;
        request = new Request("- -1.5 * 3.1415 / -7 -2");
    }
    private void then_correct_result_1()
    {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        response = (Response)responseEntity.getBody();
        assertEquals("42.00",response.getResult());

    }
    private void then_correct_result_2()
    {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        response = (Response)responseEntity.getBody();
        assertEquals("1337.00",response.getResult());

    }
    private void then_correct_result_3()
    {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        response = (Response)responseEntity.getBody();
        assertEquals("-12.50",response.getResult());

    }
    private void then_correct_result_4()
    {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        response = (Response)responseEntity.getBody();
        assertEquals("100500.00",response.getResult());

    }
    private void then_error()
    {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        errorResponse = (ErrorResponse)responseEntity.getBody();
        assertEquals(BUSINESS_VALIDATION_ERROR_CODE.getValue(),errorResponse.getErrors().get(0).getCode());
        assertEquals("error",errorResponse.getErrors().get(0).getMessage());

    }
    private void then_error_7()
    {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        errorResponse = (ErrorResponse)responseEntity.getBody();
        assertEquals(BUSINESS_VALIDATION_ERROR_CODE.getValue(),errorResponse.getErrors().get(0).getCode());
        assertEquals("The input string should contain numbers and operations.",errorResponse.getErrors().get(0).getMessage());

    }
    private void then_validation_error()
    {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        errorResponse = (ErrorResponse)responseEntity.getBody();
        assertEquals(VALIDATION_ERROR_CODE.getValue(),errorResponse.getErrors().get(0).getCode());
        assertEquals("Polish Notation expression cannot be empty",errorResponse.getErrors().get(0).getMessage());

    }
    private void then_request_system_validation_error()
    {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        errorResponse = (ErrorResponse)responseEntity.getBody();
        assertEquals(VALIDATION_ERROR_CODE.getValue(),errorResponse.getErrors().get(0).getCode());
        assertEquals("RequestingSystem cannot be empty",errorResponse.getErrors().get(0).getMessage());

    }
    private void then_request_user_validation_error()
    {
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        errorResponse = (ErrorResponse)responseEntity.getBody();
        assertEquals(VALIDATION_ERROR_CODE.getValue(),errorResponse.getErrors().get(0).getCode());
        assertEquals("RequestingUser cannot be empty",errorResponse.getErrors().get(0).getMessage());

    }

}
