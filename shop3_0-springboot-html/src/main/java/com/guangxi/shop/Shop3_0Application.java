package com.guangxi.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.guangxi")
public class Shop3_0Application {

    public static void main(String[] args) {
        SpringApplication.run(Shop3_0Application.class, args);
    }

}
