package com.xaghoul.reactiveintrotask.properties;

import lombok.Data;

import java.time.Duration;

@Data
public class ConfigurationProperties {

    private Duration delayMillis;
    private Duration timeoutMillis;

    public ConfigurationProperties(int delayMillis, int timeoutMillis) {
        this.delayMillis = Duration.ofMillis(delayMillis);
        this.timeoutMillis = Duration.ofMillis(timeoutMillis);
    }
}
