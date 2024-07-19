package com.luciana.desafio.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.luciana.desafio.entities.Cliente;
import com.luciana.desafio.entities.ItemVenda;
import com.luciana.desafio.entities.Pagamento;
import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.entities.Venda;
import com.luciana.desafio.entities.enums.StatusVenda;
import com.luciana.desafio.entities.enums.TipoCliente;
import com.luciana.desafio.repositories.ClienteRepository;
import com.luciana.desafio.repositories.ItemVendaRepository;
import com.luciana.desafio.repositories.ProdutoRepository;
import com.luciana.desafio.repositories.VendaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private VendaRepository vendaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ItemVendaRepository itemVendaRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		// inserindo clientes
		
		Cliente cl1 = new Cliente(null, TipoCliente.ADMIN, "Maria", "520.834.160-31", "maria@gmail.com", passwordEncoder.encode("123maria"));
		Cliente cl2 = new Cliente(null, TipoCliente.USER, "Alberto", "268.647.710-59", "alberto@gmail.com", passwordEncoder.encode("alberto456"));
		Cliente cl3 = new Cliente(null, TipoCliente.USER, "Ana Carolina", "572.761.830-41", "anacarolina@gmail.com", passwordEncoder.encode("ana789carol"));
		
		clienteRepository.saveAll(Arrays.asList(cl1, cl2, cl3));
		
		
		// inserindo vendas
		
		Venda v1 = new Venda(null, Instant.parse("2024-02-10T19:53:00Z"), StatusVenda.PENDENTE, cl1);
		Venda v2 = new Venda(null, Instant.parse("2024-03-18T10:14:00Z"), StatusVenda.FECHADA, cl2);
		Venda v3 = new Venda(null, Instant.parse("2024-03-20T22:37:00Z"), StatusVenda.CANCELADA, cl2);
		Venda v4 = new Venda(null, Instant.parse("2024-03-01T18:00:00Z"), StatusVenda.PENDENTE, cl3);
		
		vendaRepository.saveAll(Arrays.asList(v1, v2, v3, v4));
		
		
		// inserindo produtos
		
		Produto p1 = new Produto(null, "Notebook", 3000.00, 30, true);
		Produto p2 = new Produto(null, "Mouse", 90.00, 240, true);
		Produto p3 = new Produto(null, "Fone sem fio", 250.00, 10, true);
		Produto p4 = new Produto(null, "Mousepad", 60.00, 0, false);
		Produto p5 = new Produto(null, "Monitor", 800.00, 20, true);
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		
		// inserindo itens
		
		ItemVenda it1 = new ItemVenda(v1, p1, 1, p1.getPreco());
		ItemVenda it2 = new ItemVenda(v1, p2, 4, p2.getPreco());
		ItemVenda it3 = new ItemVenda(v2, p2, 2, p2.getPreco());
		ItemVenda it4 = new ItemVenda(v2, p5, 1, p5.getPreco());
		ItemVenda it5 = new ItemVenda(v3, p3, 1, p3.getPreco());
		ItemVenda it6 = new ItemVenda(v4, p5, 3, p5.getPreco());
	
		itemVendaRepository.saveAll(Arrays.asList(it1, it2, it3, it4, it5, it6));
		
		
		// inserindo pagamentos
		
		Pagamento pg1 = new Pagamento(null, Instant.parse("2024-03-15T11:00:00Z"), v2);
		Pagamento pg2 = new Pagamento(null, Instant.parse("2024-03-22T08:15:00Z"), v3);
		v2.setPagamento(pg1);
		v3.setPagamento(pg2);
		
		vendaRepository.saveAll(Arrays.asList(v2, v3, v4));
		
	}
	
	
	
	
}
