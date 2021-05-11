package com.xaghoul.reactiveintrotask.model.calculation;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Calculation {
    private final int calculationNumber;
    private final Object result;
    private final Duration executionTime;

    @Override
    public String toString() {
        return "Calculation{" +
                "calculationNumber=" + calculationNumber +
                ", result=" + result +
                ", executionTime=" + executionTime.toMillis() + " millis" +
                '}';
    }
}
