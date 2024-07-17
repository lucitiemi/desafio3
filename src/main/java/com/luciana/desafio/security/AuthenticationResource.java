package com.luciana.desafio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luciana.desafio.dto.RegisterDTO;
import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.repositories.ClienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	TokenService tokenService;

	@PostMapping(value = "/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((Cliente)auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));	
	}
	
	
	@PostMapping(value = "/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO dto) {
		if(this.repository.findByEmail(dto.email()) != null)
			return ResponseEntity.badRequest().build();

		String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());
		Cliente novoCliente = new Cliente(null, dto.role(), dto.nome(), dto.cpf(), dto.email(), dto.senha());
		
		this.repository.save(novoCliente);
		
		return ResponseEntity.ok().build();
	}
	
	
}
