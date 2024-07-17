package com.luciana.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.luciana.desafio.entities.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	UserDetails findByEmail(String email);
	

}
