package com.fiera.demo.validate;

/**
 * Esta interface se encarga de validar un objeto determinado
 * @author oscar
 *
 * @param <T>
 * @param <S>
 */
public interface Validator <T,S>{
	/**
	 * Este metodo se encargara de validar un objeto antes de que se persista en la base
	 * @param dto
	 * @return
	 */
	public boolean validCreate(S dto);
	/**
	 * Este metodo se encarga de validar si un objeto esta valido
	 * @param entity
	 * @return
	 */
	public boolean validEntity(T entity);
	
}
