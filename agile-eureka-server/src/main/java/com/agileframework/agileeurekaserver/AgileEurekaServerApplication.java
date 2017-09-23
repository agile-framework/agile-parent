package com.agileframework.agileeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AgileEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgileEurekaServerApplication.class, args);
	}
}
