package com.xaghoul.reactiveintrotask.client;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
public class FluxFactoryConfig {

    private final CalculationWebClient client;

    @Bean
    @Profile("ordered")
    public ResultFluxFactory orderedResultFluxFactory() {
        return client::getOrderedResult;
    }

    @Bean
    @Profile("unordered")
    public ResultFluxFactory unorderedResultFluxFactory() {
        return client::getUnorderedResult;
    }
}
