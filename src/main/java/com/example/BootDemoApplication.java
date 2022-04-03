package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@Cacheable
public class BootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootDemoApplication.class, args);
    }

}
