package com.compdes.user_microservice.common.infrastructure.inputadapters.rest.handlers;

import com.compdes.user_microservice.common.application.exceptions.EntityAlreadyExistsException;
import com.compdes.user_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.user_microservice.common.infrastructure.inputadapters.rest.dto.RestApiError;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestApiError> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestApiError(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<RestApiError> handleEntityAlreadyExistException(EntityAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RestApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RestApiError> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new RestApiError(HttpStatus.CONFLICT.value(), e.getMessage()));
    }


}
