package com.luciana.desafio.entities.enums;

public enum TipoCliente {
	
	ADMIN("admin"),
	USUARIO("user");
	
	private String role;
	
	private TipoCliente(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	/*
	public static TipoCliente valueOf(int code) {
		for (TipoCliente value :  TipoCliente.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo de Tipo de Cliente Invalido");
	}
	*/
}
