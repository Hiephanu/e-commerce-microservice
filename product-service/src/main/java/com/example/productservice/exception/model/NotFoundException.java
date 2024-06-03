package com.example.productservice.exception.model;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(String message, Throwable throwable){
        super(message,throwable);
    }
}
