package com.luciana.desafio.entities.enums;

public enum TipoCliente {
	
	ADMIN(0),
	USUARIO(1);
	
	private int code;
	
	private TipoCliente(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static TipoCliente valueOf(int code) {
		for (TipoCliente value :  TipoCliente.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo de Tipo de Cliente Invalido");
	}
}
