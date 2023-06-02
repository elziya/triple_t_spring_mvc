package ru.kpfu.itis.exceptions;

import org.springframework.http.HttpStatus;

public class TripleTRestNotFoundException extends TripleTRestServiceException{

    public TripleTRestNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public TripleTRestNotFoundException(Throwable cause, HttpStatus httpStatus) {
        super (cause, HttpStatus.NOT_FOUND);
    }
}
