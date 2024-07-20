package com.luciana.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.repositories.ProdutoRepository;
import com.luciana.desafio.services.exceptions.DatabaseException;
import com.luciana.desafio.services.exceptions.InsufficientStockException;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
@CacheConfig(cacheNames = "produtos")
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	
	// Para consultar todos os produto
	@Cacheable(key="#root.methodName")
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	// Para consultar produto pelo id
	public Produto findById(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	// Para consultar todos os produto ativos
	@Cacheable(key="#root.methodName")
	public List<Produto> findProdAtivos() {
		return repository.findByStatusProdTrue();
	}

	// Para consultar todos os produto inativos
		public List<Produto> findProdInativos() {
			return repository.findByStatusProdFalse();
		}
	
	
	// Para inserir produto
	@CacheEvict(allEntries = true)
	public Produto inserir(Produto obj) {
		return repository.save(obj);
	}
	
	
	
	// Para deletar produto 
	@CacheEvict(allEntries = true)
	public void deletar(Integer id) {
		
		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);			
			} else {				
				throw new ResourceNotFoundException(id);			
			}		
		} catch (DataIntegrityViolationException e) {			
			throw new DatabaseException("Produtos ja inseridos em alguma venda não podem ser deletados");		
		}		
	}
	
		
	// Para atualizar produto
	@CacheEvict(allEntries = true)
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
	
	
	// Para inativar produto
	@CacheEvict(allEntries = true)
	public Produto inativarProduto(Integer id) {
		try {
			Produto entity = repository.getReferenceById(id);
			entity.setStatusProd(false);
			return repository.save(entity);	
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);	
		}
	}
	
	
	// Para diminuir estoque
	@CacheEvict(allEntries = true)
	public Produto diminuirEstoque(Integer id, Integer qtde) {
		try {
			Produto entity = repository.getReferenceById(id);
			if (entity.getEstoque() >= qtde) {
				entity.setEstoque(entity.getEstoque() - qtde);
				return repository.save(entity);	
			}
			else {
				throw new InsufficientStockException(id);
			}
			
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);	
		}
	}
	
	// Para aumentar estoque
	@CacheEvict(allEntries = true)
		public Produto aumentarEstoque(Integer id, Integer qtde) {
			try {
				System.out.println("2");
				Produto entity = repository.getReferenceById(id);
				System.out.println("estoque=" + entity.getEstoque());
				System.out.println("qtde=" + qtde);
				entity.setEstoque(entity.getEstoque() + qtde);
				System.out.println("estoque=" + entity.getEstoque());
				System.out.println("qtde=" + qtde);
				return repository.save(entity);	
			}
			catch (EntityNotFoundException e) {
				throw new ResourceNotFoundException(id);	
			}
		}

	
}
