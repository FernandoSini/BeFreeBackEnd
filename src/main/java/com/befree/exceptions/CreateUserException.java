package com.befree.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CreateUserException extends RuntimeException {
    private static final long serialVersionUid = 1L;

    public CreateUserException(String exception) {
        super(exception);
    }
}
