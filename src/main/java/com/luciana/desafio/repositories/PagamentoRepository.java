package com.luciana.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luciana.desafio.entities.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
	
	

}
