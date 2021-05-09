package com.xaghoul.reactiveintrotask.model.calculation;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.Duration;
import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class UnorderedCalculation extends Calculation implements CsvResult {

    int functionNumber;
    int calculationNumber;

    public UnorderedCalculation(int functionNumber, int calculationNumber, Object result, Duration executionTime) {
        super(calculationNumber, result, executionTime);
        this.functionNumber = functionNumber;
        this.calculationNumber = calculationNumber;
    }

    public List<Object> getData() {
        return List.of(calculationNumber, functionNumber, super.getResult(), super.getExecutionTime());
    }
}
