package com.xaghoul.reactiveintrotask.model;

import lombok.AllArgsConstructor;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.time.Duration;
import java.time.Instant;

@AllArgsConstructor
public class GraalJsCalculation {

    private final String functionCode;

    // TODO: 5/7/2021 pass id here or in service
    public Calculation calculate(int idx) {
        try(Context context = Context.create()) {
            Value functionResult = context.eval("js", functionCode);
            Instant start = Instant.now();
            Object result = functionResult.execute();
            Instant end = Instant.now();
            return new Calculation(result, Duration.between(end, start));
        }
    }
}
