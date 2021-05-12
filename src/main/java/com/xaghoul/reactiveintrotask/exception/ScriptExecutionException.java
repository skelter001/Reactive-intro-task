package com.xaghoul.reactiveintrotask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ScriptExecutionException extends ScriptCalculationException {

    public ScriptExecutionException(String msg) {
        super(msg);
    }

    public ScriptExecutionException(Throwable cause) {
        super(cause);
    }

    public ScriptExecutionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
