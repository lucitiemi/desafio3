package com.luciana.desafio.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luciana.desafio.dto.ConsultaDataDTO;
import com.luciana.desafio.dto.ItemVendaDTO;
import com.luciana.desafio.dto.RelatorioDTO;
import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.services.VendaService;

@RestController
@RequestMapping(value = "/vendas")
public class VendaResource {
	
	@Autowired
	private VendaService service;
	
	
	// Para consultar todas as vendas - USER
	@GetMapping
	public ResponseEntity<List<Venda>> findAll() {
		 List<Venda> list = service.findAll();
		 return ResponseEntity.ok().body(list);
	 }

	
	// Para consultar uma venda por id - USER
	@GetMapping(value = "/{id}")
	public ResponseEntity<Venda> find(@PathVariable Integer id) {
		Venda obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
	
		
	// Para listar vendas entre datas - USER
	@PostMapping(value = "/consulta-entre-datas")
	public ResponseEntity<List<Venda>> consultaEntreDatas(@RequestBody ConsultaDataDTO dto) {
		List<Venda> list = service.consultaEntreDatas(dto);
		return ResponseEntity.ok().body(list);
	 }
	
		
	// Para criar nova venda - USER					url -> /vendas/criar-venda?cliente-id=2
	@PostMapping(value = "/criar-venda")
	public ResponseEntity<Venda> criar(@RequestParam (value = "cliente-id") Integer clienteId) {
		Venda venda = service.criar(clienteId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(venda.getId()).toUri();
		return ResponseEntity.created(uri).body(venda);
	}
	
	// Para inserir item na venda - USER
	@PostMapping(value="{vendaId}/inserir-item")
	public ResponseEntity<Venda> inserirItem(@PathVariable Integer vendaId, @RequestBody ItemVendaDTO obj) {
		return ResponseEntity.ok().body(service.inserirItem(vendaId, obj));
	}
	
	
	// Para retirar item na venda - USER
	@DeleteMapping(value="/{vendaId}/deletar-item/{produtoId}")
    public ResponseEntity<Venda> retirarItemVenda(@PathVariable Integer vendaId, @PathVariable Integer produtoId) {
		Venda venda = service.retirarItemVenda(vendaId, produtoId);
		return ResponseEntity.ok().body(venda);
    }
	
	
	// Para atualizar quantidade itemVenda - USER				
	@PutMapping(value = "/{vendaId}/atualizar-qtde-item")
	public ResponseEntity<Venda> atualizarQtde(@PathVariable Integer vendaId, @RequestBody ItemVendaDTO dto) {
		Venda venda = service.atualizarItemVenda(vendaId, dto);
		return ResponseEntity.ok().body(venda);
	}
	
		
	// Para deletar venda - ADMIN
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	

	
	// Para atualizar um venda (somente data e status) - ADMIN
	@PutMapping(value = "/{id}")
	public ResponseEntity<Venda> atualizar(@PathVariable Integer id, @RequestBody Venda obj) {
		obj = service.atualizar(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
	// Para cancelar venda - USER
	@PutMapping(value = "/{id}/cancelar")
	public ResponseEntity<Venda> cancelar(@PathVariable Integer id) {
		Venda venda = service.cancelarVenda(id);
		return ResponseEntity.ok().body(venda);
	}
	
	
	// Para gerar pagamento da venda - USER
	@PutMapping(value = "/{id}/pagar")
	public ResponseEntity<Venda> pagar(@PathVariable Integer id) {
		Venda venda = service.pagar(id);
		return ResponseEntity.ok().body(venda);
	}
		

	// Para gerar relatorio mensal - ADMIN
	@GetMapping(value = "/relatorio-mensal/{ano}/{mes}") 
	public ResponseEntity<RelatorioDTO> relatorioMensal(@PathVariable Integer ano, @PathVariable Integer mes) {
		System.out.println("1");
		RelatorioDTO dto = service.relatorioMensal(mes, ano);
		return ResponseEntity.ok().body(dto);
	}
	
	
	// Para gerar relatorio semanal - ADMIN
	@GetMapping(value = "/relatorio-semanal/{ano}/{mes}/{dia}") 
	public ResponseEntity<RelatorioDTO> relatorioSemanal(@PathVariable Integer ano, @PathVariable Integer mes, @PathVariable Integer dia) {
		RelatorioDTO dto = service.relatorioSemanal(ano, mes, dia);
		return ResponseEntity.ok().body(dto);
	}
	
	
	
	
	
	
}
