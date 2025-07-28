package com.gdwho.api.infrastructure.gateways.exceptions;

public class UserPersistenceException extends RuntimeException {
    public UserPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}