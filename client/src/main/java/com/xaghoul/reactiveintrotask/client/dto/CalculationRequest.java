package com.xaghoul.reactiveintrotask.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculationRequest {
    private String firstFunction;
    private String secondFunction;
    private int iter;
}
