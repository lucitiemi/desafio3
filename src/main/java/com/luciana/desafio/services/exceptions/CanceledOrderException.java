package com.luciana.desafio.services.exceptions;

public class CanceledOrderException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CanceledOrderException(Object id) {
		super("Venda ja esta cancelada / Id: " + id);
	}

}
