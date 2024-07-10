package com.luciana.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.repositories.ProdutoRepository;
import com.luciana.desafio.services.exceptions.DatabaseException;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	
	// Para consultar produto
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	public Produto findById(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	
	// Para inserir produto
	public Produto inserir(Produto obj) {
		return repository.save(obj);
	}
	
	
	
	// Para deletar produto 
	public void deletar(Integer id) {
		
		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);			
			} else {				
				throw new ResourceNotFoundException(id);			
			}		
		} catch (DataIntegrityViolationException e) {			
			throw new DatabaseException(e.getMessage());		
		}		
	}
	
	
	
	
	// Para atualizar produto
	public Produto atualizar(Integer id, Produto obj) {
		try {
			Produto entity = repository.getReferenceById(id);
			atualizarDados(entity, obj);
			return repository.save(entity);	
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);	
		}
	}

	private void atualizarDados(Produto entity, Produto obj) {
		entity.setDescricao(obj.getDescricao());
		entity.setPreco(obj.getPreco());
		entity.setEstoque(obj.getEstoque());
		entity.setStatusProd(obj.getStatusProd());
	}
	
	
	
}
