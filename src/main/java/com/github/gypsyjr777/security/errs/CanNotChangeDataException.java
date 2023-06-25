package com.github.gypsyjr777.security.errs;

import org.springframework.security.core.AuthenticationException;

public class CanNotChangeDataException extends AuthenticationException {
    public CanNotChangeDataException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CanNotChangeDataException(String msg) {
        super(msg);
    }
}
