package com.xaghoul.reactiveintrotask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ScriptCalculationException extends CalculationException {

    public ScriptCalculationException(String msg) {
        super(msg);
    }

    public ScriptCalculationException(Throwable cause) {
        super("An error occurred during calculating function", cause);
    }

    public ScriptCalculationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
