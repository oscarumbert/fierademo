package com.fiera.demo.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fiera.demo.service.TrackerServiceImpl;
import com.fiera.demo.dto.ResponseCreateDTO;
import com.fiera.demo.model.Tracker;
import com.fiera.demo.controller.TrackerController;
import com.fiera.demo.dto.Converter;
import com.fiera.demo.enumerator.ErrorsMessage;
import com.fiera.demo.exception.TrackerException;

public class TrackerControllerTest {


	private TrackerServiceImpl trackService;
	private Converter converter;
	private TrackerController trackController;
	
	@BeforeEach
	public void initMocks() throws TrackerException { 
		trackService = createServiceMock("https://www.fiera.com.ar");
		converter = createConverter();
		
		trackController = new TrackerController(trackService,converter);
	}
	@Test
	public void createOkTest() throws TrackerException {
		assertThat(trackController.createTracker("https://www.fiera.com.ar")).isNotNull();

	}
	@Test
	public void createExceptionTest() throws TrackerException {
		trackService = createServiceMockException();
		trackController = new TrackerController(trackService,converter);

		assertThatExceptionOfType(TrackerException.class)
		  .isThrownBy(() -> {
			  trackController.createTracker("https://www.fiera.com.ar");
	    }).withMessageMatching(ErrorsMessage.URL_NULL.getDescription());	

	}
	
	@Test
	public void redirectOkTest() throws TrackerException, URISyntaxException {
		assertThat(trackController.redirect("https://www.fiera.com.ar")).isNotNull();

	}
	@Test
	public void redirectExceptionTest() throws TrackerException {
		trackService = getServiceMockException();
		trackController = new TrackerController(trackService,converter);

		assertThatExceptionOfType(TrackerException.class)
		  .isThrownBy(() -> {
			  trackController.redirect("https://www.fiera.com.ar");
	    }).withMessageMatching(ErrorsMessage.URL_NULL.getDescription());	

	}
	
	@Test
	public void redirectedQuantityOkTest() throws TrackerException, URISyntaxException {
		assertThat(trackController.redirectedQuantity("https://www.fiera.com.ar")).isNotNull();

	}
	@Test
	public void redirectedQuantityExceptionTest() throws TrackerException {
		trackService = getServiceMockException();
		trackController = new TrackerController(trackService,converter);

		assertThatExceptionOfType(TrackerException.class)
		  .isThrownBy(() -> {
			  trackController.redirectedQuantity("https://www.fiera.com.ar");
	    }).withMessageMatching(ErrorsMessage.URL_NULL.getDescription());	

	}
	@Test
	public void updateOkTest() throws TrackerException, URISyntaxException {
		assertThat(trackController.invalid("https://www.fiera.com.ar").getBody()).isEqualTo("Se invalido la url exitosamente");

	}
	@Test
	public void updateExceptionTest() throws TrackerException {
		trackService = getServiceMockException();
		trackController = new TrackerController(trackService,converter);

		assertThatExceptionOfType(TrackerException.class)
		  .isThrownBy(() -> {
			  trackController.invalid("https://www.fiera.com.ar");
	    }).withMessageMatching(ErrorsMessage.URL_NULL.getDescription());		

	}
	
	private TrackerServiceImpl createServiceMock(String url) throws TrackerException {
		TrackerServiceImpl trackerService = (TrackerServiceImpl) Mockito.mock(TrackerServiceImpl.class);
		Mockito.when(trackerService.create(Mockito.anyString())).thenReturn(new Tracker());
		Mockito.when(trackerService.get("https://www.fiera.com.ar", true)).thenReturn(new Tracker("https://www.fiera.com.ar"));
		Mockito.when(trackerService.get("https://www.fiera.com.ar", false)).thenReturn(new Tracker());
		Mockito.when(trackerService.update(Mockito.anyString())).thenReturn(true);

		return trackerService;
	}
	private TrackerServiceImpl createServiceMockException() throws TrackerException {
		TrackerServiceImpl trackerService = (TrackerServiceImpl) Mockito.mock(TrackerServiceImpl.class);
		Mockito.when(trackerService.create(Mockito.anyString())).thenThrow(new TrackerException(ErrorsMessage.URL_NULL));

		return trackerService;
	}
	private TrackerServiceImpl getServiceMockException() throws TrackerException {
		TrackerServiceImpl trackerService = (TrackerServiceImpl) Mockito.mock(TrackerServiceImpl.class);
		Mockito.when(trackerService.get("https://www.fiera.com.ar", true)).thenThrow(new TrackerException(ErrorsMessage.URL_NULL));

		return trackerService;
	}
	private TrackerServiceImpl updateServiceMockException() throws TrackerException {
		TrackerServiceImpl trackerService = (TrackerServiceImpl) Mockito.mock(TrackerServiceImpl.class);
		Mockito.when(trackerService.update("https://www.fiera.com.ar")).thenThrow(new TrackerException(ErrorsMessage.URL_NULL));

		return trackerService;
	}
	private Converter createConverter() {
		Converter converter = Mockito.mock(Converter.class);
		Mockito.when(converter.convertToReponseCreateDTO(Mockito.isA(Tracker.class))).thenReturn(new ResponseCreateDTO());
		return converter;
		
	}
	
}
