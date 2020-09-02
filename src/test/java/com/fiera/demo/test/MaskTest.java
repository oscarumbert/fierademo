package com.fiera.demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fiera.demo.util.Mask;

public class MaskTest {

	private Mask mask = new Mask();
	@Test
	public void generateOk() {
		String result = mask.generate();
		assertThat(result).isNotEmpty();

	}
}
