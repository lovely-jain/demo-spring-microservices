package com.ms.coder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CoderController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${sample-message}")
	String msg;
	
	private String uri ="http://PROBLEMMS/problem/";
	
	@GetMapping("/coder/{user}/problems")
	public ResponseEntity<List<String>> check(@PathVariable("user") String user ) {
		System.out.println("username-> "+user);
		List<String> problems = restTemplate.getForObject(uri+user, List.class);
		return ResponseEntity.ok(problems);
	}
	
	@HystrixCommand(fallbackMethod = "fallback")
	@GetMapping("/coder")
	public ResponseEntity<String> getHelloMessage(@RequestParam String user ) {
		System.out.println(msg+" "+user);
		if(user.equals("invalid"))
			throw new RuntimeException();
		return ResponseEntity.ok("Hello "+user);
	}
	
	public ResponseEntity<String> fallback(String user) {
		System.out.println("-------fallback-------");
		return  ResponseEntity.ok("Fallback-- "+user);
	}
	
	@PutMapping("/coder/{name}")
	public ResponseEntity<List<String>> addProblem(@PathVariable String name ) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<List> entity = new HttpEntity(headers);
		ResponseEntity<List> problems = restTemplate.exchange(uri+name, HttpMethod.PUT, entity,  List.class);
		return ResponseEntity.ok(problems.getBody());
	}
	
}
