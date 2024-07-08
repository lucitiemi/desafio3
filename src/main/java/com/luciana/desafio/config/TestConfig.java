package com.luciana.desafio.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.repositories.ClienteRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	
	
	@Override
	public void run(String... args) throws Exception {
		
		Cliente cl1 = new Cliente(null, "Maria", "333.444.555.666-77", "maria@gmail.com");
		Cliente cl2 = new Cliente(null, "Alberto", "364.256.789-80", "alberto@gmail.com");
		Cliente cl3 = new Cliente(null, "Ana Carolina", "326.153.248-60", "anacarolina@gmai.com");
		
		clienteRepository.saveAll(Arrays.asList(cl1, cl2, cl3));
		
		
	}
	
	
	
	
}
