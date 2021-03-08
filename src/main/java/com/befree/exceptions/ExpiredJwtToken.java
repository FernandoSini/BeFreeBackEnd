package com.befree.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ExpiredJwtToken extends AuthenticationException {
    private static final long serialVersionUid = 1L;

    public ExpiredJwtToken(String exception) {
        super(exception);
    }
}
