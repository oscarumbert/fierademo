package com.fiera.demo.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fiera.demo.builder.TrackerBuilder;
import com.fiera.demo.enumerator.ErrorsMessage;
import com.fiera.demo.exception.TrackerException;
import com.fiera.demo.repository.TrackerRepository;
import com.fiera.demo.service.TrackerServiceImpl;
import com.fiera.demo.validate.ValidatorImpl;
import com.fiera.demo.model.Tracker;

public class TrackerServiceTest {
	
	private TrackerServiceImpl trackerService;
	
	@BeforeEach
	public void initMocks() { 
		TrackerRepository trackerRepository = createTrackerRepositoryMock(createTracker("","https://www.fiera.com.ar"));
		ValidatorImpl validate = createValidateMock(true,null);
		TrackerBuilder trackerBuilder = createTrackerBuilderMock(createTracker("","https://www.fiera.com.ar"));
		
		trackerService = new TrackerServiceImpl(trackerRepository,validate,trackerBuilder);
	}
	
	@Test
	public void createOkTest() throws TrackerException {
		String url ="https://www.fiera.com.ar";
		assertThat(trackerService.create(url)).extracting("target").isEqualTo("https://www.fiera.com.ar");
	}
	
	@Test
	public void createTestException() {
		String url ="https://www.fiera.com.ar";
		//mock
		TrackerRepository trackerRepository = createTrackerRepositoryMock(null);
		ValidatorImpl validate = createValidateMock(true,null);
		TrackerBuilder trackerBuilder = createTrackerBuilderMock(createTracker("","https://www.fiera.com.ar"));
		trackerService = new TrackerServiceImpl(trackerRepository,validate,trackerBuilder);
				
		assertThatExceptionOfType(TrackerException.class)
		  .isThrownBy(() -> {
			  trackerService.create(url);
		}).withMessageMatching("No se pudo dar de alta error de conexion");
		
	}
	
	@Test
	public void createtUrlNullTes() throws TrackerException {
		String url ="";
		//mock
		TrackerRepository trackerRepository = createTrackerRepositoryMock(createTracker("https://www.prueba.com","https://www.fiera.com.ar"));
		ValidatorImpl validate = createValidateMock(false,ErrorsMessage.URL_INVALID);
		TrackerBuilder trackerBuilder = createTrackerBuilderMock(createTracker("","https://www.fiera.com.ar"));
		trackerService = new TrackerServiceImpl(trackerRepository,validate,trackerBuilder);
		
		assertThatExceptionOfType(TrackerException.class)
		  .isThrownBy(() -> {
			  trackerService.create(url);
		}).withMessageMatching(ErrorsMessage.URL_INVALID.getDescription());	
	}
	@Test
	public void getOkWithValidTest() throws TrackerException {
		String url ="https://www.fiera.com.ar";
		assertThat(trackerService.get("", true)).extracting("target").isEqualTo("https://www.fiera.com.ar");
	}

	@Test
	public void getOkWithoutValidTest() throws TrackerException {
		String url = "https://www.prueba.com";
		// mock
		TrackerRepository trackerRepository = createTrackerRepositoryMock(createTracker("https://www.prueba.com","https://www.fiera.com.ar"));
		ValidatorImpl validate = createValidateMock(false, ErrorsMessage.URL_INVALID);
		TrackerBuilder trackerBuilder = createTrackerBuilderMock(createTracker("","https://www.fiera.com.ar"));
		trackerService = new TrackerServiceImpl(trackerRepository, validate, trackerBuilder);
		Tracker tracker = trackerService.get("", false);
		assertThat(tracker).extracting("target").isEqualTo("https://www.fiera.com.ar");
	}
	@Test
	public void getInvalidTest() throws TrackerException {
		String url ="https://www.fiera.com.ar";
		//mock
		TrackerRepository trackerRepository = createTrackerRepositoryMock(createTracker("https://www.prueba.com","https://www.fiera.com.ar"));
		ValidatorImpl validate = createValidateMock(false,ErrorsMessage.URL_INVALID);
		TrackerBuilder trackerBuilder = createTrackerBuilderMock(createTracker("","https://www.fiera.com.ar"));
		trackerService = new TrackerServiceImpl(trackerRepository,validate,trackerBuilder);
		
		assertThatExceptionOfType(TrackerException.class)
		  .isThrownBy(() -> {
			  trackerService.get(url,true);
		}).withMessageMatching(ErrorsMessage.URL_INVALID.getDescription());	}
	
	@Test
	public void getErrorTest() throws TrackerException {
		String url ="https://www.fiera.com.ar";
		//mock
		TrackerRepository trackerRepository = createTrackerRepositoryMock(null);
		ValidatorImpl validate = createValidateMock(true,null);
		TrackerBuilder trackerBuilder = createTrackerBuilderMock(createTracker("","https://www.fiera.com.ar"));
		trackerService = new TrackerServiceImpl(trackerRepository,validate,trackerBuilder);
		
		assertThatExceptionOfType(TrackerException.class)
		  .isThrownBy(() -> {
			  trackerService.get("", true);
		}).withMessageMatching(ErrorsMessage.URL_NOT_EXIST.getDescription());	
		
	}	
	@Test
	public void getOkUpdate() throws TrackerException {
		String url ="https://www.fiera.com.ar";
		assertThat(trackerService.update("https://www.fiera.com.ar")).isTrue();
	}

	@Test
	public void getErrorUpdate() throws TrackerException {
		String url ="https://www.fiera.com.ar";
		//mock
		TrackerRepository trackerRepository = createTrackerRepositoryMock(null);
		ValidatorImpl validate = createValidateMock(true,null);
		TrackerBuilder trackerBuilder = createTrackerBuilderMock(createTracker("","https://www.fiera.com.ar"));
		trackerService = new TrackerServiceImpl(trackerRepository,validate,trackerBuilder);
		
		assertThatExceptionOfType(TrackerException.class)
		  .isThrownBy(() -> {
			  trackerService.update("https://www.fiera.com.ar");
		}).withMessageMatching(ErrorsMessage.URL_NOT_EXIST.getDescription());	
		
	}
	
	private Tracker createTracker(String url,String target) {
		Tracker tracker = new Tracker();
		tracker.setLink(url);
		tracker.setTarget(target);
		tracker.setValid(true);
		tracker.setId("retYUjU");
		tracker.setRedirectedQuantity(0L);
		tracker.setExpirationDate(LocalDateTime.now());
		return tracker;
	}
	private TrackerRepository createTrackerRepositoryMock(Tracker tracker) {
		TrackerRepository trackerRepository = Mockito.mock(TrackerRepository.class);
		Mockito.when(trackerRepository.save(Mockito.isA(Tracker.class))).thenReturn(tracker);
		
		Mockito.when(trackerRepository.findById(Mockito.anyString())).thenReturn(tracker != null ? Optional.of(tracker): 
																				 Optional.ofNullable(tracker));
		return trackerRepository;
	} 
	private ValidatorImpl createValidateMock(boolean result,ErrorsMessage error) {
		ValidatorImpl validate = Mockito.mock(ValidatorImpl.class);
		Mockito.when(validate.validCreate(Mockito.anyString())).thenReturn(result);
		Mockito.when(validate.validEntity(Mockito.isA(Tracker.class))).thenReturn(result);
		Mockito.when(validate.getMessage()).thenReturn(error);

		return validate;
	} 
	private TrackerBuilder createTrackerBuilderMock(Tracker tracker) {
		TrackerBuilder trackerBuilder = Mockito.mock(TrackerBuilder.class);
		Mockito.when(trackerBuilder.build(Mockito.anyString())).thenReturn(tracker);
		return trackerBuilder;
	} 

}
