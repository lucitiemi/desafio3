package com.luciana.desafio.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciana.desafio.entities.Pagamento;
import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.repositories.PagamentoRepository;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;
	
	// Para consultar pagamento por id
	public List<Pagamento> findAll() {
		return repository.findAll();
		
	}
	
	// Para listar todos pagamento
	public Pagamento findById(Integer id) {
		Optional<Pagamento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	// Para criar um novo pagamento
	public Pagamento criar(Venda venda, Instant dataPgto) {
		Pagamento pagamento = new Pagamento();
		pagamento.setDataPgto(dataPgto);
		pagamento.setVenda(venda);
		return repository.save(pagamento);
	}
}





