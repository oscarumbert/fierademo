package com.fiera.demo.util;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Mask {

	private final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public String generate() {		
	    Random random = new Random();
	    StringBuilder stringBuilder = new StringBuilder();
	    for(int i = 0;i<10;i++) {
	    	stringBuilder.append(LETTERS.charAt(random.nextInt(LETTERS.length()-1)));
	    }
	    
	    return stringBuilder.toString();
	}
}
