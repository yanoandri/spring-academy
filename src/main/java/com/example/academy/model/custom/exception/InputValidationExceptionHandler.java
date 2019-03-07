package com.example.academy.model.custom.exception;

public class InputValidationExceptionHandler extends RuntimeException {
    public InputValidationExceptionHandler(String exception){
        super(exception);
    }
}
