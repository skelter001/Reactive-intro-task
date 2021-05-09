package com.xaghoul.reactiveintrotask.web;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class CalculationWebClient {

    private final WebClient client = WebClient.create("http://localhost:8080");

    private Mono<ClientResponse> orderedResult = client.get()
            .uri("/calculate/ordered")
            .accept(MediaType.TEXT_PLAIN)
            .exchangeToMono(rs -> Mono.just(rs.mutate().build()));

    private Mono<ClientResponse> unorderedResult = client.get()
            .uri("/calculate/unordered")
            .accept(MediaType.TEXT_PLAIN)
            .exchangeToMono(rs -> Mono.just(rs.mutate().build()));

    public void getOrderedResult() {
        orderedResult.subscribe(System.out::println);
    }

    public void getUnorderedResult() {
        unorderedResult.subscribe(System.out::println);
    }
}
