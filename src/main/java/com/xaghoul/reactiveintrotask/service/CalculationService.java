package com.xaghoul.reactiveintrotask.service;

import com.xaghoul.reactiveintrotask.model.OrderedCalculation;
import com.xaghoul.reactiveintrotask.model.UnorderedCalculation;
import reactor.core.publisher.Flux;

public interface CalculationService {
    Flux<OrderedCalculation> calculationOrderedResult(String firstFunction, String secondFunction, int count);
    Flux<UnorderedCalculation> calculationUnorderedResult(String firstFunction, String secondFunction, int count);
}
