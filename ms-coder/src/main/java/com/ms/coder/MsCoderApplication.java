package com.ms.coder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsCoderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCoderApplication.class, args);
	}

}
