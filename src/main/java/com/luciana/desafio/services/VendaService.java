package com.luciana.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.repositories.VendaRepository;
import com.luciana.desafio.services.exceptions.ObjectNotFoundException;

@Service
public class VendaService {

	@Autowired
	private VendaRepository repository;
	
	public List<Venda> findAll() {
		return repository.findAll();
		
	}
	
	public Venda findById(Integer id) {
		Optional<Venda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: " + Venda.class.getName()));
	}
}
