package com.luciana.desafio.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Produto implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private Double preco;
	private Integer estoque;
	private Boolean statusProd;
	
	
	// associacoes:
	@OneToMany (mappedBy = "id.produto")
	private Set<ItemVenda> itens = new HashSet<>();
	
	
	// construtores:
	public Produto () {}

	public Produto(Integer id, String descricao, Double preco, Integer estoque, Boolean statusProd) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
		this.statusProd = statusProd;
	}

	
	// getters e setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Boolean getStatusProd() {
		return statusProd;
	}

	public void setStatusProd(Boolean statusProd) {
		this.statusProd = statusProd;
	}
	
	@JsonIgnore
	public Set<Venda> getVendas() {
		Set<Venda> set = new HashSet<>();
		for (ItemVenda x : itens) {
			set.add(x.getVenda());
		}
		return set;
	}

	
	// heshcode e equals
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

	
	
	// to String
	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", preco=" + preco + "]";
	}
	
	
	
	
	
	

}
