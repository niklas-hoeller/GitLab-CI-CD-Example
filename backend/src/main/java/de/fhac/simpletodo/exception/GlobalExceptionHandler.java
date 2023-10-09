package de.fhac.simpletodo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RescourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleRescourceNotFoundException(RescourceNotFoundException e) {
        ErrorDetails errorDetails = new ErrorDetails("Resource not found", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationErrorException.class)
    public ResponseEntity<ErrorDetails> handleValidationErrorException(ValidationErrorException e) {
        ErrorDetails errorDetails = new ErrorDetails("Validation error", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
