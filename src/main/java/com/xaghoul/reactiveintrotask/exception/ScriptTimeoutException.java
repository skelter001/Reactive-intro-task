package com.xaghoul.reactiveintrotask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ScriptTimeoutException extends ScriptCalculationException {

    public ScriptTimeoutException(String msg) {
        super(msg);
    }

    public ScriptTimeoutException(Throwable cause) {
        super(cause);
    }

    public ScriptTimeoutException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
