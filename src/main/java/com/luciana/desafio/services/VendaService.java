package com.luciana.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.repositories.VendaRepository;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;

@Service
public class VendaService {

	@Autowired
	private VendaRepository repository;
	
	
	// Para consultar venda
	public List<Venda> findAll() {
		return repository.findAll();
	}
	
	public Venda findById(Integer id) {
		Optional<Venda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	
	// Para inserir venda
	public Venda inserir(Venda obj) {
		return repository.save(obj);
	}
	
	
	/*
	// Para deletar venda
	public void deletar(Integer id) {
		repository.deleteById(id);
	}
	
	
	
	// Para atualizar um venda
	public Venda atualizar(Integer id, Venda obj) {
		Venda entity = repository.getReferenceById(id);
		atualizarDados(entity, obj);
		return repository.save(entity);		
	}

	private void atualizarDados(Venda entity, Venda obj) {
		entity.setCliente(obj.getCliente()); 
		entity.setDataVenda(obj.getDataVenda());
		entity.setStatusVenda(obj.getStatusVenda());
	}
	
	*/
	
	
}
