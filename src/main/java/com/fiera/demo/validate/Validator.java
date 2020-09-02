package com.fiera.demo.validate;

public interface Validator <T,S>{

	public boolean validCreate(S dto);
	public boolean validEntity(T entity);
	
}
