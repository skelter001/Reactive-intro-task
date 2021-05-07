package com.xaghoul.reactiveintrotask.configuration;

import com.xaghoul.reactiveintrotask.properties.CalculationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(CalculationProperties.class)
@Configuration
public class CalculationConfiguration {

}
