package com.luciana.desafio.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luciana.desafio.entities.Venda;


public interface VendaRepository extends JpaRepository<Venda, Integer> {

	List<Venda> findByDataVendaBetween(Instant dataInicial, Instant dataFinal);
	
	
	

}
