package com.luciana.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.luciana.desafio.dto.ClienteAtualizacaoDTO;
import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.repositories.ClienteRepository;
import com.luciana.desafio.services.exceptions.DatabaseException;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	
	// Para consultar todos os clientes
	public List<Cliente> findAll() {
		return repository.findAll();	
	}
	
	
	// Para consultar cliente pelo id
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	
	// Para criar novo cliente
	public Cliente criar(Cliente obj) {
		return repository.save(obj);
	}
	
	
	// Para deletar cliente 
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

		
	// Para atualizar cliente
	public Cliente atualizar(Integer id, ClienteAtualizacaoDTO dto) {
		Cliente cliente = repository.getReferenceById(id);
		atualizarDados(cliente, dto);
		return repository.save(cliente);
	}

	private void atualizarDados(Cliente cliente, ClienteAtualizacaoDTO dto) {
		cliente.setNome(dto.nome());
		cliente.setEmail(dto.email());
	}
	
	
	
}
