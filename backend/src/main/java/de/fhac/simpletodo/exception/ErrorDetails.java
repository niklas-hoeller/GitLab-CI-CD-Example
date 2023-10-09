package de.fhac.simpletodo.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String message, String details) {

    public ErrorDetails(String message, String details) {
        this(LocalDateTime.now(), message, details);
    }
}