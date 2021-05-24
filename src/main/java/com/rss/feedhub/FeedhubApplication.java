package com.rss.feedhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FeedhubApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedhubApplication.class, args);
    }

}
