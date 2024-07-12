package com.luciana.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luciana.desafio.entities.Produto;
import java.util.List;


public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	List<Produto> findByStatusProdTrue();

}
