package com.github.gypsyjr777.security.errs;

import org.springframework.security.core.AuthenticationException;

public class NoUserFoundException extends AuthenticationException {
    public NoUserFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoUserFoundException(String msg) {
        super(msg);
    }
}
