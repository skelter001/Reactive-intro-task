package com.xaghoul.reactiveintrotask.configuration;

import com.xaghoul.reactiveintrotask.service.CalculatorFactory;
import com.xaghoul.reactiveintrotask.service.impl.GraalJsCalculatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorFactoryProvider {
    @Bean
    public CalculatorFactory calculatorFactory() {
        return new GraalJsCalculatorFactory();
    }
}
