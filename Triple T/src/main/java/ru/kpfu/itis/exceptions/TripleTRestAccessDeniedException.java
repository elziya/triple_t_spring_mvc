package ru.kpfu.itis.exceptions;

import org.springframework.http.HttpStatus;

public class TripleTRestAccessDeniedException extends TripleTRestServiceException{

    public TripleTRestAccessDeniedException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

}
