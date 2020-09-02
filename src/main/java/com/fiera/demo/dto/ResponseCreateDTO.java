package com.fiera.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseCreateDTO {

	private String target;
	private String link;
	private boolean valid;
}
