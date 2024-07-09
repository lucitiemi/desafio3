package com.luciana.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luciana.desafio.entities.ItemVenda;
import com.luciana.desafio.entities.pk.ItemVendaPK;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, ItemVendaPK> {
	
	

}
