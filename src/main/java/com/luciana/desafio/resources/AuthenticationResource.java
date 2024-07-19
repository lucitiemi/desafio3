package com.luciana.desafio.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luciana.desafio.dto.LoginRequestDTO;
import com.luciana.desafio.dto.LoginResponseDTO;
import com.luciana.desafio.dto.RegisterDTO;
import com.luciana.desafio.dto.TokenDatesDTO;
import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.entities.enums.TipoCliente;
import com.luciana.desafio.repositories.ClienteRepository;
import com.luciana.desafio.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResource {
	
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
		
		Cliente cliente = this.repository.findByEmail(dto.email()).orElseThrow(() -> new RuntimeException("User not Found")); // criar excecao personalizada
		
		if(passwordEncoder.matches(dto.senha(), cliente.getPassword())) {
			String token = this.tokenService.generateToken(cliente);
			TokenDatesDTO datesDTO = tokenService.getTokenDates(token);
			return ResponseEntity.ok(new LoginResponseDTO(cliente.getNome(), token, datesDTO.issuedDate(), datesDTO.expirationDate()));
		}
		return ResponseEntity.badRequest().build();
		
	}
		
	@PostMapping(value = "/register")
	public ResponseEntity<LoginResponseDTO> register(@RequestBody @Valid RegisterDTO dto) {
		
		Optional<Cliente> cliente = this.repository.findByEmail(dto.email());
		
		if (cliente.isEmpty()) {
			Cliente novoCliente = new Cliente();
			novoCliente.setTipoCliente(TipoCliente.USER);
			novoCliente.setNome(dto.nome());
			novoCliente.setCpf(dto.cpf());
			novoCliente.setEmail(dto.email());
			novoCliente.setSenha(passwordEncoder.encode(dto.senha()));
			this.repository.save(novoCliente);
			
			String token = this.tokenService.generateToken(novoCliente);						
			TokenDatesDTO datesDTO = tokenService.getTokenDates(token);
			return ResponseEntity.ok(new LoginResponseDTO(novoCliente.getNome(), token, datesDTO.issuedDate(), datesDTO.expirationDate()));

		}
		return ResponseEntity.badRequest().build();
		
		
	}
	
	
}
