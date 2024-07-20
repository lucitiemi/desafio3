package com.luciana.desafio.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luciana.desafio.entities.pk.ItemVendaPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class ItemVenda implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemVendaPK id = new ItemVendaPK();
	
	@PositiveOrZero(message = "Quantidade deve ser um numero positivo")
	@NotNull(message = "Quantidade nao pode ser nulo")
	private Integer quantidade;
	
	@PositiveOrZero(message = "Preco deve ser um numero positivo")
	@NotNull(message = "Preco nao pode ser nulo")
	private Double preco;
	
	
	// construtores:
	public ItemVenda() {}

	public ItemVenda(Venda venda, Produto produto, Integer quantidade, Double preco) {
		super();
		id.setVenda(venda);
		id.setProduto(produto);
		this.quantidade = quantidade;
		this.preco = preco;
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
		return preco;
	}

	public void setPrice(Double preco) {
		this.preco = preco;
	}

	
	
	// metodos:
	public Double getSubTotal() {
		return preco * quantidade;
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

	
	// to String
	@Override
	public String toString() {
		return "ItemVenda [idProd=" + id.getProduto().getId() + ", descricao=" + id.getProduto().getDescricao() + 
				", quantidade=" + quantidade + ", preco=" + preco + 
				", idVenda=" + id.getVenda().getId() + "]";
	}
	
	
	
	
	

}
