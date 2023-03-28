package com.tradeteam.OrderBookEurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OrderBookEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderBookEurekaServerApplication.class, args);
	}

}
