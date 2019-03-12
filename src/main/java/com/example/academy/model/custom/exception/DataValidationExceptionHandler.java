package com.example.academy.model.custom.exception;

public class DataValidationExceptionHandler extends RuntimeException {
    public DataValidationExceptionHandler(String exception){
        super(exception);
    }
}
