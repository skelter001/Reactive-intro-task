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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
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
                        .body(BodyInserters.fromValue(
                                calculationService.calculateOrdered(
                                        calculation.getFirstFunction(),
                                        calculation.getSecondFunction(),
                                        calculation.getIter())
                                        .map(OrderedCalculation::getDataAsString)
                                        .onErrorResume(TimeoutException.class, te -> Mono.error(
                                                new ScriptTimeoutException("Timeout exception occurred during script execution", te)))
                                        .onErrorResume(ExecutionException.class, ee -> Mono.error(
                                                new ScriptExecutionException("Execution exception: invalid script code", ee)))
                                        .onErrorResume(InterruptedException.class, ie -> Mono.error(
                                                new CalculationException("Interrupt exception occurred during script execution", ie)))
                                        .onErrorReturn(Predicate.not(e -> e instanceof CalculationException),
                                                "An error occurred trying to calculate script")))
        );
    }

    public Mono<ServerResponse> unorderedCalculation(ServerRequest request) {

        Mono<CalculationRequest> calculationMono = request.bodyToMono(CalculationRequest.class);

        return calculationMono.flatMap(calculation ->
                ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(BodyInserters.fromValue(
                                calculationService.calculateUnordered(
                                        calculation.getFirstFunction(),
                                        calculation.getSecondFunction(),
                                        calculation.getIter())
                                        .map(UnorderedCalculation::getDataAsString)
                        )));
    }
}
