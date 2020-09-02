package com.fiera.demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fiera.demo.validate.Validator;
import com.fiera.demo.validate.ValidatorImpl;


public class ValidatorTest {

	private Validator validate = new ValidatorImpl();

	@Test
	public void validateCreateOkTest() {
		
		assertThat(validate.validCreate("https://www.fierastudio.com/")).isTrue();
		
	}
}
