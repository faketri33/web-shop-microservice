package org.faketri;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

    private static final Logger log = LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        MDC.put("service", "api-gateway");
        log.info("Starting GatewayApplication");
        SpringApplication.run(GatewayApplication.class, args);
    }
}
