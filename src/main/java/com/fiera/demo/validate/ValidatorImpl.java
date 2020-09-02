package com.fiera.demo.validate;

import com.fiera.demo.enumerator.ErrorsMessage;
import com.fiera.demo.model.Tracker;

public class ValidatorImpl implements Validator<Tracker,String>{
	private boolean result;
	private ErrorsMessage message;
	
	@Override
	public boolean validCreate(String url) {
		this.result = true;
		
		if(url == null || url.isEmpty() ) {
			this.result = false;
			message = ErrorsMessage.URL_NULL;
		}
		
		return this.result;
	}

	@Override
	public boolean validEntity(Tracker entity) {
		// TODO Auto-generated method stub
		return false;
	}



}
