package com.fiera.demo.service;
/**
 * Este servicio se encarga de conectarse con el repository y provee de las operaciones
 * alta update por el momento
 * @author oscar
 *
 * @param <T> en la entidad que se persiste con el repository
 * @param <S> el id de la entidad
 */
public interface ServiceGeneric <T,S>{

	public T create(S id) throws Exception;
	public T get(S id,boolean withValidations)  throws Exception ;
	public boolean update(S id) throws Exception;
}
