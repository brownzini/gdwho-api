package com.gdwho.api.infrastructure.gateways.exceptions;

public class NoValidFieldException extends RuntimeException {
    public NoValidFieldException(String message) {
        super(message);
    }
}