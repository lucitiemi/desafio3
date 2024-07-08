package com.luciana.desafio.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luciana.desafio.entities.Cliente;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@GetMapping
	public ResponseEntity<Cliente> findAll() {
		Cliente cl = new Cliente(1, "Maria", "336.352.351-60", "maria@gmail.com");
		return ResponseEntity.ok().body(cl);
	}
	

}
