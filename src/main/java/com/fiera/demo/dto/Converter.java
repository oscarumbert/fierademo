package com.fiera.demo.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fiera.demo.model.Tracker;

@Component
public class Converter {

	private final ModelMapper modelMapper = new ModelMapper();
	
	public ResponseCreateDTO convertToReponseCreateDTO(Tracker tracker) {
		return modelMapper.map(tracker, ResponseCreateDTO.class);
	}
	
}
