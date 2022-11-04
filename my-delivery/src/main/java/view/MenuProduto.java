package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ProdutoController;
import model.vo.ProdutoVO;
import model.vo.TipoProdutoVO;


public class MenuProduto {
	
	private static final int OPCAO_MENU_CADASTRAR_PRODUTO = 1;
	private static final int OPCAO_MENU_CONSULTAR_PRODUTO = 2;
	private static final int OPCAO_MENU_ATUALIZAR_PRODUTO = 3;
	private static final int OPCAO_MENU_EXCLUIR_PRODUTO = 4;
	private static final int OPCAO_MENU_PRODUTO_VOLTAR = 9;
	
	private static final int OPCAO_MENU_CONSULTAR_TODOS_PRODUTOS = 1;
	private static final int OPCAO_MENU_CONSULTAR_UM_PRODUTO = 2;
	private static final int OPCAO_MENU_CONSULTAR_PRODUTO_SAIR = 9;
	
	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuProduto() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_PRODUTO_VOLTAR) {
			switch (opcao) {
				case OPCAO_MENU_CADASTRAR_PRODUTO: {
					this.cadastrarProduto();
					break;
				}
				case OPCAO_MENU_CONSULTAR_PRODUTO: {
					this.consultarProduto();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_PRODUTO: {
					this.atualizarProduto();
					break;
				}
				case OPCAO_MENU_EXCLUIR_PRODUTO: {
					this.excluirProduto();
					break;
				}
				default: {
					System.out.println("\nOpção Inválida!!!");
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}
	}
	
	private int apresentarOpcoesMenu() {
		System.out.println("\n-------- Sistema Foodtruck ---------");
		System.out.println("--------- Menu Produto ----------");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_CADASTRAR_PRODUTO + " - Cadastrar Produto");
		System.out.println(OPCAO_MENU_CONSULTAR_PRODUTO + " - Consultar Produto");
		System.out.println(OPCAO_MENU_ATUALIZAR_PRODUTO + " - Atualizar Produto");
		System.out.println(OPCAO_MENU_EXCLUIR_PRODUTO + " - Excluir Produto");
		System.out.println(OPCAO_MENU_PRODUTO_VOLTAR + " - Voltar");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	private void cadastrarProduto() {
		ProdutoVO produtoVO = new ProdutoVO();
		do {
			produtoVO.setTipoProduto(TipoProdutoVO.getTipoProdutoVOPorValor(this.apresentarOpcoesTipoProduto()));
		} while(produtoVO.getTipoProduto() == null);
		System.out.print("\nDigite o nome: ");
		produtoVO.setNome(teclado.nextLine());
		System.out.print("Digite o preço: ");
		produtoVO.setPreco(Double.parseDouble(teclado.nextLine()));
		produtoVO.setDataCadastro(LocalDateTime.now());
		
		if(this.validarCamposCadastro(produtoVO)) {
			ProdutoController produtoController = new ProdutoController();
			produtoVO = produtoController.cadastrarProdutoController(produtoVO);
			if(produtoVO.getIdProduto() != 0) {
				System.out.println("Produto cadastrado com sucesso!");
			} else {
				System.out.println("Não foi possível cadastar o Produto!");
			}
		}
	}

	private int apresentarOpcoesTipoProduto() {
		ProdutoController produtoController = new ProdutoController();
		ArrayList<TipoProdutoVO> listaTipoProdutoVO = produtoController.consultarTipoProdutos();
		System.out.println("\n---- Tipos de Produtos ----");
		System.out.println("Opções:");
		for(int i = 0; i < listaTipoProdutoVO.size(); i++) {
			System.out.println(listaTipoProdutoVO.get(i).getValor() + " - " + listaTipoProdutoVO.get(i));
		}
		System.out.print("\nDigite uma opção: ");		
		return Integer.parseInt(teclado.nextLine());
	}
	
	private boolean validarCamposCadastro(ProdutoVO produtoVO) {
		boolean resultado = true;
		System.out.println();
		if(produtoVO.getNome() == null || produtoVO.getNome().isEmpty()) {
			System.out.println("O campo nome é obrigatório.");
			resultado = false;
		}
		if(produtoVO.getPreco() == 0) {
			System.out.println("O campo preço é obrigatório.");
			resultado = false;
		}
		if(produtoVO.getDataCadastro() == null) {
			System.out.println("O campo data de cadastro é obrigatório.");
			resultado = false;
		}
		return resultado;
	}
	

	private void consultarProduto() {
		int opcao = this.apresentarOpcoesConsulta();
		ProdutoController produtoController = new ProdutoController();
		while (opcao != OPCAO_MENU_CONSULTAR_PRODUTO_SAIR) {
			switch (opcao) {
				case OPCAO_MENU_CONSULTAR_TODOS_PRODUTOS: {
					opcao = OPCAO_MENU_CONSULTAR_PRODUTO_SAIR;
					ArrayList<ProdutoVO> listaProdutosVO = produtoController.consultarTodosProdutosController();
					System.out.print("\n--------- RESULTADO DA CONSULTA ---------");
					System.out.printf("\n%3s  %-13s  %-20s  %-7s  %-24s  %-24s ", 
							"ID", "TIPO PRODUTO", "NOME", "PREÇO", "DATA CADASTRO", "DATA EXCLUSÃO");
					for (int i = 0; i < listaProdutosVO.size(); i++) {
						listaProdutosVO.get(i).imprimir();
					}
					System.out.println();
					break;
				}
				case OPCAO_MENU_CONSULTAR_UM_PRODUTO: {
					opcao = OPCAO_MENU_CONSULTAR_PRODUTO_SAIR;
					ProdutoVO produtoVO = new ProdutoVO();
					System.out.print("\nInforme o código do Produto: ");
					produtoVO.setIdProduto(Integer.parseInt(teclado.nextLine()));
					if(produtoVO.getIdProduto() != 0) {
						ProdutoVO produto = produtoController.consultarProdutoController(produtoVO);
						System.out.print("\n--------- RESULTADO DA CONSULTA ---------");
						System.out.printf("\n%3s  %-13s  %-20s  %-7s  %-24s  %-24s ", 
								"ID", "TIPO PRODUTO", "NOME", "PREÇO", "DATA CADASTRO", "DATA EXCLUSÃO");
						produto.imprimir();
						System.out.println();
					} else {
						System.out.println("O campo código do produto e obrigatório.");
					}

					break;
				}
				default: {
					System.out.println("\nOpção Inválida");
					opcao = this.apresentarOpcoesConsulta();
				}
			}
		}
	}
	
	private int apresentarOpcoesConsulta() {
		System.out.println("\nInforme o tipo de consulta a ser realizada");
		System.out.println(OPCAO_MENU_CONSULTAR_TODOS_PRODUTOS + " - Consultar todos os Produtos");
		System.out.println(OPCAO_MENU_CONSULTAR_UM_PRODUTO + " - Consultar um Produto Específico");
		System.out.println(OPCAO_MENU_CONSULTAR_PRODUTO_SAIR + " - Voltar");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	private void atualizarProduto() {
		ProdutoVO produtoVO = new ProdutoVO();
		System.out.print("\nInforme o código do Produto: ");
		produtoVO.setIdProduto(Integer.parseInt(teclado.nextLine()));
		do {
			produtoVO.setTipoProduto(TipoProdutoVO.getTipoProdutoVOPorValor(this.apresentarOpcoesTipoProduto()));
		} while(produtoVO.getTipoProduto() == null);
		System.out.println();
		System.out.print("Digite o nome: ");
		produtoVO.setNome(teclado.nextLine());
		System.out.print("Digite o preço: ");
		produtoVO.setPreco(Double.parseDouble(teclado.nextLine()));
		System.out.print("Digite a data de cadastro no formato dd/MM/yyyy HH:mm:ss: ");
		produtoVO.setDataCadastro(LocalDateTime.parse(teclado.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
		
		if(this.validarCamposCadastro(produtoVO)) {
			ProdutoController produtoController = new ProdutoController();
			boolean resultado = produtoController.atualizarProdutoController(produtoVO);
			if(resultado){
				System.out.println("\nProduto atualizado com Sucesso.");
			} else {
				System.out.println("Não foi possível atualizar o Produto.");
			}
		}
	}

	private void excluirProduto() {
		ProdutoVO produtoVO = new ProdutoVO();
		System.out.print("\nInforme o código do Produto: ");
		produtoVO.setIdProduto(Integer.parseInt(teclado.nextLine()));
		System.out.print("Digite a data da exclusão no formato dd/MM/yyyy HH:mm:ss: ");
		produtoVO.setDataExclusao(LocalDateTime.parse(teclado.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
		
		if(produtoVO.getIdProduto() == 0 || produtoVO.getDataExclusao() == null) {
			System.out.println("Os campos código do produto e data de exclusão são obrigatórios.");
		} else {
			ProdutoController produtoController = new ProdutoController();
			boolean resultado = produtoController.excluirProdutoController(produtoVO);
			if(resultado){
				System.out.println("\nProduto excluído com Sucesso.");
			} else {
				System.out.println("Não foi possível excluir o Produto.");
			}
		}
	}


}
