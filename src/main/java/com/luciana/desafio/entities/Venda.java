package com.luciana.desafio.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luciana.desafio.entities.enums.StatusVenda;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;



@Entity
public class Venda implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataVenda;
	
	@NotNull(message = "Status da Venda nao pode ser nulo")
	private StatusVenda statusVenda;
	
	
	
	// associacoes:
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	@OneToMany (mappedBy = "id.venda")
	private Set<ItemVenda> itens = new HashSet<>();
		
	@OneToOne (mappedBy = "venda", cascade = CascadeType.ALL)
	private Pagamento pagamento;
	
	
	

	// construtores:
	public Venda() {
		this.statusVenda = StatusVenda.PENDENTE;
		
	}
	
	public Venda(Integer id, Instant dataVenda, Cliente cliente) {
		super();
		this.id = id;
		this.dataVenda = dataVenda;
		this.statusVenda = StatusVenda.PENDENTE;
		this.cliente = cliente;
	}
	
	public Venda(Integer id, Instant dataVenda, StatusVenda statusVenda, Cliente cliente) {
		super();
		this.id = id;
		this.dataVenda = dataVenda;
		this.statusVenda = statusVenda;
		this.cliente = cliente;
	}

	
	
	// getters e setters:
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Instant dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	public StatusVenda getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(StatusVenda statusVenda) {
		if (statusVenda != null) {
			this.statusVenda = statusVenda;
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Set<ItemVenda> getItens() {
		return itens;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	
	
	// metodos:
	public Double getTotal() {
		double soma = 0.0;
		for (ItemVenda x : itens) {
			soma += x.getSubTotal();
		}
		return soma;		
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
		Venda other = (Venda) obj;
		return Objects.equals(id, other.id);
	}

	
	

	
	
	

}
