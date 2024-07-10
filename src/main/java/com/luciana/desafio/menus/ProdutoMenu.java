package com.luciana.desafio.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.luciana.desafio.entities.Produto;
import com.luciana.desafio.services.ProdutoService;


public class ProdutoMenu {

	@Autowired
	private ProdutoService produtoService;

	public ProdutoMenu() {
	}
	
	
	public void menu() {
		
		
		Scanner sc = new Scanner(System.in);
		Integer opcao = 0;
		
		Integer id;
		String descr;
		Double preco;
		Integer estoque;
		Boolean status;
		
		Produto prod;
		
		
		
		do {
			System.out.println();
			System.out.println("ESCOLHA UMA OPCAO:");
			System.out.println();
			System.out.println("1 - Inserir Produto");
			System.out.println("2 - Deletar Produto");
			System.out.println("3 - Atualizar Produto");
			System.out.println("4 - Buscar Produto");
			System.out.println("5 - Listar todos os Produto");
			System.out.println("0 - Voltar");
			
			opcao = sc.nextInt();
		
			
			switch (opcao) {
				case 0: {
					break;
				}
				case 1: {	// INSERIR
					System.out.println();
					System.out.print("Digite a descricao do produto: ");
					descr = sc.nextLine();
					sc.nextLine();
					System.out.print("Digite o preco do produto: ");
					preco = sc.nextDouble();
					System.out.print("Digite o estoque do produto: ");
					estoque = sc.nextInt();
					System.out.print("Digite o status do produto (true - ativo / false - inativo): ");
					status = sc.nextBoolean();
					
					prod = new Produto(null, descr, preco, estoque, status);
					produtoService.inserir(prod);
					break;
				}
				case 2: {	// DELETAR
					System.out.println();
					System.out.print("Digite o id do produto: ");
					id = sc.nextInt();
					
					produtoService.deletar(id);
					break;
				}
				case 3: {	// ATUALIZAR
					System.out.println();
					System.out.print("Digite o id do produto: ");
					id = sc.nextInt();
					System.out.println();
					
					prod = produtoService.findById(id);
					System.out.println(prod.toString());
					System.out.println();
					
					System.out.print("Digite nova descricao do produto: ");
					descr = sc.nextLine();
					sc.nextLine();
					System.out.print("Digite novo preco do produto: ");
					preco = sc.nextDouble();
					System.out.print("Digite novo estoque do produto: ");
					estoque = sc.nextInt();
					System.out.print("Digite o status do produto (true - ativo / false - inativo): ");
					status = sc.nextBoolean();
					
					prod = new Produto(null, descr, preco, estoque, status);
					produtoService.atualizar(id, prod);			
					break;
				}
				case 4: {	// BUSCAR UM PRODUTO
					System.out.println();
					System.out.print("Digite o id do produto: ");
					id = sc.nextInt();
					System.out.println();
					
					prod = produtoService.findById(id);
					System.out.println(prod.toString());
					System.out.println();
					
					break;
				}
				case 5: {	// LISTAR TODOS OS PRODUTOS
					
					List<Produto> list = new ArrayList<>();
					list = produtoService.findAll();
					
					for (Produto p : list) {
						System.out.println(p.toString());
					}
					
					break;
				}
				default:
					throw new IllegalArgumentException("Valor inexistente: " + opcao);
			}
			
		
		} while (opcao != 0);
		
		sc.close();
		
	}
	
	
}
