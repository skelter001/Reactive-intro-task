package com.xaghoul.reactiveintrotask.client;

import com.xaghoul.reactiveintrotask.client.dto.CalculationRequest;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface ResultFluxFactory {
    Flux<String> getResultFlux(CalculationRequest request);
}
