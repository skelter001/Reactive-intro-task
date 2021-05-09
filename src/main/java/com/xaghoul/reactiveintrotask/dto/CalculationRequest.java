package com.xaghoul.reactiveintrotask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CalculationRequest {
    private final String firstFunction;
    private final String secondFunction;
    private final int iters;
}
