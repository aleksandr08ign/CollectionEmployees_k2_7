package ru.skypro.CollectionEmployees.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFounException extends RuntimeException {
    public EmployeeNotFounException() {
    }

    public EmployeeNotFounException(String message) {
        super(message);
    }

    public EmployeeNotFounException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFounException(Throwable cause) {
        super(cause);
    }

    public EmployeeNotFounException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
