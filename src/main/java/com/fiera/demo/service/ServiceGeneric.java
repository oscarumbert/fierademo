package com.fiera.demo.service;

public interface ServiceGeneric <T,S>{

	public T create(S id) throws Exception;
	public T get(S id,boolean withValidations)  throws Exception ;
	public boolean update(S id) throws Exception;
}
