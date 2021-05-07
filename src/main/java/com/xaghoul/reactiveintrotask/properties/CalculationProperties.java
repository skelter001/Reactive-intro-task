package com.xaghoul.reactiveintrotask.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties("reactiveintrotask")
public class CalculationProperties {

    private Duration delayMillis;
    private Duration timeoutMillis;

    public CalculationProperties(int delayMillis, int timeoutMillis) {
        this.delayMillis = Duration.ofMillis(delayMillis);
        this.timeoutMillis = Duration.ofMillis(timeoutMillis);
    }
}
