package com.luciana.desafio.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luciana.desafio.dto.ItemVendaDTO;
import com.luciana.desafio.dto.VendaDTO;
import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.services.VendaService;

@RestController
@RequestMapping(value = "/vendas")
public class VendaResource {
	
	@Autowired
	private VendaService service;
	
	
	// Para consultar venda
	@GetMapping
	public ResponseEntity<List<Venda>> findAll() {
		 List<Venda> list = service.findAll();
		 return ResponseEntity.ok().body(list);
	 }

	@GetMapping(value = "/{id}")
	public ResponseEntity<Venda> find(@PathVariable Integer id) {
		
		Venda obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
	
	
	// Para inserir venda
	@PostMapping
	public ResponseEntity<Venda> inserir(@RequestBody VendaDTO obj) {
		Venda venda = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(venda.getId()).toUri();
		return ResponseEntity.created(uri).body(venda);
	}
	
	@PostMapping(value="/inserir-item")
	public ResponseEntity<Venda> inserirItem(@RequestBody ItemVendaDTO obj) {
		return ResponseEntity.ok().body(service.inserirItem(obj));
	}
	
	
	/*
	// Para deletar venda
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	// Para atualizar um venda
	@PutMapping(value = "/{id}")
	public ResponseEntity<Venda> atualizar(@PathVariable Integer id, @RequestBody Venda obj) {
		obj = service.atualizar(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
	// cancelar venda
	 * 
	 * 
	*/
	

}
