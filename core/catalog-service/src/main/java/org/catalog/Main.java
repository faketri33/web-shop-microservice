package org.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.catalog", "org.shared"})
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        MDC.put("service", "catalog-service");
        log.info("Starting CatalogApplication");
        SpringApplication.run(Main.class, args);
    }
}
