package com.ms.problem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProblemController {
	List<String> problems = new ArrayList<String>();
	
	@GetMapping("/{user}")
	public List<String> allProblemsOfUser(@PathVariable("user") String userId) {
		if("lovely".equals(userId)) {
			problems.add("0-1 Knapsack");
			problems.add("DFS");
		}
		else {
			problems.add("Matrix Chain Multiplication");
			problems.add("BFS");
		}
		return problems;
	}
	
	@PutMapping("/{problem}")
	public ResponseEntity<List<String>> addProblems(@PathVariable("problem") String name) {
		problems.add(name);
		return ResponseEntity.ok(problems);
	}
	
}
