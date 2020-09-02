package com.fiera.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.fiera.demo.exception.TrackerException;


@RestController
public class TrackerController {
	
	@Autowired
	private TrackerServiceImpl trackerService;
	
	@Autowired
	private Converter converter;
	
	public TrackerController(TrackerServiceImpl trackerServiceImpl,Converter converter) {
		this.trackerService = trackerServiceImpl;
		this.converter =  converter;
	}
	
	@PostMapping("create")
	public ResponseEntity<?> createTracker(@RequestParam String url) throws TrackerException{
		ResponseCreateDTO response = null;
		Tracker tracker = trackerService.create(url);
		response = converter.convertToReponseCreateDTO(tracker);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	@GetMapping(value ="{url}")
	public ResponseEntity<?> redirect(@PathVariable String url) throws URISyntaxException, TrackerException{
		Tracker tracker = trackerService.get(url,true);
		URI yahoo = new URI(tracker.getTarget()); 
		HttpHeaders httpHeaders = new HttpHeaders(); 
		httpHeaders.setLocation(yahoo); 
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER); 

	}
	@GetMapping(value="redirectedQuantity/{url}")
	public ResponseEntity<?> redirectedQuantity(@PathVariable String url) throws TrackerException{
		Tracker tracker = trackerService.get(url,false);
		return ResponseEntity.status(HttpStatus.OK).body(tracker.getRedirectedQuantity());

	}
	@PutMapping(value ="{url}")
	public ResponseEntity<?> invalid(@PathVariable String url) throws TrackerException{
		trackerService.update(url);
		return ResponseEntity.status(HttpStatus.OK).body("Se invalido la url exitosamente");

	}
}
