package com.fiera.demo.service;

public interface ServiceGeneric <T,S>{

	public T create(S entity) throws Exception;
	public T get(S entity,boolean withValidations)  throws Exception ;
	public boolean update(S entity) throws Exception;
}
