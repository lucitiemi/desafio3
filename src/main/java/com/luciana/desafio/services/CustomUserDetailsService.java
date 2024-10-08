package com.luciana.desafio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.repositories.ClienteRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private ClienteRepository repository;

	
	// metodo para transformar um Cliente em um UserDetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));		// aqui tem que ser essa excecao especifica
		return user;
	}

}
