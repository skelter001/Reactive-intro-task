package com.xaghoul.reactiveintrotask.client.web;

import com.xaghoul.reactiveintrotask.client.dto.CalculationRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CalculationWebClient {

    private final WebClient client = WebClient.create("http://localhost:8080");

    public Flux<String> getOrderedResult(CalculationRequest request) {
        return client.post()
                .uri("/calculations/ordered")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .bodyValue(request)
                .exchangeToFlux(rs -> rs.bodyToFlux(String.class));
    }

    public Flux<String> getUnorderedResult(CalculationRequest request) {
        return client.post()
                .uri("/calculations/unordered")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .bodyValue(request)
                .exchangeToFlux(rs -> rs.bodyToFlux(String.class));
    }
}
