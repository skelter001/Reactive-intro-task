package com.xaghoul.reactiveintrotask.service.impl;

import com.xaghoul.reactiveintrotask.model.calculation.Calculation;
import com.xaghoul.reactiveintrotask.model.calculation.OrderedCalculation;
import com.xaghoul.reactiveintrotask.model.calculation.UnorderedCalculation;
import com.xaghoul.reactiveintrotask.properties.CalculationProperties;
import com.xaghoul.reactiveintrotask.service.CalculationService;
import com.xaghoul.reactiveintrotask.service.CalculatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static reactor.core.publisher.Flux.fromStream;

@Service
public class CalculateServiceImpl implements CalculationService {

    private final CalculatorFactory factory;
    private final CalculationProperties properties;

    @Autowired
    public CalculateServiceImpl(CalculatorFactory factory, CalculationProperties properties) {
        this.factory = factory;
        this.properties = properties;
    }

    private Flux<Calculation> calculate(String function, int iters) {
        return Mono.fromSupplier(() -> factory.createCalculator(function))
                .flatMapMany(calc -> fromStream(IntStream.range(1, iters).boxed())
                        .delayElements(properties.getDelayMillis())
                        .map(calc::calculate));
    }

    @Override
    public Flux<OrderedCalculation> calculateOrdered(String firstFunction, String secondFunction, int iters) {
        AtomicInteger counter = new AtomicInteger(0);

        Flux<Calculation> calculationFlux1 = calculate(firstFunction, iters)
                .doOnNext(res -> counter.incrementAndGet());

        Flux<Calculation> calculationFlux2 = calculate(secondFunction, iters)
                .doOnNext(res -> counter.decrementAndGet());

        return calculationFlux1.zipWith(calculationFlux2,
                (res1, res2) -> new OrderedCalculation(
                        //res1.getCalculationNumber(),
                        res1,
                        Math.max(counter.get(), 0),
                        res2,
                        Math.max(-counter.get(), 0)
                )
        );
    }

    @Override
    public Flux<UnorderedCalculation> calculateUnordered(String firstFunction, String secondFunction, int iters) {
        return Flux
                .merge(
                        calculate(firstFunction, iters)
                                .map(
                                        calculation -> new UnorderedCalculation(
                                                1,
                                                calculation.getCalculationNumber(),
                                                calculation.getResult(),
                                                calculation.getExecutionTime()
                                        )
                                )
                        ,
                        calculate(secondFunction, iters)
                                .map(
                                        calculation -> new UnorderedCalculation(
                                                2,
                                                calculation.getCalculationNumber(),
                                                calculation.getResult(),
                                                calculation.getExecutionTime()
                                        )
                                )
                );
    }
}
