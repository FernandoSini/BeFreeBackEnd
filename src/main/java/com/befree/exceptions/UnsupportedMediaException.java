package com.befree.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class UnsupportedMediaException extends RuntimeException{
    private static final long serialVersionUid = 1L;

    public UnsupportedMediaException(String exception) {
        super(exception);
    }
}
