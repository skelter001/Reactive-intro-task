package com.xaghoul.reactiveintrotask.model.calculation;

import java.util.List;
import java.util.stream.Collectors;

public interface CsvResult {
    List<Object> getData();

    default String getDataAsString() {
        return getData().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}
