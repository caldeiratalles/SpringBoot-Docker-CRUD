package com.example.springbootapirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@SpringBootApplication(scanBasePackages = "com.example.springbootapirest")
@EntityScan(basePackages = "com.example.springbootapirest.model")
public class SpringBootApirestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApirestApplication.class, args);
    }

}
