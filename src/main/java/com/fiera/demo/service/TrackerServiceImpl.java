package com.fiera.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fiera.demo.model.Tracker;
import com.fiera.demo.builder.TrackerBuilder;
import com.fiera.demo.enumerator.ErrorsMessage;
import com.fiera.demo.exception.TrackerException;
import com.fiera.demo.repository.TrackerRepository;
import com.fiera.demo.validate.ValidatorImpl;

public class TrackerServiceImpl implements ServiceGeneric<Tracker,String>{

	@Autowired
	private TrackerRepository trackerRepository;
	
	@Autowired
	private ValidatorImpl validate;
	
	@Autowired
	private TrackerBuilder trackerBuilder;
	
	public TrackerServiceImpl(TrackerRepository linkRepository, ValidatorImpl validate,TrackerBuilder linkBuilder) {
		this.trackerRepository = linkRepository;
		this.validate = validate;
		this.trackerBuilder = linkBuilder;
	}
	
	@Override
	public Tracker create(String id) throws TrackerException {
		Tracker tracker = null;
		
		if(validate.validCreate(id)) {
			
			tracker = trackerBuilder.build(id);
			tracker = trackerRepository.save(tracker);
			
			if(tracker == null) {
				throw new TrackerException(ErrorsMessage.ERROR_CONNECTION);
			}
			
		}else {
			throw new TrackerException(validate.getMessage());
		}
		
		return tracker;
	}

	@Override
	public Tracker get(String id, boolean withValidations) throws TrackerException {
		Optional<Tracker> optional = trackerRepository.findById(id);
		
		if(optional.isPresent()) {
			if( withValidations && !validate.validEntity(optional.get())) {
				throw new TrackerException(validate.getMessage());

			}else {
				Tracker tracker = optional.get();
				tracker.setRedirectedQuantity(tracker.getRedirectedQuantity()+1);
				//TODO se deberia validar que guardo bien
				trackerRepository.save(tracker);
				return tracker;
			}
		}else {
			throw new TrackerException(ErrorsMessage.URL_NOT_EXIST);
		}
	}

	@Override
	public boolean update(String entity) throws TrackerException {
		// TODO Auto-generated method stub
		return false;
	}

}
