package ru.kpfu.itis.exceptions;

import org.springframework.http.HttpStatus;

public class TripleTVkOAuthException extends TripleTServiceException{

    public TripleTVkOAuthException() {
        super(HttpStatus.METHOD_NOT_ALLOWED, "Не удалось войти через VK");
    }

    public TripleTVkOAuthException(Throwable cause, HttpStatus httpStatus) {
        super (cause, httpStatus);
    }
}