package com.luciana.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.repositories.ProdutoRepository;
import com.luciana.desafio.services.exceptions.ObjectNotFoundException;

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
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	
	
	// Para inserir produto
	public Produto inserir(Produto obj) {
		return repository.save(obj);
	}
	
	
	
	// Para deletar produto
	public void deletar(Integer id) {
		repository.deleteById(id);
	}
	
	
	
	// Para atualizar um produto
	public Produto atualizar(Integer id, Produto obj) {
		Produto entity = repository.getReferenceById(id);
		atualizarDados(entity, obj);
		return repository.save(entity);		
	}

	private void atualizarDados(Produto entity, Produto obj) {
		entity.setDescricao(obj.getDescricao());
		entity.setPreco(obj.getPreco());
		entity.setStatusProd(obj.getStatusProd());
	}
	
	
	
}
