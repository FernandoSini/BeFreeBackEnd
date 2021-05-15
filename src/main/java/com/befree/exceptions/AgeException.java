package com.befree.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AgeException extends RuntimeException {
    private static final long serialVersionUid = 1L;

    public AgeException(String exception) {
        super(exception);
    }
}
