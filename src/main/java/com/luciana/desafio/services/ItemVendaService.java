package com.luciana.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciana.desafio.entities.ItemVenda;
import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.entities.pk.ItemVendaPK;
import com.luciana.desafio.repositories.ItemVendaRepository;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;

@Service
public class ItemVendaService {

	@Autowired
	private ItemVendaRepository repository;


	// Para consultar venda
	public List<ItemVenda> findAll() {
		return repository.findAll();
	}
	
	public ItemVenda findById(ItemVendaPK id) {
		Optional<ItemVenda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	// Para criar itemVenda
	public ItemVenda inserir(Venda venda, Produto produto, Integer quantidade) {
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setVenda(venda);
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(quantidade);
		itemVenda.setPrice(produto.getPreco());
		return repository.save(itemVenda);
	}
	
	
	// Para deletar itemVenda
	public void deletar(ItemVendaPK itemVendaPK) {
		repository.deleteById(itemVendaPK);
	}
	

	// Para atualizar itemVenda
	public ItemVenda atualizar(ItemVendaPK itemVendaPK, ItemVenda obj) {
		ItemVenda entity = repository.getReferenceById(itemVendaPK);
		atualizarDados(entity, obj);
		return repository.save(entity);
	}
	
	private void atualizarDados(ItemVenda entity, ItemVenda obj) {
		entity.setQuantidade(obj.getQuantidade());
		entity.setPrice(obj.getPrice());
	}
	
	
		

}
	