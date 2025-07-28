package com.gdwho.api.infrastructure.gateways.exceptions;

public class NoContentAvailableDBException extends RuntimeException {
    public NoContentAvailableDBException(String message, Throwable cause) {
        super(message);
    }
}