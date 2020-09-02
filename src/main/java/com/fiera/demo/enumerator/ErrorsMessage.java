package com.fiera.demo.enumerator;

import org.springframework.http.HttpStatus;
/**
 * Este enum se encarga de manejar los errores de la aplicacion
 * @author oscar
 *
 */
public enum ErrorsMessage {

	URL_INVALID("La url no es valida",HttpStatus.NOT_FOUND),
	URL_INVALIDATED("La url esta invalidada",HttpStatus.NOT_FOUND),
	URL_EXPIRED("La url expiro",HttpStatus.NOT_FOUND),
	URL_NULL("El campo url no puede ser nulo o vacio",HttpStatus.BAD_REQUEST),
	URL_NOT_EXIST("La url que solicita no existe",HttpStatus.NOT_FOUND),
	ERROR_CONNECTION("No se pudo dar de alta error de conexion",HttpStatus.INTERNAL_SERVER_ERROR);

	
	private String description;
	private HttpStatus status;
	
	private ErrorsMessage(String description,HttpStatus status) {
		this.description = description;
		this.status = status;
	}

	public String getDescription() {
		return description;
	}
}
