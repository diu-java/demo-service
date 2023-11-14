package com.diu.edu.demoservice.exception;

public class ServiceBusinessException extends RuntimeException{
    public ServiceBusinessException(String message) {
        super(message);
    }
}
