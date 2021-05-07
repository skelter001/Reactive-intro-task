package com.xaghoul.reactiveintrotask.service;

import com.xaghoul.reactiveintrotask.model.calculation.OrderedCalculation;
import com.xaghoul.reactiveintrotask.model.calculation.UnorderedCalculation;
import reactor.core.publisher.Flux;

public interface CalculationService {
    Flux<OrderedCalculation> calculationOrderedResult(String firstFunction, String secondFunction, int count);
    Flux<UnorderedCalculation> calculationUnorderedResult(String firstFunction, String secondFunction, int count);
}
