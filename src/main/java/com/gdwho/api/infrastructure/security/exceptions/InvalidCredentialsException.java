package com.gdwho.api.infrastructure.security.exceptions;

public class InvalidCredentialsException extends RuntimeException  {
    public InvalidCredentialsException() {
        super("Username or password is wrong or doesn't exist");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
