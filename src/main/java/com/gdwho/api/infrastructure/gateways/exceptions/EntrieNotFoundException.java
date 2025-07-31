package com.gdwho.api.infrastructure.gateways.exceptions;

public class EntrieNotFoundException extends RuntimeException {
    public EntrieNotFoundException(String message) {
        super(message);
    }
}