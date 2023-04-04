package com.botapeer.exception;

import org.springframework.security.access.AccessDeniedException;

public class DifferentUserException extends AccessDeniedException {
    public DifferentUserException(String message) {
        super(message);
    }
}