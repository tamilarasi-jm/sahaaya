package com.sahaaya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SahaayaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SahaayaApplication.class, args);
    }
}