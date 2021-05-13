package com.xaghoul.reactiveintrotask.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CalculationRouter {

    @Bean
    public RouterFunction<ServerResponse> route(CalculationHandler calculationHandler) {
        return RouterFunctions
                .route()
                .POST("/calculations/ordered",
                        RequestPredicates.accept(MediaType.TEXT_EVENT_STREAM),
                        calculationHandler::orderedCalculation)
                .POST("/calculations/unordered",
                        RequestPredicates.accept(MediaType.TEXT_EVENT_STREAM),
                        calculationHandler::unorderedCalculation)
                .GET("/calculations/unordered",calculationHandler::unorderedCalculation)
                .GET("calculation/ordered", calculationHandler::orderedCalculation)
                .build();
    }
}
