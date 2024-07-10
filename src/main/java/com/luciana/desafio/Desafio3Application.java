package com.luciana.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Desafio3Application {

	public static void main(String[] args) {
		SpringApplication.run(Desafio3Application.class, args);
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
