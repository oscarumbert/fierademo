package com.fiera.demo.exception;

import com.fiera.demo.enumerator.ErrorsMessage;

import lombok.Getter;

@Getter
public class TrackerException extends Exception {

	private ErrorsMessage errorsMessage;
	
	public TrackerException(ErrorsMessage errorsMessage) {
		super(errorsMessage.getDescription());
		this.errorsMessage = errorsMessage;
	}
	
}
