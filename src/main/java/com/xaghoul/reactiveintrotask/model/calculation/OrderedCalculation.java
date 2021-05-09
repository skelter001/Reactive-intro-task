package com.xaghoul.reactiveintrotask.model.calculation;

import lombok.Value;

import java.util.List;

@Value
public class OrderedCalculation implements CsvResult {

    int calculationNumber;
    Calculation firstCalculation;
    int firstFunctionAheadResults;
    Calculation secondCalculation;
    int secondFunctionAheadResults;

    public OrderedCalculation(Calculation firstCalculation, int firstFunctionAheadResults,
                              Calculation secondCalculation, int secondFunctionAheadResults) {
        if(firstCalculation.getCalculationNumber() != secondCalculation.getCalculationNumber())
            throw new RuntimeException();
        calculationNumber = firstCalculation.getCalculationNumber();
        this.firstCalculation = firstCalculation;
        this.firstFunctionAheadResults = firstFunctionAheadResults;
        this.secondCalculation = secondCalculation;
        this.secondFunctionAheadResults = secondFunctionAheadResults;
    }

    @Override
    public List<Object> getData() {
        return List.of(
                calculationNumber,
                firstCalculation.getResult(),
                firstCalculation.getExecutionTime(),
                firstFunctionAheadResults,
                secondCalculation.getResult(),
                secondCalculation.getExecutionTime(),
                secondFunctionAheadResults
        );
    }
}
