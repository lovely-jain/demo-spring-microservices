package com.ms.coder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CoderController {
	@Autowired
	private RestTemplate restTemplate;
	
	private String uri ="http://PROBLEMMS/";
	
	@GetMapping("/")
	public ResponseEntity<String> getHelloMessage(@RequestParam String user ) {
		return ResponseEntity.ok("Hello "+user);
	}
	
	@GetMapping("/{user}/problems")
	public ResponseEntity<List<String>> getUserRelatedProblems(@PathVariable String user ) {
		List<String> problems = restTemplate.getForObject(uri+user, List.class);
		return ResponseEntity.ok(problems);
	}
	
	
}
