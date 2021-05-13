package com.xaghoul.reactiveintrotask.model.calculator;

import com.xaghoul.reactiveintrotask.model.calculation.Calculation;

public interface Calculator {
    Calculation calculate(int callNumber);
}
