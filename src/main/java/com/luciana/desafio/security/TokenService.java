package com.luciana.desafio.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.luciana.desafio.entities.Cliente;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	
	// Para gerar o token
	public String generateToken(Cliente cliente) {		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("auth-api")
					.withSubject(cliente.getEmail())
					.withIssuedAt(Instant.now())
					.withExpiresAt(this.genExpirationDate())
					.sign(algorithm);
			return token;
		} 
		catch (JWTCreationException e) {
			throw new RuntimeException("Error while generating token", e);				// criar classe personalizada de excecao!
		}
	}
	
	
	// Para validar o token
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)		
					.withIssuer("auth-api")
					.build()					// cria o objeto para fazer a verificacao
					.verify(token)				// verifica de fato o token
					.getSubject();				// pega o email
		} catch (JWTVerificationException e) {
			return null;
		}
	}
	
	
	// Para gerar data de expiracao do token
	private Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
	
	

}
