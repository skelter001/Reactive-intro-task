package com.xaghoul.reactiveintrotask.model.calculator;

import com.xaghoul.reactiveintrotask.model.calculation.Calculation;
import lombok.AllArgsConstructor;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.time.Duration;
import java.time.Instant;

@AllArgsConstructor
public class GraalJsCalculator implements Calculator {

    private final String functionCode;

    // TODO: 5/7/2021 pass id here or in service
    @Override
    public Calculation calculate(int idx) {
        try(Context context = Context.create()) {
            Value functionResult = context.eval("js", functionCode);
            Instant start = Instant.now();
            Object result = functionResult.execute();
            Instant end = Instant.now();
            return new Calculation(idx, result, Duration.between(end, start));
        }
    }
}
