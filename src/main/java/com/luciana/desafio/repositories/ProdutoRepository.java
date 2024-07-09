package com.luciana.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luciana.desafio.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	

}
