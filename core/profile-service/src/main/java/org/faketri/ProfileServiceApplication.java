package org.faketri;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(ProfileServiceApplication.class);

    public static void main(String[] args) {
        MDC.put("service", "profile-service");
        log.info("Starting ProfileApplication");
        SpringApplication.run(ProfileServiceApplication.class, args);
    }
}
