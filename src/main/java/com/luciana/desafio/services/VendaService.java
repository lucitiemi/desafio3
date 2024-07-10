package com.luciana.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciana.desafio.dto.ItemVendaDTO;
import com.luciana.desafio.dto.VendaDTO;
import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.entities.ItemVenda;
import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.repositories.VendaRepository;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;

@Service
public class VendaService {

	@Autowired
	private VendaRepository repository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemVendaService itemVendaService;
	
	
	// Para consultar venda
	public List<Venda> findAll() {
		return repository.findAll();
	}
	
	public Venda findById(Integer id) {
		Optional<Venda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	
	// Para inserir venda
	public Venda inserir(VendaDTO obj) {
		Venda venda = new Venda();
		venda.setDataVenda(obj.dataVenda());
		Cliente cliente = clienteService.findById(obj.clienteId());
		venda.setCliente(cliente);
		return repository.save(venda);
	}
	
	
	// Para inserir item na venda
	public Venda inserirItem(ItemVendaDTO dto) {
		Venda venda = findById(dto.vendaId());
		Produto produto = produtoService.findById(dto.produtoId());
		ItemVenda item = itemVendaService.inserir(venda, produto, dto.quantidade());
		venda.getItens().add(item);
		return repository.save(venda);
	}
	
	
	/*
	// Para deletar venda
	public void deletar(Integer id) {
		repository.deleteById(id);
	}
	
	
	
	// Para atualizar um venda
	public Venda atualizar(Integer id, Venda obj) {
		Venda entity = repository.getReferenceById(id);
		atualizarDados(entity, obj);
		return repository.save(entity);		
	}

	private void atualizarDados(Venda entity, Venda obj) {
		entity.setCliente(obj.getCliente()); 
		entity.setDataVenda(obj.getDataVenda());
		entity.setStatusVenda(obj.getStatusVenda());
	}
	
	*/
	
	
}
