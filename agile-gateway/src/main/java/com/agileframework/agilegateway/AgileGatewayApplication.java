package com.agileframework.agilegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableZuulProxy
public class AgileGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgileGatewayApplication.class, args);
	}
}
