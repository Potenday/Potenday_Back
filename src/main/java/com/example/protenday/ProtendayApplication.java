package com.example.protenday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ProtendayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProtendayApplication.class, args);
    }

}
