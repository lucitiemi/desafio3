package com.luciana.desafio.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;


@Entity
public class Pagamento implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	
	@Id	
	private Integer id;
	private Instant dataPgto;
	
	
	// associacoes:
	@JsonIgnore
	@OneToOne
	@MapsId
	@JoinColumn(name = "venda_id")
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

	
	
	// to String
	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", dataPgto=" + dataPgto + ", venda=" + venda + "]";
	}
	

}
