package com.fiera.demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fiera.demo.builder.TrackerBuilder;
import com.fiera.demo.model.Tracker;
import com.fiera.demo.util.Mask;

public class TrackerBuilderTest {

	private TrackerBuilder trackerBuilder;
	
	@BeforeEach
	public void initMocks() { 
		Mask mask = Mockito.mock(Mask.class);
		Mockito.when(mask.generate()).thenReturn("AdhjfsH");
		trackerBuilder = new TrackerBuilder(mask);
	}
	
	@Test
	public void builderEmptyTest() {
		Tracker track = trackerBuilder.build("www.prueba.com");
		assertThat(track.getCreateDate()).isEqualTo(LocalDate.now());
		assertThat(track.getId()).isEqualTo("AdhjfsH");
		assertThat(track.getLink()).isEqualTo("http://localhost:8080/AdhjfsH");
		assertThat(track.getRedirectedQuantity()).isEqualTo(0);
		assertThat(track.getTarget()).isEqualTo("www.prueba.com");

	}
}
