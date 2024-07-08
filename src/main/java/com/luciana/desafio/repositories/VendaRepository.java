package com.luciana.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luciana.desafio.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
	
	

}
