package com.luciana.desafio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luciana.desafio.entities.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	Optional<Cliente> findByEmail(String email);
	

}
