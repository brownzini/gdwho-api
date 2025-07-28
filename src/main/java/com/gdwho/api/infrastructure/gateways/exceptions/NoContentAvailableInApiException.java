package com.gdwho.api.infrastructure.gateways.exceptions;

public class NoContentAvailableInApiException extends RuntimeException {
    public NoContentAvailableInApiException(String message) {
        super(message);
    }
}