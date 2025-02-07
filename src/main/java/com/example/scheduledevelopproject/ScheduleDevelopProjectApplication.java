package com.example.scheduledevelopproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleDevelopProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleDevelopProjectApplication.class, args);
    }

}
