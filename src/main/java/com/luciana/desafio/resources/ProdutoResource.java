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

import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.services.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	
	// Para consultar todos os produto - USER
	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		 List<Produto> list = service.findAll();
		 return ResponseEntity.ok().body(list);
	 }
	
	
	// Para consultar produto pelo id - USER
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// Para consultar todos os produto ativos - USER
	@GetMapping(value = "/ativos")
	public ResponseEntity<List<Produto>> findProdAtivos() {
		 List<Produto> list = service.findProdAtivos();
		 return ResponseEntity.ok().body(list);
	 }
	
	// Para consultar todos os produto inativos - ADMIN
	@GetMapping(value = "/inativos")
	public ResponseEntity<List<Produto>> findProdInativos() {
		 List<Produto> list = service.findProdInativos();
		 return ResponseEntity.ok().body(list);
	 }

	// Para inserir produto - ADMIN
	@PostMapping
	public ResponseEntity<Produto> inserir(@Valid @RequestBody Produto obj) {
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	

	// Para deletar produto - ADMIN
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
		
	
	// Para atualizar um produto - ADMIN
	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Integer id, @Valid @RequestBody Produto obj) {
		obj = service.atualizar(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
	// Para inativar um produto - ADMIN
	@PutMapping(value = "/{id}/inativar")
	public ResponseEntity<Produto> inativar(@PathVariable Integer id) {
		Produto produto = service.inativarProduto(id);
		return ResponseEntity.ok().body(produto);
	}
	
	

}
