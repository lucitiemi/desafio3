package com.luciana.desafio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration							// indica que eh uma classe de configuracao
@EnableWebSecurity						// indica que essa classe que vai cuidar das configuracoes de seguranca
public class SecurityConfigurations {
	

	
	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable())																		// desativa a essa configuracao padrao
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))		// configura para o tipo autenticacao para STATELESS
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/h2-console/**").permitAll()	
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()				
						.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()	
						
						.requestMatchers(HttpMethod.PUT, "/clientes/{clienteId}/solicitar-alteracao-senha").hasRole("USER")
						.requestMatchers(HttpMethod.PUT, "/clientes/{clienteId}/alterar-senha").hasRole("USER")
						.requestMatchers(HttpMethod.GET, "/clientes/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/clientes/criar-admin").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/clientes/**").hasRole("ADMIN")
						
						.requestMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/produtos/inativos").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/produtos/{id}").hasRole("USER")
						.requestMatchers(HttpMethod.GET, "/produtos/ativos").hasRole("USER")
						.requestMatchers(HttpMethod.POST, "/produtos/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/produtos/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/produtos/**").hasRole("ADMIN")
						
						.requestMatchers(HttpMethod.DELETE, "/vendas/{id}").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/vendas/{id}").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/vendas/relatorio-mensal/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/vendas/relatorio-semanal/**").hasRole("ADMIN")
						
						.requestMatchers(HttpMethod.GET, "/caches/**").permitAll()
			
						.anyRequest().authenticated()
				)
				.headers(headers -> headers
	                    .frameOptions(frameOptions -> frameOptions.sameOrigin())  					// permite frames de mesma origem para o console do H2
	                )
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)		// faz a autenticacao do usuario antes dos filtros de permissoes
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
