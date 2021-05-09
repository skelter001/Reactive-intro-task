package com.xaghoul.reactiveintrotask.web;

import com.xaghoul.reactiveintrotask.dto.CalculationRequest;
import com.xaghoul.reactiveintrotask.model.calculation.OrderedCalculation;
import com.xaghoul.reactiveintrotask.model.calculation.UnorderedCalculation;
import com.xaghoul.reactiveintrotask.service.CalculationService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CalculationHandler {

    private final CalculationService calculationService;

    public CalculationHandler(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    public Mono<ServerResponse> orderedCalculation(@RequestBody ServerRequest request) {

        Mono<CalculationRequest> calculationMono = request.bodyToMono(CalculationRequest.class);

        return calculationMono.flatMap(calculation ->
                ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromValue(
                                calculationService.calculateOrdered(
                                        calculation.getFirstFunction(),
                                        calculation.getSecondFunction(),
                                        calculation.getIters())
                                        .map(OrderedCalculation::getDataAsString)
                        )));
    }

    public Mono<ServerResponse> unorderedCalculation(@RequestBody ServerRequest request) {

        Mono<CalculationRequest> calculationMono = request.bodyToMono(CalculationRequest.class);

        return calculationMono.flatMap(calculation ->
                ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromValue(
                                calculationService.calculateUnordered(
                                        calculation.getFirstFunction(),
                                        calculation.getSecondFunction(),
                                        calculation.getIters())
                                        .map(UnorderedCalculation::getDataAsString)
                        )));
    }
}
