package com.luciana.desafio.entities.enums;

public enum StatusVenda {
	
	PENDENTE(0),
	FECHADA(1),
	CANCELADA(2);
	
	private int code;
	
	private StatusVenda(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static StatusVenda valueOf(int code) {
		for (StatusVenda value :  StatusVenda.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo de Status de Venda Invalido");
	}

}
