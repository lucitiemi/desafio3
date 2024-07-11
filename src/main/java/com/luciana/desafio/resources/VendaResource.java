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

import com.luciana.desafio.dto.ItemVendaAtualizarDTO;
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
	
	
	// Para criar nova venda
	@PostMapping
	public ResponseEntity<Venda> inserir(@RequestBody VendaDTO obj) {
		Venda venda = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(venda.getId()).toUri();
		return ResponseEntity.created(uri).body(venda);
	}
	
	// Para inserir item na venda
	@PostMapping(value="/inserir-item")
	public ResponseEntity<Venda> inserirItem(@RequestBody ItemVendaDTO obj) {
		return ResponseEntity.ok().body(service.inserirItem(obj));
	}
	
	
	// Para retirar item na venda
	@DeleteMapping(value="/{vendaId}/item/{produtoId}")
    public ResponseEntity<Venda> retirarItemVenda(@PathVariable Integer vendaId, @PathVariable Integer produtoId) {
		Venda venda = service.retirarItemVenda(vendaId, produtoId);
		return ResponseEntity.ok().body(venda);
    }
	
	
	// Para atualizar itemVenda	
	@PutMapping(value = "/{vendaId}/item/{produtoId}")
	public ResponseEntity<Venda> atualizar(@PathVariable Integer vendaId, @PathVariable Integer produtoId, @RequestBody ItemVendaAtualizarDTO obj) {
		Venda venda = service.atualizarItemVenda(vendaId, produtoId, obj);
		return ResponseEntity.ok().body(venda);
	}
	
		
	// Para deletar venda
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	

	
	// Para atualizar um venda (somente data e status)
	@PutMapping(value = "/{id}")
	public ResponseEntity<Venda> atualizar(@PathVariable Integer id, @RequestBody Venda obj) {
		obj = service.atualizar(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
	// Para cancelar venda
	@PutMapping(value = "/cancelar/{id}")
	public ResponseEntity<Venda> cancelar(@PathVariable Integer id) {
		Venda venda = service.cancelarVenda(id);
		return ResponseEntity.ok().body(venda);
	}
	

}
