package com.xaghoul.reactiveintrotask.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties("reactiveintrotask")
@NoArgsConstructor
public class CalculationProperties {

    private int delayMillis;
    private int timeoutMillis;

    public CalculationProperties(int delayMillis, int timeoutMillis) {
        this.delayMillis = delayMillis;
        this.timeoutMillis = timeoutMillis;
    }

    public Duration getDelayAsDuration() {
        return Duration.ofMillis(delayMillis);
    }

    public Duration getTimeoutAsDuration() {
        return Duration.ofMillis(timeoutMillis);
    }
}
