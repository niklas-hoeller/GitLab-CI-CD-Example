package de.fhac.simpletodo.exception;

public class ValidationErrorException extends RuntimeException {

    public ValidationErrorException(String message) {
        super(message);
    }
}
