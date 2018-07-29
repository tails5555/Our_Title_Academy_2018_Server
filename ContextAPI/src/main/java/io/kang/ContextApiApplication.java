package io.kang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ContextApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContextApiApplication.class, args);
    }
}
