package com.fiera.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tracker {

	@Id
	private String id;
	private String target;
	private boolean valid;
	private String link;
	private Long redirectedQuantity;
	private LocalDate createDate;
	private LocalDateTime expirationDate;
}
