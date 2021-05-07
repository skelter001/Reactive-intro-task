package com.xaghoul.reactiveintrotask.service;

import com.xaghoul.reactiveintrotask.model.calculator.Calculator;

public interface CalculatorFactory {
    Calculator createCalculator(String functionCode);
}
