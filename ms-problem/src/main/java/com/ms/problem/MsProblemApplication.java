package com.ms.problem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsProblemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProblemApplication.class, args);
	}

}
