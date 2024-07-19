package com.luciana.desafio.services.exceptions;

public class InvalidResetPasswordTokenException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidResetPasswordTokenException() {
		super("Token inválido");
	}

}
