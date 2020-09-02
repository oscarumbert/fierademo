package com.fiera.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


public class TrackerController {

	
	@PostMapping("create")
	public ResponseEntity<?> createTracker(@RequestParam String url){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
		
	}
	@GetMapping(value ="{url}")
	public ResponseEntity<?> redirect(@PathVariable String url){
		return ResponseEntity.status(HttpStatus.CREATED).body(null);


	}
	@GetMapping(value="redirectedQuantity/{url}")
	public ResponseEntity<?> redirectedQuantity(@PathVariable String url) {
		return ResponseEntity.status(HttpStatus.CREATED).body(null);


	}
	@PutMapping(value ="{url}")
	public ResponseEntity<?> invalid(@PathVariable String url){
		return ResponseEntity.status(HttpStatus.CREATED).body(null);

	}
}
