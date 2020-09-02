package com.fiera.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fiera.demo.exception.TrackerException;

@RestControllerAdvice
public class ErrorController {

	@ExceptionHandler(TrackerException.class)
	public ResponseEntity<?> handleTrackerException(TrackerException e){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
}
