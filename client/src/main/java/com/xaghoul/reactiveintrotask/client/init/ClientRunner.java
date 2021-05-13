package com.xaghoul.reactiveintrotask.client.init;

import com.xaghoul.reactiveintrotask.client.configuration.ResultFluxFactory;
import com.xaghoul.reactiveintrotask.client.dto.CalculationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientRunner implements CommandLineRunner {

    private final ResultFluxFactory fluxFactory;

    @Override
    public void run(String... args) {
        CalculationRequest req = new CalculationRequest(
                "while (true) {}",
                "return callNumber * 2",
                10);

        fluxFactory.getResultFlux(req)
                .doOnNext(log::info).blockLast();
    }
}
