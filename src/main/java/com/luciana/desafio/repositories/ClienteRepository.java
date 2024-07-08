package com.luciana.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luciana.desafio.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	

}
