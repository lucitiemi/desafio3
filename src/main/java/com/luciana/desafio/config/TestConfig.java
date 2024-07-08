package com.luciana.desafio.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.repositories.ClienteRepository;
import com.luciana.desafio.repositories.VendaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private VendaRepository vendaRepository;

	
	
	@Override
	public void run(String... args) throws Exception {
		
		Cliente cl1 = new Cliente(null, "Maria", "333.444.555.666-77", "maria@gmail.com");
		Cliente cl2 = new Cliente(null, "Alberto", "364.256.789-80", "alberto@gmail.com");
		Cliente cl3 = new Cliente(null, "Ana Carolina", "326.153.248-60", "anacarolina@gmail.com");
		
		clienteRepository.saveAll(Arrays.asList(cl1, cl2, cl3));
		
		//
		
		Venda v1 = new Venda(null, Instant.parse("2024-02-10T19:53:00Z"), cl1);
		Venda v2 = new Venda(null, Instant.parse("2024-03-15T10:14:00Z"), cl2);
		Venda v3 = new Venda(null, Instant.parse("2024-03-20T22:37:00Z"), cl2);
		Venda v4 = new Venda(null, Instant.parse("2024-05-01T18:00:00Z"), cl3);
		
		vendaRepository.saveAll(Arrays.asList(v1, v2, v3, v4));
		
	}
	
	
	
	
}
