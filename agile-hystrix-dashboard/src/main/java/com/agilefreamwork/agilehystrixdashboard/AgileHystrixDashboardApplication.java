package com.agilefreamwork.agilehystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//开启Hystrix仪表盘
@EnableHystrixDashboard
public class AgileHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgileHystrixDashboardApplication.class, args);
	}
}
