package org.alaa.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.alaa.controller.domain.Request;
import org.alaa.controller.domain.Response;
import org.alaa.domain.Error;
import org.alaa.domain.ErrorCode;
import org.alaa.domain.ErrorResponse;
import org.alaa.domain.Result;
import org.alaa.service.Service;
import org.alaa.service.ValidationService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Tag(name = "Polish Notation Controller",description = "This controller will contain all operation about Polish Notation")
@RestController
@Slf4j
public class Controller
{
    @Autowired
    private ValidationService validationService;

    @Autowired
    private Service service;
    private static final String REQUEST_USER="RequestUser";
    private static final String REQUEST_SYSTEM="RequestSystem";
    private static final String GET_POLISH_NOTATION="/polish-notion/v1/get-result";



@Operation(description = "This Api will take the list of numbers and operation then it will evaluate it based on Polish Notation.",
        responses ={
        @ApiResponse(responseCode = "200", description = "evaluates arithmetic expressions written in Polish notation",
                content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Response.class))),
        @ApiResponse(responseCode = "400", description = "Return list of errors.",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ErrorResponse.class),
                                examples = {
                                        @ExampleObject(
                                                name = "Invalid Request",
                                                value = "{\"code\": \"4000\", \"message\" : \"RequestingSystem cannot be empty\"}"
                                        ),
                                        @ExampleObject(
                                                name = "Business Invalid Request",
                                                value = "{\"code\": \"4001\", \"message\" : \"error\"}"
                                        )
                        })),
        @ApiResponse(responseCode = "500", description = "Generic internal server error",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ErrorResponse.class),
                examples = {
                        @ExampleObject(
                                name = "Invalid Request",
                                value = "{\"code\": \"5001\", \"message\" : \"Error message\"}"
                        )}))})

    @PostMapping(value = GET_POLISH_NOTATION,consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> getPolishNotation(@RequestBody Request request,
                                                @RequestHeader(value = REQUEST_USER) String requestUser,
                                                @RequestHeader(value = REQUEST_SYSTEM) String requestSystem){

    try{
        String transactionId= UUID.randomUUID().toString();
        logRequest(transactionId,requestUser,requestSystem,request);

            return validationService
                    .validateRequest(requestUser,requestSystem,request)
                    .fold(invalid-> new ResponseEntity<>(new ErrorResponse(invalid.asJava()),HttpStatus.BAD_REQUEST),
                            valid->service
                                    .getPolishNotion(transactionId,valid._1,valid._2,valid._3)
                                    .block()
                                    .fold(this::invalidResponse,this::okResponse));
        }catch (Exception e){
        return new ResponseEntity<>(new ErrorResponse(List.of(new Error(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), e.getMessage()))),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }
    private void logRequest(final String transactionId, final String requestUser, final  String requestSystem,final Request request)
    {
        String requestLog="TransactionId: "+transactionId+" ,RequestUser: "+requestUser+" ,RequestSystem: "+ requestSystem+" ,Request: "+request;
        log.info("request: "+requestLog);
    }
    private ResponseEntity<ErrorResponse> invalidResponse(final ErrorResponse errorResponse)
    {
        log.error("Error was found:\n"+ errorResponse );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }
    private ResponseEntity<Response> okResponse(Result result){
        Response response =new Response(String.valueOf(result.getValue()));
        log.info("Success response: "+response);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
