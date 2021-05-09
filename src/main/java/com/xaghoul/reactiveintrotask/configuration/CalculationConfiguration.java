package com.xaghoul.reactiveintrotask.configuration;

import com.xaghoul.reactiveintrotask.properties.CalculationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CalculationProperties.class)
public class CalculationConfiguration {

    private final CalculationProperties properties;

    public CalculationConfiguration(CalculationProperties properties) {
        this.properties = properties;
    }
}
