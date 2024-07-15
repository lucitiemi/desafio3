package com.luciana.desafio.services.exceptions;

public class EmptyOrderException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EmptyOrderException(Object id) {
		super("Venda esta vazia / Id: " + id);
	}

}
