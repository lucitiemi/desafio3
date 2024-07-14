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
import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	// Para consultar todos os clientes
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		 List<Cliente> list = service.findAll();
		 return ResponseEntity.ok().body(list);
	 }

	// Para consultar cliente pelo id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
	
	// Para criar novo cliente
	@PostMapping
	public ResponseEntity<Cliente> criar(@RequestBody Cliente obj) {
		obj = service.criar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	// Para deletar cliente
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Void> deletar(@PathVariable Integer id) {
			service.deletar(id);
			return ResponseEntity.noContent().build();
		}
	
	// Para atualizar um cliente
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @RequestBody ClienteAtualizacaoDTO dto) {
		Cliente cliente = service.atualizar(id, dto);
		return ResponseEntity.ok().body(cliente);
	}
		
	
	
	

}
