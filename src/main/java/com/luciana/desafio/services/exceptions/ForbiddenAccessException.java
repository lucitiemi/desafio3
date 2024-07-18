package com.luciana.desafio.services.exceptions;

public class ForbiddenAccessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ForbiddenAccessException(Object id) {
		super("Acesso negado / Id: " + id);
	}

}
