package org.alaa.controller;

import org.alaa.controller.domain.Request;
import org.alaa.service.Service;
import org.alaa.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class Controller
{
    @Autowired
    private ValidationService validationService;

    @Autowired
    private Service service;
    private static final String REQUEST_USER="RequestUser";
    private static final String REQUEST_SYSTEM="RequestSystem";
    private final static Logger logger= LoggerFactory.getLogger(Controller.class);
    private static final String GET_POLISH_NOTATION="/polish-notion/v1/get-result";

    @PostMapping(value = GET_POLISH_NOTATION,consumes = "application/json",produces = "application/json")
    public ResponseEntity getPolishNotation(@RequestBody Request request,
            @RequestHeader(value = REQUEST_USER) String requestUser,
            @RequestHeader(value = REQUEST_SYSTEM) String requestSystem){

        String transactionId= UUID.randomUUID().toString();
        try{
            logRequest(transactionId,requestUser,requestSystem,request);
            return validationService.validateRequest(requestUser,requestSystem,request)
                    .fold(invalid->new ResponseEntity(invalid,HttpStatus.BAD_REQUEST),
                            valid->service.getPolishNotion(transactionId,valid._1,valid._2,valid._3).fold(
                                    invalid-> new ResponseEntity(invalid,HttpStatus.BAD_REQUEST),
                                    serviceValid-> new ResponseEntity(serviceValid,HttpStatus.OK)
                            ));

        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void logRequest(final String transactionId, final String requestUser, final  String requestSystem,final Request request)
    {
        String requestLog="TransactionId: "+transactionId+" ,RequestUser: "+requestUser+" ,RequestSystem: "+ requestSystem+" ,Request: "+request;
        logger.info(requestLog);
    }
}
