package ru.geekbrains.cart_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CartServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(CartServiceApp.class);
    }
}
