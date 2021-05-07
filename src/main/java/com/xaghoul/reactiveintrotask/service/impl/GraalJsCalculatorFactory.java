package com.xaghoul.reactiveintrotask.service.impl;

import com.xaghoul.reactiveintrotask.model.calculator.Calculator;
import com.xaghoul.reactiveintrotask.model.calculator.GraalJsCalculator;
import com.xaghoul.reactiveintrotask.service.CalculatorFactory;

public class GraalJsCalculatorFactory implements CalculatorFactory {

    @Override
    public Calculator createCalculator(String functionCode) {
        return new GraalJsCalculator(functionCode);
    }
}
