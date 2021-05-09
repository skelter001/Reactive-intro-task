package com.xaghoul.reactiveintrotask.service;

import com.xaghoul.reactiveintrotask.model.calculation.OrderedCalculation;
import com.xaghoul.reactiveintrotask.model.calculation.UnorderedCalculation;
import reactor.core.publisher.Flux;

public interface CalculationService {
    Flux<OrderedCalculation> calculateOrdered(String firstFunction, String secondFunction, int iter);
    Flux<UnorderedCalculation> calculateUnordered(String firstFunction, String secondFunction, int iter);
}
