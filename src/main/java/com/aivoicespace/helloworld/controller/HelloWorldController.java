package com.aivoicespace.helloworld.controller;

import com.aivoicespace.helloworld.config.LoggingEnabled;
import com.aivoicespace.helloworld.controller.dto.HelloWorldResponse;
import io.micrometer.core.instrument.Counter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@LoggingEnabled
@Slf4j
public class HelloWorldController {
    @Autowired
    private Counter helloWorldCounter;
    @Operation(summary = "Return a Hello World response")
    @ApiResponse(responseCode = "200", description = "Return Hello World!",
            content = @Content(schema = @Schema(implementation = HelloWorldResponse.class)))
    @GetMapping("/helloworld")
    public ResponseEntity<HelloWorldResponse> getHelloWorld()
            throws Exception {
        HelloWorldResponse response = HelloWorldResponse.builder()
                .response("Hello World!")
                .build();
        helloWorldCounter.increment();
        log.info("returing response: {}", response);
        return ResponseEntity.ok(response);
    }
}
