package com.guangxi.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
@ComponentScan("com.guangxi")
public class Shop2_0Application {

    public static void main(String[] args) {
        SpringApplication.run(Shop2_0Application.class, args);
    }

}
