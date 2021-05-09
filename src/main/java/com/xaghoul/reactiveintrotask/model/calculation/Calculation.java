package com.xaghoul.reactiveintrotask.model.calculation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.Duration;

@EqualsAndHashCode
@ToString
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Calculation {
    int calculationNumber;
    Object result;
    Duration executionTime;
}
