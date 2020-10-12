package com.example.piwkotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"Service"})
@ComponentScan({"Config"})
@EnableJpaRepositories({"Repository"})
@ComponentScan({"com.example.piwkotest"})
@EntityScan({"Entity"})
public class PiwkotestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiwkotestApplication.class, args);
    }

}
