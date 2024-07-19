package com.luciana.desafio.services.exceptions;

public class ForbiddenAccessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ForbiddenAccessException() {
		super("Acesso negado");
	}

}
