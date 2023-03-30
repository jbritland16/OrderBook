package com.tradeteam.TradingEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TradingEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingEngineApplication.class, args);
	}

}
