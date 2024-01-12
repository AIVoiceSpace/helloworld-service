package com.aivoicespace.helloworld.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldMetrics {

    @Bean
    public Counter helloWorldCounter(MeterRegistry meterRegistry) {
        return Counter.builder("helloworld.counter")
                .description("The number of hello worlds returned")
                .register(meterRegistry);
    }

}
