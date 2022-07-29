package com.hodolog.hodolog.api.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/*
 * 최상위 custom한 exception
 */
@Getter
public abstract class HodologException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public HodologException(String message) {
        super(message);
    }

    public HodologException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
