package com.luciana.desafio.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.repositories.ClienteRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	ClienteRepository clienteRepository;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var token = this.recoverToken(request);						// recupera o token da requisicao
		var email = tokenService.validateToken(token);				// valida o token
		
		if(token != null) {
			Cliente user = clienteRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
			//var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	        //var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
			var authentication = new UsernamePasswordAuthenticationToken(user, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {		// esse metodo pega o token da requisicao
		var authHeader = request.getHeader("Authorization");		// onde, na requisicao, esta o token
		if(authHeader == null) {
			return null;
		}
		return authHeader.replace("Bearer ", "");
	}
	


}
