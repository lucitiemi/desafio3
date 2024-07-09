package com.luciana.desafio.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;


@Entity
public class Pagamento implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Instant dataPgto;
	
	
	// associacoes:
	@OneToOne
	@MapsId
	private Venda venda;
	
	
	
	// construtores:
	public Pagamento() {}


	public Pagamento(Integer id, Instant dataPgto, Venda venda) {
		super();
		this.id = id;
		this.dataPgto = dataPgto;
		this.venda = venda;
	}


	
	
	// getters e setters
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Instant getDataPgto() {
		return dataPgto;
	}


	public void setDataPgto(Instant dataPgto) {
		this.dataPgto = dataPgto;
	}


	public Venda getVenda() {
		return venda;
	}


	public void setVenda(Venda venda) {
		this.venda = venda;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
