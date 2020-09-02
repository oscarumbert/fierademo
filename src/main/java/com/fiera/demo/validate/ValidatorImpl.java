package com.fiera.demo.validate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fiera.demo.enumerator.ErrorsMessage;
import com.fiera.demo.model.Tracker;

import lombok.Getter;

@Getter
@Component
public class ValidatorImpl implements Validator<Tracker,String>{
	private boolean result;
	private ErrorsMessage message;
	
	@Override
	public boolean validCreate(String url) {
		this.result = true;
		
		if(url == null || url.isEmpty() ) {
			this.result = false;
			message = ErrorsMessage.URL_NULL;
		}else if(!isValidUrl(url)){
			this.result = false;
			message = ErrorsMessage.URL_INVALID;
		}
		
		return this.result;
	}

	@Override
	public boolean validEntity(Tracker tracker) {
		this.result = true;
		
		if(!isValidUrl(tracker.getTarget())) {
			this.result = false;
			message = ErrorsMessage.URL_INVALID;
		}else if(!tracker.isValid()){
			this.result = false;
			message = ErrorsMessage.URL_INVALIDATED;
		}else if(!isExpired(tracker.getExpirationDate())) {
			this.result = false;
		}
		return this.result;
	}
	
	private boolean isValidUrl(String url) {
		boolean result = true;
		try {
            new URL(url).toURI();
        }
        catch (URISyntaxException | MalformedURLException e) {
        	result = false;
        }
		
		return result;
	}
	private boolean isExpired(LocalDateTime date) {
		boolean result = true;
		LocalDateTime actually = LocalDateTime.now();
		
		if(date.isBefore(LocalDateTime.now())) {
			message = ErrorsMessage.URL_EXPIRED;
			result = false;
		}
		return result;
		
	}

}
