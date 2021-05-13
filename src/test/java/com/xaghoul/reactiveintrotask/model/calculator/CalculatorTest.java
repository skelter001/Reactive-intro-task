package com.xaghoul.reactiveintrotask.model.calculator;

import com.xaghoul.reactiveintrotask.exception.CalculationException;
import com.xaghoul.reactiveintrotask.service.CalculatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CalculatorTest {

    private final CalculatorFactory calculatorFactory;

    CalculatorTest(@Autowired CalculatorFactory calculatorFactory) {
        this.calculatorFactory = calculatorFactory;
    }

    @Test
    public void createCalculation_WhenValidCode_ReturnCalculation() {
        assertDoesNotThrow(() -> calculatorFactory.createCalculator(
                "for (var i = 0; i < 100; i++) {\n" +
                        "idx += 1;\n" +
                        "}\n" +
                        "return idx;",
                10000
        ));
    }


    @Test
    public void calculate_WhenNoErrors_ReturnValue() {
        Object result = calculatorFactory
                .createCalculator("return callNumber * 2;", 10000)
                .calculate(1)
                .getResult();

        assertTrue(result instanceof Integer || result instanceof Double);
        if (result instanceof Integer)
            assertEquals(2, result);
        else
            assertEquals(2.0, result);
    }

    @Test
    public void calculate_WhenExecutionError_ThrowException() {
        assertThrows(CalculationException.class,
                () -> calculatorFactory
                        .createCalculator("throw new Error('some error')", 10000)
                        .calculate(0)
        );
    }

}