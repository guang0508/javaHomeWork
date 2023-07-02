package com.guangxi.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.guangxi.shop")
@MapperScan("com.guangxi.shop.mapper")
public class Shop_4Application {

	public static void main(String[] args) {
		SpringApplication.run(Shop_4Application.class, args);
	}

}
