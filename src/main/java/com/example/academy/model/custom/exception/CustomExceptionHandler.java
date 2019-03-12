package com.example.academy.model.custom.exception;

import com.example.academy.model.custom.response.MetaResponse;
import com.example.academy.model.custom.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
       Response model = new Response();
       model.setStatus(Response.MESSAGE_ERROR);
       model.setMeta(new MetaResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ""));
        return new ResponseEntity(model, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataValidationExceptionHandler.class)
    public  ResponseEntity handleDataValidation(DataValidationExceptionHandler ex){
        Response model = new Response();
        model.setStatus(Response.MESSAGE_ERROR);
        model.setMeta(new MetaResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ""));
        return new ResponseEntity(model, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InputValidationExceptionHandler.class)
    public  ResponseEntity handleInputValidation(InputValidationExceptionHandler ex){
        Response model = new Response();
        model.setStatus(Response.MESSAGE_ERROR);
        model.setMeta(new MetaResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ""));
        return new ResponseEntity(model, HttpStatus.BAD_REQUEST);
    }
}
