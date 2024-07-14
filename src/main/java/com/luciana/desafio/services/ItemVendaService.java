package com.luciana.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.luciana.desafio.entities.ItemVenda;
import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.entities.pk.ItemVendaPK;
import com.luciana.desafio.repositories.ItemVendaRepository;
import com.luciana.desafio.services.exceptions.DatabaseException;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
	
	
	// Para deletar itemVenda								so posso deletar se o status estiver pendente
	public void deletar(ItemVendaPK itemVendaPK) {
		try {
			if (repository.existsById(itemVendaPK)) {
				repository.deleteById(itemVendaPK);			
			} else {				
				throw new ResourceNotFoundException(itemVendaPK);			
			}		
		} catch (DataIntegrityViolationException e) {			
			throw new DatabaseException(e.getMessage());		
		}	
	}
	

	// Para atualizar itemVenda
	public ItemVenda atualizar(ItemVendaPK itemVendaPK, ItemVenda obj) {
		try {
			ItemVenda entity = repository.getReferenceById(itemVendaPK);
			atualizarDados(entity, obj);
			return repository.save(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(itemVendaPK);
		}
		
	}
	
		
	private void atualizarDados(ItemVenda entity, ItemVenda obj) {
		entity.setQuantidade(obj.getQuantidade());
		entity.setPrice(obj.getPrice());
	}
	
	
		

}
	