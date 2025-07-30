package com.gdwho.api;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@EnableFeignClients(basePackages = "com.gdwho.api.infrastructure.gateways.api")
@ComponentScan(basePackages = "com.gdwho.api")
@SpringBootApplication
public class InitializateApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(InitializateApplication.class, args);
	}

}
