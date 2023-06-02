package ru.kpfu.itis.exceptions;

import org.springframework.http.HttpStatus;

public class TripleTRestDuplicateEntityException extends TripleTRestServiceException{

    public TripleTRestDuplicateEntityException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public TripleTRestDuplicateEntityException(Throwable cause, HttpStatus httpStatus) {
        super (cause, HttpStatus.BAD_REQUEST);
    }
}