package com.xaghoul.reactiveintrotask.exception;

public class FunctionCalculationException extends RuntimeException {

    public FunctionCalculationException() {
        super("An error occurred during calculating function");
    }

    public FunctionCalculationException(Throwable ex) {
        super("An error occurred during calculating function", ex);
    }

    public FunctionCalculationException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
