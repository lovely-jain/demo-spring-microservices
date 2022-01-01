package com.ms.coder.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class CoderController {
	@Autowired
	private RestTemplate restTemplate;
	
	private String uri ="http://PROBLEMMS/";
	
	@GetMapping("/")
	public ResponseEntity<String> getHelloMessage(@RequestParam String user ) {
		return ResponseEntity.ok("Hello "+user);
	}
	
	@GetMapping("/coder/{user}/problems")
	public ResponseEntity<List<String>> getUserRelatedProblems(@PathVariable String user ) {
		System.out.println("here ");
		List<String> problems = restTemplate.getForObject(uri+user, List.class);
		return ResponseEntity.ok(problems);
	}
	
	@PutMapping("/{name}")
	public ResponseEntity<List<String>> addProblem(@PathVariable String name ) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<List> entity = new HttpEntity(headers);

		ResponseEntity<List> problems = restTemplate.exchange(uri+name, HttpMethod.PUT, entity,  List.class);
		return ResponseEntity.ok(problems.getBody());
	}
	
}
