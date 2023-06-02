package ru.kpfu.itis.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TripleTServiceException extends RuntimeException{

    private final HttpStatus httpStatus;

    public TripleTServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public TripleTServiceException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }
}
