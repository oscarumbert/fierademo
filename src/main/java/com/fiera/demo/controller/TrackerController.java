package com.fiera.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiera.demo.dto.Converter;
import com.fiera.demo.dto.ResponseCreateDTO;
import com.fiera.demo.model.Tracker;
import com.fiera.demo.service.TrackerServiceImpl;


@RestController
public class TrackerController {
	
	@Autowired
	private TrackerServiceImpl trackerService;
	
	@Autowired
	private Converter converter;
	
	@PostMapping("create")
	public ResponseEntity<?> createTracker(@RequestParam String url) throws Exception{
		ResponseCreateDTO response = null;
		Tracker tracker = trackerService.create(url);
		response = converter.convertToReponseCreateDTO(tracker);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
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
