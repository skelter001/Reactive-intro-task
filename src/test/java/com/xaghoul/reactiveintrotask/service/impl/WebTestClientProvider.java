package com.xaghoul.reactiveintrotask.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.reactive.server.WebTestClient;

@Configuration
public class WebTestClientProvider {
    @Bean
    public WebTestClient webTestClient() {
        return WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }
}
