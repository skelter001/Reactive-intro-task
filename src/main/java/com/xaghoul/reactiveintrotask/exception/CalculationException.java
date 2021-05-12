package com.xaghoul.reactiveintrotask.exception;

public class CalculationException extends RuntimeException {

    public CalculationException(String msg) {
        super(msg);
    }

    public CalculationException(Throwable cause) {
        super(cause);
    }

    public CalculationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
