package com.luciana.desafio.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luciana.desafio.entities.pk.ItemVendaPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class ItemVenda implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemVendaPK id = new ItemVendaPK();
	
	private Integer quantidade;
	private Double price;
	
	
	// contrutores:
	public ItemVenda() {}

	public ItemVenda(Venda venda, Produto produto, Integer quantidade, Double price) {
		super();
		id.setVenda(venda);
		id.setProduto(produto);
		this.quantidade = quantidade;
		this.price = price;
	}

	
	// getters e setters:
	@JsonIgnore
	public Venda getVenda() {
		return id.getVenda();
	}
	
	public void setVenda(Venda venda) {
		id.setVenda(venda);
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
		
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		ItemVenda other = (ItemVenda) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	

}
