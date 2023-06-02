package ru.kpfu.itis.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TripleTRestServiceException extends RuntimeException{

    private final HttpStatus httpStatus;

    public TripleTRestServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public TripleTRestServiceException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }
}
