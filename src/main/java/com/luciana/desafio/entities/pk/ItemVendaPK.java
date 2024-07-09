package com.luciana.desafio.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.entities.Venda;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class ItemVendaPK implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="id_venda")
	private Venda venda;
	
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	
	
	// getters e setters
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
	// hashcode e equals
	@Override
	public int hashCode() {
		return Objects.hash(produto, venda);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVendaPK other = (ItemVendaPK) obj;
		return Objects.equals(produto, other.produto) && Objects.equals(venda, other.venda);
	}
	
	
	
	

}
