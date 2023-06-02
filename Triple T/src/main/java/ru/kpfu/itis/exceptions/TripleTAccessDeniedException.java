package ru.kpfu.itis.exceptions;

import org.springframework.http.HttpStatus;

public class TripleTAccessDeniedException extends TripleTServiceException{

    public TripleTAccessDeniedException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
