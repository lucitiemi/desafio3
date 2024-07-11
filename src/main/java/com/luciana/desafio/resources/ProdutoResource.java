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

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	
	// Para consultar produto
	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		 List<Produto> list = service.findAll();
		 return ResponseEntity.ok().body(list);
	 }

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		
		Produto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	
	// Para inserir produto
	@PostMapping
	public ResponseEntity<Produto> inserir(@RequestBody Produto obj) {
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	
	
	// Para deletar produto
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	// Para atualizar um produto
	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Integer id, @RequestBody Produto obj) {
		obj = service.atualizar(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
	// Para inativar um produto
		@PutMapping(value = "/inativar/{id}")
		public ResponseEntity<Produto> inativar(@PathVariable Integer id) {
			Produto produto = service.inativarProduto(id);
			return ResponseEntity.ok().body(produto);
		}
	
	

}
