package ru.kpfu.itis.exceptions;

import org.springframework.http.HttpStatus;

public class TripleTNotFoundException extends TripleTServiceException{

    public TripleTNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public TripleTNotFoundException(Throwable cause, HttpStatus httpStatus) {
        super (cause, HttpStatus.NOT_FOUND);
    }
}
