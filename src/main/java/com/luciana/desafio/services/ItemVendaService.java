package com.luciana.desafio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciana.desafio.entities.ItemVenda;
import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.repositories.ItemVendaRepository;

@Service
public class ItemVendaService {

	@Autowired
	private ItemVendaRepository repository;
	

	
	
	
	
	// Para criar itemVenda
	public ItemVenda inserir(Venda venda, Produto produto, Integer quantidade) {
		ItemVenda itemVenda = new ItemVenda();
		
		
		itemVenda.setVenda(venda);
		

		itemVenda.setProduto(produto);
		
		itemVenda.setQuantidade(quantidade);
		
		itemVenda.setPrice(produto.getPreco());
		
		return repository.save(itemVenda);
	}
	

}
