package com.gdwho.api.infrastructure.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.gdwho.api.infrastructure.gateways.exceptions.DataNotFoundException;
import com.gdwho.api.infrastructure.gateways.exceptions.EntrieNotFoundException;
import com.gdwho.api.infrastructure.gateways.exceptions.FieldNotFoundException;
import com.gdwho.api.infrastructure.gateways.exceptions.NoContentAvailableDBException;
import com.gdwho.api.infrastructure.gateways.exceptions.NoValidFieldException;
import com.gdwho.api.infrastructure.gateways.exceptions.NotPossibleTrainModelException;
import com.gdwho.api.infrastructure.gateways.exceptions.OperationFailedException;
import com.gdwho.api.infrastructure.gateways.exceptions.UserAlreadyExistsException;
import com.gdwho.api.infrastructure.gateways.exceptions.UserNotFoundException;
import com.gdwho.api.infrastructure.gateways.exceptions.UserPersistenceException;
import com.gdwho.api.infrastructure.handler.dto.ErrorResponse;
import com.gdwho.api.infrastructure.security.exceptions.InvalidCredentialsException;
import com.gdwho.api.infrastructure.security.exceptions.UnauthorizedException;

import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFound(UsernameNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJwtSignature(SignatureException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> InvalidCredentialsException(InvalidCredentialsException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> userAlreadyExistsException(UserAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity<ErrorResponse> operationFailedException(OperationFailedException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> dataNotFoundException(DataNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EntrieNotFoundException.class)
    public ResponseEntity<ErrorResponse> entrieNotFoundException(EntrieNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(FieldNotFoundException.class)
    public ResponseEntity<ErrorResponse> fieldNotFoundException(FieldNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NoValidFieldException.class)
    public ResponseEntity<ErrorResponse> noValidFieldException(NoValidFieldException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NotPossibleTrainModelException.class)
    public ResponseEntity<ErrorResponse> notPossibleTrainModelException(NotPossibleTrainModelException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserPersistenceException.class)
    public ResponseEntity<ErrorResponse> handleUserPersistence(UserPersistenceException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NoContentAvailableDBException.class)
    public ResponseEntity<ErrorResponse> noContentAvailableDBException(NoContentAvailableDBException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleEnumConversionException(MethodArgumentTypeMismatchException ex) {
        ErrorResponse message = new ErrorResponse("[Invalid Type]: This type is not valid");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String firstFieldError = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Invalid Requisition");

        ErrorResponse error = new ErrorResponse(firstFieldError);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        ErrorResponse error = new ErrorResponse("[Internal Error]: An unexpected error occurred on the server");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}