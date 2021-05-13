package com.xaghoul.reactiveintrotask.web;

import com.xaghoul.reactiveintrotask.dto.CalculationRequest;
import com.xaghoul.reactiveintrotask.exception.CalculationException;
import com.xaghoul.reactiveintrotask.exception.ScriptExecutionException;
import com.xaghoul.reactiveintrotask.exception.ScriptTimeoutException;
import com.xaghoul.reactiveintrotask.model.calculation.OrderedCalculation;
import com.xaghoul.reactiveintrotask.model.calculation.UnorderedCalculation;
import com.xaghoul.reactiveintrotask.service.CalculationService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Component
public class CalculationHandler {

    private final CalculationService calculationService;

    public CalculationHandler(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    public Mono<ServerResponse> orderedCalculation(ServerRequest request) {

        Mono<CalculationRequest> calculationMono = request.bodyToMono(CalculationRequest.class);

        return calculationMono.flatMap(calculation ->
                ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(calculationService.calculateOrdered(
                                calculation.getFirstFunction(),
                                calculation.getSecondFunction(),
                                calculation.getIter())
                                        .map(OrderedCalculation::getDataAsString)
                                        .onErrorReturn(InterruptedException.class,
                                                "Interrupt exception occurred during script execution")
                                        .onErrorReturn(ScriptTimeoutException.class,
                                                "Timeout exception occurred during script execution")
                                        .onErrorReturn(ScriptExecutionException.class,
                                                "Execution exception occurred during script execution")
                                        .onErrorReturn(Predicate.not(e -> e instanceof CalculationException),
                                                "An error occurred during calculation script"),
                                String.class)
        );
    }

    public Mono<ServerResponse> unorderedCalculation(ServerRequest request) {

        Mono<CalculationRequest> calculationMono = request.bodyToMono(CalculationRequest.class);

        return calculationMono.flatMap(calculation ->
                ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(calculationService.calculateUnordered(
                                calculation.getFirstFunction(),
                                calculation.getSecondFunction(),
                                calculation.getIter())
                                        .map(UnorderedCalculation::getDataAsString)
                                        .onErrorReturn(InterruptedException.class,
                                                "Interrupt exception occurred during script execution")
                                        .onErrorReturn(ScriptTimeoutException.class,
                                                "Timeout exception occurred during script execution")
                                        .onErrorReturn(ScriptExecutionException.class,
                                                "Execution exception occurred during script execution")
                                        .onErrorReturn(Predicate.not(e -> e instanceof CalculationException),
                                                "An error occurred during calculation script"),
                                String.class));
    }
}
