package com.luciana.desafio.services.exceptions;

public class GenerationTokenException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public GenerationTokenException() {
		super("Error while generating token");
	}

}
