package com.luciana.desafio.services.exceptions;

public class UnavailableOrderException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UnavailableOrderException(Object id) {
		super("Apenas Vendas com status PENDENTE podem ser modificadas / Id: " + id);
	}


}
