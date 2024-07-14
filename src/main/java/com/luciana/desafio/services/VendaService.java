package com.luciana.desafio.services;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.luciana.desafio.dto.ConsultaDataDTO;
import com.luciana.desafio.dto.ItemVendaAtualizarDTO;
import com.luciana.desafio.dto.ItemVendaDTO;
import com.luciana.desafio.dto.RelatorioDTO;
import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.entities.ItemVenda;
import com.luciana.desafio.entities.Pagamento;
import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.entities.enums.StatusVenda;
import com.luciana.desafio.entities.pk.ItemVendaPK;
import com.luciana.desafio.repositories.VendaRepository;
import com.luciana.desafio.services.exceptions.DatabaseException;
import com.luciana.desafio.services.exceptions.InsufficientStockException;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
	
	@Autowired
	private PagamentoService pagamentoService;
	
	
	// Para consultar todas as vendas
	public List<Venda> findAll() {
		return repository.findAll();
	}
	
	
		
	// Para consultar uma venda
	public Venda findById(Integer id) {
		Optional<Venda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	
	
	// Para listar vendas entre datas
	public List<Venda> consultaEntreDatas(ConsultaDataDTO dto) {
		List<Venda> list = repository.findByDataVendaBetween(dto.getDataInicial(), dto.getDataFinal());
		return list;
	}
	
		
	// Para criar nova venda
	public Venda criar(Integer clienteId) {
		Venda venda = new Venda();
		Instant dataPgto = Instant.now();
		venda.setDataVenda(dataPgto);
		Cliente cliente = clienteService.findById(clienteId);
		venda.setCliente(cliente);
		return repository.save(venda);
	}
	
	
	// Para inserir item na venda
	public Venda inserirItem(Integer vendaId, ItemVendaDTO dto) {
		Venda venda = findById(vendaId);
		Produto produto = produtoService.findById(dto.produtoId());
		
		if (produto.getEstoque() >= dto.quantidade()) {
			ItemVenda item = itemVendaService.inserir(venda, produto, dto.quantidade());
			venda.getItens().add(item);
			return repository.save(venda);
		}
		else {
			throw new InsufficientStockException(dto.produtoId());
		}
		
	}
	
	
	// Para retirar item na venda											// so pode deletar se o status estiver PENDENTE!
	public Venda retirarItemVenda(Integer vendaId, Integer produtoId) {
		
		Venda venda = findById(vendaId);
		Produto produto = produtoService.findById(produtoId);
		
		ItemVendaPK itemVendaPK = new ItemVendaPK();
        itemVendaPK.setVenda(venda);
        itemVendaPK.setProduto(produto); 
               
        itemVendaService.deletar(itemVendaPK);
        
		return repository.save(venda);	

	}
	
	 
	// Para atualizar itemVenda												// so pode deletar se o status estiver PENDENTE!
	public Venda atualizarItemVenda(Integer vendaId, Integer produtoId, ItemVendaAtualizarDTO dto) {
		Venda venda = findById(vendaId);
		Produto produto = produtoService.findById(produtoId);
		
		ItemVendaPK itemVendaPK = new ItemVendaPK();
        itemVendaPK.setVenda(venda);
        itemVendaPK.setProduto(produto); 
               
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setQuantidade(dto.quantidade());
        itemVenda.setPrice(dto.price());
        
        itemVendaService.atualizar(itemVendaPK, itemVenda);
        
		return repository.save(venda);	
	}
		
	
	// Para deletar venda
	public void deletar(Integer id) {
		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);			
			} else {				
				throw new ResourceNotFoundException(id);			
			}		
		} catch (DataIntegrityViolationException e) {			
			throw new DatabaseException(e.getMessage());		
		}	
	}
	
	

	// Para atualizar um venda
	public Venda atualizar(Integer id, Venda obj) {
		try {
			Venda entity = repository.getReferenceById(id);
			atualizarDados(entity, obj);
			return repository.save(entity);		
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}
	
	private void atualizarDados(Venda entity, Venda obj) {
		entity.setDataVenda(obj.getDataVenda());
		entity.setStatusVenda(obj.getStatusVenda());
	}
	
	
	// Para cancelar venda
	public Venda cancelarVenda(Integer id) {
		Venda entity = repository.getReferenceById(id);
		entity.setStatusVenda(StatusVenda.CANCELADA);
		return repository.save(entity);		
	}
	
	
	// Para gerar pagamento da venda
	public Venda pagar(Integer vendaId) {
		Venda venda = repository.getReferenceById(vendaId);
		Instant dataPgto = Instant.now();
		Pagamento pagamento = pagamentoService.criar(venda, dataPgto);
		venda.setPagamento(pagamento);
		venda.setStatusVenda(StatusVenda.FECHADA);
		return repository.save(venda);
	}
	
	
	// Para gerar relatorio mensal
	public RelatorioDTO relatorioMensal(Integer mes, Integer ano) {
		
		YearMonth mesRelat = YearMonth.of(ano, mes);		
		
		LocalDateTime dataInicialLocal = mesRelat.atDay(1).atStartOfDay();
		LocalDateTime dataFinalLocal = mesRelat.atEndOfMonth().atStartOfDay();
		
		Instant dataInicial = dataInicialLocal.toInstant(ZoneOffset.UTC);
		Instant dataFinal = dataFinalLocal.toInstant(ZoneOffset.UTC);
		
		RelatorioDTO dto = gerarRelatorio(dataInicial, dataFinal);
		
		return dto;
	}


	// Para gerar relatorio semanal
	public RelatorioDTO relatorioSemanal(Integer ano, Integer mes, Integer dia) {
		
		LocalDateTime dataConsulta = LocalDateTime.of(ano, mes, dia, 0, 0);
		
		DayOfWeek diaDaSemana = dataConsulta.getDayOfWeek();
		
		LocalDateTime dataInicialLocal = dataConsulta.minusDays(diaDaSemana.getValue() - (diaDaSemana.getValue()-1));
		LocalDateTime dataFinalLocal = dataInicialLocal.plusDays(6);
		
		Instant dataInicial = dataInicialLocal.toInstant(ZoneOffset.UTC);
		Instant dataFinal = dataFinalLocal.toInstant(ZoneOffset.UTC);
		
		RelatorioDTO dto = gerarRelatorio(dataInicial, dataFinal);
		
		return dto;
	}
	
	
	// Para gerar relatorio
	public RelatorioDTO gerarRelatorio(Instant dataInicial, Instant dataFinal) {
		
		List<Venda> lista = new ArrayList<>();
		ConsultaDataDTO dto = new ConsultaDataDTO();

		dto.setDataInicial(dataInicial);
		dto.setDataFinal(dataFinal);
		
		lista = consultaEntreDatas(dto);

		Integer qtdeTotalVendas=0, qtdeTotalVendasFechadas=0, qtdeTotalVendasPendentes=0, qtdeTotalVendasCanceladas=0;
		Double valorTotalVendas=0.0, valorTotalVendasFechadas=0.0, valorTotalVendasPendentes=0.0, valorTotalVendasCanceladas=0.0;
		
		for (Venda venda : lista) {
			 qtdeTotalVendas++;
			 valorTotalVendas += venda.getTotal();
			 
			 if (venda.getStatusVenda() == StatusVenda.FECHADA) {
				 qtdeTotalVendasFechadas++;
				 valorTotalVendasFechadas += venda.getTotal();
			 }
			 else if (venda.getStatusVenda() == StatusVenda.PENDENTE) {
				 qtdeTotalVendasPendentes++;
				 valorTotalVendasPendentes += venda.getTotal();
			 }
			 if (venda.getStatusVenda() == StatusVenda.CANCELADA) {
				 qtdeTotalVendasCanceladas++;
				 valorTotalVendasCanceladas += venda.getTotal();
			 }
		}
		
		RelatorioDTO relatorio = new RelatorioDTO(qtdeTotalVendas, valorTotalVendas, 
				qtdeTotalVendasFechadas, valorTotalVendasFechadas, 
				qtdeTotalVendasPendentes, valorTotalVendasPendentes, 
				qtdeTotalVendasCanceladas, valorTotalVendasCanceladas);
		
		return relatorio;
	}

	
	 
	
}
