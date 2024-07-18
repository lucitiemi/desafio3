package com.luciana.desafio.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luciana.desafio.dto.RegisterDTO;
import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.entities.enums.TipoCliente;
import com.luciana.desafio.repositories.ClienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResource {
	
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO dto) {
		
		Cliente cliente = this.repository.findByEmail(dto.email()).orElseThrow(() -> new RuntimeException("User not Found")); // criar excecao personalizada
		
		if(passwordEncoder.matches(dto.senha(), cliente.getPassword())) {
			String token = this.tokenService.generateToken(cliente);
			return ResponseEntity.ok(new LoginResponseDTO(cliente.getNome(), token));
		}
		return ResponseEntity.badRequest().build();
		
		/*
		var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((Cliente)auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));	
		*/
	}
	
	
	@PostMapping(value = "/register")
	public ResponseEntity<LoginResponseDTO> register(@RequestBody @Valid RegisterDTO dto) {
		
		Optional<Cliente> cliente = this.repository.findByEmail(dto.email());
		
		if (cliente.isEmpty()) {
			Cliente novoCliente = new Cliente();
			novoCliente.setTipoCliente(TipoCliente.USUARIO);
			novoCliente.setNome(dto.nome());
			novoCliente.setCpf(dto.cpf());
			novoCliente.setEmail(dto.email());
			novoCliente.setSenha(passwordEncoder.encode(dto.senha()));
			this.repository.save(novoCliente);
			
			String token = this.tokenService.generateToken(novoCliente);
			return ResponseEntity.ok(new LoginResponseDTO(novoCliente.getNome(), token));

		}
		return ResponseEntity.badRequest().build();
		
		
		/*
		if (this.repository.findByEmail(dto.email()) != null)
			return ResponseEntity.badRequest().build();

		String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());
		Cliente novoCliente = new Cliente(null, dto.role(), dto.nome(), dto.cpf(), dto.email(), dto.senha());
		
		this.repository.save(novoCliente);
		
		return ResponseEntity.ok().build();
		*/
	}
	
	
}
