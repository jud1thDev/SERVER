package com.efub.leadtoyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LeadToyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeadToyProjectApplication.class, args);
    }

}
