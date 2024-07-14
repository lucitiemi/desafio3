package com.luciana.desafio.services.exceptions;

public class InsufficientStockException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InsufficientStockException(Object id) {
		super("Estoque insuficiente / Id: " + id);
	}

}
