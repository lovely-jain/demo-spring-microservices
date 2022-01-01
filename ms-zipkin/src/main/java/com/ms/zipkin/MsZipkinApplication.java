package com.ms.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class MsZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsZipkinApplication.class, args);
	}

}
