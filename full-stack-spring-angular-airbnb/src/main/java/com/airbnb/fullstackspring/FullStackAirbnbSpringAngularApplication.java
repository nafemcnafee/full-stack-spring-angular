package com.airbnb.fullstackspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@EnableAutoConfiguration
@ComponentScan

public class FullStackAirbnbSpringAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullStackAirbnbSpringAngularApplication.class, args);
		System.out.println("hiii");

	}
}