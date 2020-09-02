package com.fiera.demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.fiera.demo.enumerator.ErrorsMessage;
import com.fiera.demo.validate.ValidatorImpl;
import com.fiera.demo.model.Tracker;

public class ValidatorTest {

	private ValidatorImpl validate = new ValidatorImpl();

	@Test
	public void validateCreateOkTest() {
		
		assertThat(validate.validCreate("https://www.fierastudio.com/")).isTrue();
		
	}
	@Test
	public void validateCreateUrlInvalidTest() {
		
		assertThat(validate.validCreate("www.prueba.com")).isFalse();
		assertThat(validate.getMessage()).isEqualTo(ErrorsMessage.URL_INVALID);
		
	}
	@Test
	public void validateCreateError() {
		
		assertThat(validate.validCreate("")).isFalse();
		assertThat(validate.getMessage()).isEqualTo(ErrorsMessage.URL_NULL);

		
	}
	@Test
	public void validateCreateErrorNull() {
		
		assertThat(validate.validCreate(null)).isFalse();
		assertThat(validate.getMessage()).isEqualTo(ErrorsMessage.URL_NULL);

		
	}
	@Test
	public void validTrackerOK() {
		Tracker tracker = createTracker("https://www.fierastudio.com/",true,false);
		assertThat(validate.validEntity(tracker)).isTrue();
		
	}
	@Test
	public void validTrackerUrlInvalid() {
		Tracker tracker = createTracker("www.prueba.com",true,false);
		assertThat(validate.validEntity(tracker)).isFalse();
		assertThat(validate.getMessage()).isEqualTo(ErrorsMessage.URL_INVALID);
		
	}
	@Test
	public void validTrackerUrlInvalided() {
		Tracker tracker = createTracker("https://www.fierastudio.com/",false,false);
		assertThat(validate.validEntity(tracker)).isFalse();
		assertThat(validate.getMessage()).isEqualTo(ErrorsMessage.URL_INVALIDATED);
		
	}
	@Test
	public void validTrackerUrlExpired() {
		Tracker tracker = createTracker("https://www.fierastudio.com/",true,true);
		assertThat(validate.validEntity(tracker)).isFalse();
		assertThat(validate.getMessage()).isEqualTo(ErrorsMessage.URL_EXPIRED);
		
	}
	
	private Tracker createTracker(String url,boolean isValid,boolean isExpired) {
		Tracker tracker = new Tracker();

		tracker = new Tracker();
		tracker.setId("trytrIHsf");
		tracker.setCreateDate(LocalDate.now());
		tracker.setLink("http://localhost:8080/trytrIHsf");
		tracker.setTarget(url);
		tracker.setRedirectedQuantity(0L);
		tracker.setValid(isValid);
		
		tracker.setExpirationDate(!isExpired ? LocalDateTime.now().plusMinutes(2) : LocalDateTime.now().minusMinutes(2));
		return tracker;
	}
}
