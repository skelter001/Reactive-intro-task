package com.xaghoul.reactiveintrotask.model.calculation;

import com.xaghoul.reactiveintrotask.exception.CalculationException;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderedCalculationTest {

    @Test
    public void create_WhenIndicesAreEqual_DoNotThrow() {
        assertDoesNotThrow(() -> new OrderedCalculation(
                new Calculation(1, null, Duration.ZERO),
                1,
                new Calculation(1, 0, Duration.ZERO),
                1)
        );
    }

    @Test
    public void create_WhenDifferentIndices_ThrowException() {
        assertThrows(CalculationException.class, () -> new OrderedCalculation(
                new Calculation(1, null, Duration.ZERO),
                1,
                new Calculation(2, 0, Duration.ZERO),
                1));
    }
}