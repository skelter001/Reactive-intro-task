package com.xaghoul.reactiveintrotask.model.calculator;

import com.xaghoul.reactiveintrotask.model.calculation.Calculation;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
public class GraalJsCalculator implements Calculator {

    private final String functionCode;
    private Context context;
    private final int timeout;

    public GraalJsCalculator(String functionCode, int timeout) {
        this.functionCode = functionCode;
        this.timeout = timeout;
        context = Context.newBuilder("js").build();
    }

    @Override
    public Calculation calculate(int callNumber) {
        Value functionResult = context.eval("js", createSourceCode(functionCode));
        AtomicReference<Instant> start = new AtomicReference<>();
        AtomicReference<Instant> end = new AtomicReference<>();
        Value result;
        try {
            result = CompletableFuture.supplyAsync(() -> {
                context.enter();
                start.set(Instant.now());
                Value r = functionResult.execute(callNumber);
                end.set( Instant.now());
                context.leave();
                return r;
            }).get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            context = Context.newBuilder("js").build();
            throw new RuntimeException(e);
        }

        double value = valueAsDigit(result);
        return new Calculation(callNumber, value, Duration.between(start.get(), end.get()));
    }

    private double valueAsDigit(Value result) {
        if (result.fitsInInt())
            return result.asInt();
        if (result.fitsInFloat())
            return result.asFloat();
        if (result.fitsInDouble())
            return result.asDouble();
        else
            throw new IllegalArgumentException("Result cannot be cast to double");
    }

    private String createSourceCode(String functionCode) {
        return "(function test(callNumber) {" +
                functionCode +
                "})";
    }
}
