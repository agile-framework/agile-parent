package com.agileframework.agileadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
//开启turbine统计
@EnableTurbine
public class AgileTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgileTurbineApplication.class, args);
	}
}
