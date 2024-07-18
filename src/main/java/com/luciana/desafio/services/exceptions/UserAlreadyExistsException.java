package com.luciana.desafio.services.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistsException() {
		super("Usuario ja cadastrado");
	}

}
