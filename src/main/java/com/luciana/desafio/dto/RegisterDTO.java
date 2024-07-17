package com.luciana.desafio.dto;

import com.luciana.desafio.entities.enums.TipoCliente;

public record RegisterDTO(TipoCliente role, String nome, String cpf, String email, String senha) {

}
