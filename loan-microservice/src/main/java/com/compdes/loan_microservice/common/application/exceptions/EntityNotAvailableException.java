package com.compdes.loan_microservice.common.application.exceptions;

public class EntityNotAvailableException extends RuntimeException {
    public EntityNotAvailableException(String message) {
        super(message);
    }
}
