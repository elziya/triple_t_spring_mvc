package ru.kpfu.itis.exceptions;

import org.springframework.http.HttpStatus;

public class TripleTFileException extends TripleTServiceException{

    public TripleTFileException(String message) {
        super(HttpStatus.METHOD_NOT_ALLOWED, message);
    }

    public TripleTFileException(Throwable cause, HttpStatus httpStatus) {
        super (cause, HttpStatus.METHOD_NOT_ALLOWED);
    }

}
