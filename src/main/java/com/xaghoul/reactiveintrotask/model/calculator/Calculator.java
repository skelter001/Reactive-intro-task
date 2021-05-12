package com.xaghoul.reactiveintrotask.model.calculator;

import com.xaghoul.reactiveintrotask.model.calculation.Calculation;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface Calculator {
    Calculation calculate(int callNumber) throws ExecutionException, InterruptedException, TimeoutException;
}
