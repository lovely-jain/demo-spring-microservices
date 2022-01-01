package com.infosys.infytel.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin2.server.internal.EnableZipkinServer;

//import zipkin.server.internal.EnableZipkinServer;


//import zipkin2.server.internal.EnableZipkinServer;



@SpringBootApplication
@EnableZipkinServer
public class InfytelZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfytelZipkinApplication.class, args);
	}
}
