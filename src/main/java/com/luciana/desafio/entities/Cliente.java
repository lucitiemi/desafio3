package com.luciana.desafio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luciana.desafio.entities.enums.TipoCliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class Cliente implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Tipo de Cliente nao pode ser nulo")		
	private TipoCliente tipoCliente;							
		
	@NotBlank(message = "Nome nao pode ser vazio")
	private String nome;
	
	@CPF(message = "O CPF deve ser valido")				
	@NotBlank(message = "CPF nao pode ser vazio")
	private String cpf;
	
	@Email(message = "O e-mail deve ser valido")		
	@NotBlank(message = "E-mail nao pode ser vazio")
	private String email;
	
	@NotBlank(message = "Senha nao pode ser vazia")
	private String senha;
	
	private String tokenResetSenha;

	
	// associacoes:
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Venda> vendas = new ArrayList<>();
	

	// construtores:	
	public Cliente() {}

	public Cliente(Integer id, TipoCliente tipoCliente, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.tipoCliente = tipoCliente;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.tokenResetSenha = null;
	}

	
	// getters e setters:
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Venda> getVendas() {
		return vendas;
	}
	
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getTokenResetSenha() {
		return tokenResetSenha;
	}

	public void setTokenResetSenha(String tokenResetSenha) {
		this.tokenResetSenha = tokenResetSenha;
	}


	
	
	// hashcode e equals
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {				// determina as autorizacoes de forma acumulativa
		if (this.tipoCliente == TipoCliente.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));		 
		}
		else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
	}

	@Override
	public String getPassword() {						
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	
	
	
	
}
