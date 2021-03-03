package com.befree.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthenticationException extends AuthenticationException {
    private static final long serialVersionUid = 1L;

    public InvalidJwtAuthenticationException(String exception) {
        super(exception);
    }
}
