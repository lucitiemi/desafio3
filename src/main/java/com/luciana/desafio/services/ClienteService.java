package com.luciana.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.luciana.desafio.dto.ClienteAtualizacaoDTO;
import com.luciana.desafio.dto.CriarClienteAdminDTO;
import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.entities.enums.TipoCliente;
import com.luciana.desafio.repositories.ClienteRepository;
import com.luciana.desafio.services.exceptions.DatabaseException;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
@CacheConfig(cacheNames = "clientes")
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	
	// Para consultar todos os clientes
	@Cacheable(key="#root.methodName")
	public List<Cliente> findAll() {
		return repository.findAll();	
	}
	
	
	// Para consultar cliente pelo id
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	
	// Para criar novo cliente admin
	@CacheEvict(allEntries = true)
	public Cliente criar(CriarClienteAdminDTO dto) {
		Cliente obj = new Cliente();
		obj.setTipoCliente(TipoCliente.ADMIN);
		obj.setNome(dto.nome());
		obj.setCpf(dto.cpf());
		obj.setEmail(dto.email());
		obj.setSenha(dto.senha());
		return repository.save(obj);
	}

	
	// Para deletar cliente 
	@CacheEvict(allEntries = true)
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
	@CacheEvict(allEntries = true)
	public Cliente atualizar(Integer id, ClienteAtualizacaoDTO dto) {
		try {
			Cliente cliente = repository.getReferenceById(id);
			atualizarDados(cliente, dto);
			return repository.save(cliente);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void atualizarDados(Cliente cliente, ClienteAtualizacaoDTO dto) {
		cliente.setNome(dto.nome());
		cliente.setEmail(dto.email());
	}
	
	
	
}
