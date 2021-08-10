package com.peanut.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.peanut"})
@SpringBootApplication
public class RabbitMqApp {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApp.class, args);
    }

}