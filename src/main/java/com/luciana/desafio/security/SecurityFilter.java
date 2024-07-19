package com.luciana.desafio.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.repositories.ClienteRepository;
import com.luciana.desafio.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var token = this.recoverToken(request);																					// recupera o token da requisicao
						
		if(token != null) {
			var email = tokenService.validateToken(token);																		// valida o token (o metodo ja retorna o email)
			Cliente user = clienteRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found")); 		// como o user ja tem um token, dificilmente cairá nessa excecao
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			//System.out.println(email);
			//System.out.println(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);												// salva no contexto a autenticacao do token
		}
		filterChain.doFilter(request, response);
	}
	

	// metodo auxiliar para pegar o token da requisicao
	private String recoverToken(HttpServletRequest request) {		
		var authHeader = request.getHeader("Authorization");		// onde, na requisicao, sendo enviado o token
		//System.out.println(authHeader);
		
		if(authHeader == null) {
			return null;
		}
		return authHeader.replace("Bearer ", "");
	}
	


}
