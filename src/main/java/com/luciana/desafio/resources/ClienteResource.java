package com.luciana.desafio.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luciana.desafio.dto.ClienteAtualizacaoDTO;
import com.luciana.desafio.dto.CriarClienteAdminDTO;
import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	// Para consultar todos os clientes - ADMIN
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		 List<Cliente> list = service.findAll();
		 return ResponseEntity.ok().body(list);
	 }

	// Para consultar cliente pelo id - ADMIN
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
	
	// Para criar novo cliente admin - ADMIN
	@PostMapping(value = "/criar-admin")
	public ResponseEntity<Cliente> criar(@Valid @RequestBody CriarClienteAdminDTO dto) {
		Cliente obj = new Cliente();
		obj = service.criar(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	// Para deletar cliente - ADMIN
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	// Para atualizar um cliente - USER
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @Valid @RequestBody ClienteAtualizacaoDTO dto) {
		Cliente cliente = service.atualizar(id, dto);
		return ResponseEntity.ok().body(cliente);
	}
		
	
	
	

}
