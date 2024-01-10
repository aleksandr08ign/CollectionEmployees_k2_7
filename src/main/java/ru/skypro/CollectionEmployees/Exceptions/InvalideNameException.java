package ru.skypro.CollectionEmployees.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalideNameException extends RuntimeException{
    public InvalideNameException() {
    }

    public InvalideNameException(String message) {
        super(message);
    }

    public InvalideNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalideNameException(Throwable cause) {
        super(cause);
    }

    public InvalideNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
