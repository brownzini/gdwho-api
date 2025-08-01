package com.gdwho.api.infrastructure.gateways.exceptions;

public class OperationFailedException extends RuntimeException {
    public OperationFailedException(String message) {
        super(message);
    }
}