package com.fiera.demo.builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fiera.demo.model.Tracker;
import com.fiera.demo.util.Mask;

@Component
public class TrackerBuilder {
	private Tracker tracker;
	
	private final String DOMINIO = "http://localhost:8080/";
	private final int TIME_EXPERITION = 1;
	
	@Autowired
	private Mask mask;
	
	public TrackerBuilder(Mask mask) {
		this.mask = mask;
	}
	public Tracker build(String url) {
		String id = mask.generate();
		
		this.tracker = new Tracker();
		this.tracker.setId(id);
		this.tracker.setCreateDate(LocalDate.now());
		this.tracker.setLink(DOMINIO+id);
		this.tracker.setTarget(url);
		this.tracker.setRedirectedQuantity(0L);
		this.tracker.setValid(true);
		this.tracker.setExpirationDate(LocalDateTime.now().plusMinutes(TIME_EXPERITION));
		return tracker;
		
	}

}
