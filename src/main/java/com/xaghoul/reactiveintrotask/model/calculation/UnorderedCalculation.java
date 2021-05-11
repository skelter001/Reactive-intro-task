package com.xaghoul.reactiveintrotask.model.calculation;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Duration;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class UnorderedCalculation extends Calculation implements CsvResult {

    private final int functionNumber;

    public UnorderedCalculation(int functionNumber, int calculationNumber, Object result, Duration executionTime) {
        super(calculationNumber, result, executionTime);
        this.functionNumber = functionNumber;
    }

    public List<Object> getData() {
        return List.of(super.getCalculationNumber(), functionNumber, super.getResult(), super.getExecutionTime());
    }
}
