package com.luciana.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Desafio3Application {

	public static void main(String[] args) {
		SpringApplication.run(Desafio3Application.class, args);
		/*
		Cliente cl1 = new Cliente(1, "Maria", "333.444.555.666-77", "maria@gmail.com");
		Cliente cl2 = new Cliente(2, "Alberto", "364.256.789-80", "alberto@gmail.com");
		Cliente cl3 = new Cliente(3, "Ana Carolina", "326.153.248-60", "anacarolina@gmail.com");
		
		Venda v1 = new Venda(1, Instant.parse("2024-02-10T19:53:00Z"), StatusVenda.PENDENTE, cl1);
		Venda v2 = new Venda(2, Instant.parse("2024-03-15T10:14:00Z"), StatusVenda.FECHADA, cl2);
		Venda v3 = new Venda(3, Instant.parse("2024-03-20T22:37:00Z"), StatusVenda.FECHADA, cl2);
		Venda v4 = new Venda(4, Instant.parse("2024-05-01T18:00:00Z"), StatusVenda.FECHADA, cl3);
		
		Produto p1 = new Produto(1, "Notebook", 3000.00, 30, true);
		Produto p2 = new Produto(2, "Mouse", 90.00, 240, true);
		Produto p3 = new Produto(3, "Fone sem fio", 250.00, 12, true);
		Produto p4 = new Produto(4, "Mousepad", 60.00, 0, false);
		Produto p5 = new Produto(5, "Monitor", 800.00, 24, true);
		
		ItemVenda it1 = new ItemVenda(v1, p1, 1, p1.getPreco());
		ItemVenda it2 = new ItemVenda(v1, p2, 1, p2.getPreco());
		ItemVenda it3 = new ItemVenda(v2, p2, 2, p2.getPreco());
		ItemVenda it4 = new ItemVenda(v2, p5, 1, p5.getPreco());
		ItemVenda it5 = new ItemVenda(v3, p3, 1, p3.getPreco());
		ItemVenda it6 = new ItemVenda(v4, p5, 3, p5.getPreco());
		
		Pagamento pg1 = new Pagamento(1, Instant.parse("2024-03-15T11:00:00Z"), v2);
		Pagamento pg2 = new Pagamento(2, Instant.parse("2024-03-22T08:15:00Z"), v3);
		Pagamento pg3 = new Pagamento(3, Instant.parse("2024-05-01T22:58:00Z"), v4);
		v2.setPagamento(pg1);
		v3.setPagamento(pg2);
		v4.setPagamento(pg3);
		
		// TESTES 
		
		System.out.println();
		System.out.println(cl1);
		
		System.out.println();
		System.out.println(p1);
		System.out.println(p2);
		
		System.out.println();
		System.out.println(it1);
		System.out.println(it2);
		
		System.out.println();

		
		*/
		
		
		
		/*
		Scanner sc = new Scanner(System.in);
		
		
		//ProdutoMenu produtoMenu = new ProdutoMenu();
		int opcao = 0;
		
		do {
			System.out.println();
			System.out.println("ESCOLHA UMA OPCAO:");
			System.out.println();
			System.out.println("1 - Gerenciar PRODUTO");
			System.out.println("2 - Gerenciar VENDA");
			System.out.println("0 - Sair");
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 0: {
				break;
			}
			case 1: {
				
				produtoMenu.menu();
				break;
			}
			case 2: {
				
	
				break;
			}
			default:
				throw new IllegalArgumentException("Valor inexistente: " + opcao);
		}
			
			
			
			
			
		} while (opcao != 0);
		
	
		sc.close();
		*/ 
		
		
		
		
		
	}

}
