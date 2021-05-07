package com.xaghoul.reactiveintrotask.configuration;

import com.xaghoul.reactiveintrotask.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(ConfigurationProperties.class)
@Configuration
public class CalculationConfiguration {

}
