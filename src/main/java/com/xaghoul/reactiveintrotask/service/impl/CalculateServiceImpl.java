package com.xaghoul.reactiveintrotask.service.impl;

import com.xaghoul.reactiveintrotask.exception.CalculationException;
import com.xaghoul.reactiveintrotask.model.calculation.Calculation;
import com.xaghoul.reactiveintrotask.model.calculation.OrderedCalculation;
import com.xaghoul.reactiveintrotask.model.calculation.UnorderedCalculation;
import com.xaghoul.reactiveintrotask.properties.CalculationProperties;
import com.xaghoul.reactiveintrotask.service.CalculationService;
import com.xaghoul.reactiveintrotask.service.CalculatorFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CalculateServiceImpl implements CalculationService {

    private final CalculatorFactory factory;
    private final CalculationProperties properties;

    @Autowired
    public CalculateServiceImpl(CalculatorFactory factory, CalculationProperties properties) {
        this.factory = factory;
        this.properties = properties;
    }

    private Flux<Calculation> calculate(String function, int iter) {
        return Mono.fromSupplier(() -> factory.createCalculator(function, properties.getTimeoutMillis()))
                .flatMapMany(calc -> Flux.fromStream(IntStream.range(0, iter).boxed())
                        .delayElements(properties.getDelayAsDuration(), Schedulers.boundedElastic())
                        .map(calc::calculate));
    }

    @Override
    public Flux<OrderedCalculation> calculateOrdered(String firstFunction, String secondFunction, int iter) {
        AtomicInteger counter = new AtomicInteger(0);

        Flux<Calculation> calculationFlux1 = calculate(firstFunction, iter)
                .doOnNext(res -> counter.incrementAndGet())
                .onBackpressureDrop();

        Flux<Calculation> calculationFlux2 = calculate(secondFunction, iter)
                .doOnNext(res -> counter.decrementAndGet())
                .onBackpressureDrop();

        return calculationFlux1.zipWith(calculationFlux2,
                (res1, res2) -> new OrderedCalculation(
                        res1,
                        Math.max(counter.get(), 0),
                        res2,
                        Math.max(-counter.get(), 0)
                )
        );
    }

    @Override
    public Flux<UnorderedCalculation> calculateUnordered(String firstFunction, String secondFunction, int iter) {
        return Flux
                .merge(
                        calculate(firstFunction, iter)
                                .publishOn(Schedulers.parallel())
                                .map(
                                        calculation -> new UnorderedCalculation(
                                                1,
                                                calculation.getCalculationNumber(),
                                                calculation.getResult(),
                                                calculation.getExecutionTime()
                                        )
                                ).onBackpressureDrop()
                        ,
                        calculate(secondFunction, iter)
                                .publishOn(Schedulers.parallel())
                                .map(
                                        calculation -> new UnorderedCalculation(
                                                2,
                                                calculation.getCalculationNumber(),
                                                calculation.getResult(),
                                                calculation.getExecutionTime()
                                        )
                                ).onBackpressureDrop()
                );
    }
}
