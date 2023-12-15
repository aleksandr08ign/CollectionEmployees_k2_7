package ru.skypro.CollectionEmployees.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmployeeAlreadeAddeException extends RuntimeException{
    public EmployeeAlreadeAddeException() {
    }

    public EmployeeAlreadeAddeException(String message) {
        super(message);
    }

    public EmployeeAlreadeAddeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeAlreadeAddeException(Throwable cause) {
        super(cause);
    }

    public EmployeeAlreadeAddeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
