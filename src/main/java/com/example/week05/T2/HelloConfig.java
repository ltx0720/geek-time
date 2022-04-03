package com.example.week05.T2;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {

    @Bean
    public HelloService getHelloService() {
        return new HelloService();
    }
}
