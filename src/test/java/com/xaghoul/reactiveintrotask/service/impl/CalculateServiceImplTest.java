package com.xaghoul.reactiveintrotask.service.impl;

import com.xaghoul.reactiveintrotask.model.calculation.OrderedCalculation;
import com.xaghoul.reactiveintrotask.model.calculation.UnorderedCalculation;
import com.xaghoul.reactiveintrotask.service.CalculationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

@SpringBootTest(properties = {"reactiveintrotask.delay-millis=100", "reactiveintrotask.timeout-millis=3000"})
public class CalculateServiceImplTest {

    private final CalculationService seriesService;

    public CalculateServiceImplTest(@Autowired CalculationService seriesService) {
        this.seriesService = seriesService;
    }

    private boolean isResultEqualToInt(int expected, Object result) {
        if (result instanceof Integer)
            return result.equals(expected);
        else
            return Double.valueOf(expected).equals(result);
    }

    @Test
    public void calculateUnordered_WhenValidFunctions_ReturnValidResults() {
        Flux<UnorderedCalculation> result =
                seriesService.calculateUnordered(
                        "return callNumber + 1;",
                        "return callNumber * 2;",
                        3
                );

        Predicate<UnorderedCalculation> resultPredicate = calcResult -> {
            int expected = calcResult.getFunctionNumber() == 1 ?
                    calcResult.getCalculationNumber() + 1 : calcResult.getCalculationNumber() * 2;

            return isResultEqualToInt(expected, calcResult.getResult());
        };

        StepVerifier.create(result.onErrorContinue((throwable, o) -> throwable.printStackTrace()).log())
                .expectNextMatches(resultPredicate)
                .expectNextMatches(resultPredicate)
                .expectNextMatches(resultPredicate)
                .expectNextMatches(resultPredicate)
                .expectNextMatches(resultPredicate)
                .expectNextMatches(resultPredicate)
                .expectComplete()
                .verify();
    }

    @Test
    public void calculateOrdered_WhenValidFunctions_ReturnValidResults() {
        Flux<OrderedCalculation> result =
                seriesService.calculateOrdered(
                        "return callNumber + 1;",
                        "return callNumber * 2;",
                        3
                );

        Predicate<OrderedCalculation> resultPredicate = calcResult ->
                isResultEqualToInt(
                        calcResult.getCalculationNumber() + 1,
                        calcResult.getFirstCalculation().getResult()
                ) && isResultEqualToInt(
                        calcResult.getCalculationNumber() * 2,
                        calcResult.getSecondCalculation().getResult()
                );

        StepVerifier.create(result)
                .expectNextMatches(resultPredicate)
                .expectNextMatches(resultPredicate)
                .expectNextMatches(resultPredicate)
                .expectComplete()
                .verify();
    }

}

