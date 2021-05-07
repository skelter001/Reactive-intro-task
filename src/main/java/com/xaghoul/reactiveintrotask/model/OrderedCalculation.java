package com.xaghoul.reactiveintrotask.model;

import lombok.Value;

import java.util.List;

@Value
public class OrderedCalculation implements CsvResult {

    int calculationNumber;
    Calculation firstCalculation;
    int firstFunctionAheadResults;
    Calculation secondCalculation;
    int secondFunctionAheadResults;

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
