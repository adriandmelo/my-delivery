package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.RelatorioController;
import model.dto.VendasCanceladaDTO;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;
import model.vo.VendaVO;

public class MenuRelatorio {
	
	private static final int OPCAO_MENU_RELATORIO_VENDAS_CANCELADAS = 1;
	private static final int OPCAO_MENU_RELATORIO_LISTA_DE_PEDIDOS = 2;
	private static final int OPCAO_MENU_RELATORIO_ACOMPANHAMENTO_PEDIDOS = 3;
	private static final int OPCAO_MENU_RELATORIO_ETIQUETA_ENTREGADOR = 4;
	private static final int OPCAO_MENU_VOLTAR = 9;
	
	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuRelatorio() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_VOLTAR) {
			switch (opcao) {
				case OPCAO_MENU_RELATORIO_VENDAS_CANCELADAS: {
					this.gerarRelatorioVendasCanceladas();
					break;
				}
				case OPCAO_MENU_RELATORIO_LISTA_DE_PEDIDOS: {
					this.gerarRelatorioListaPedidos();
					break;
				}
				case OPCAO_MENU_RELATORIO_ACOMPANHAMENTO_PEDIDOS: {
					this.gerarRelatorioAcompanhamentoPedidos();
					break;
				}
				case OPCAO_MENU_RELATORIO_ETIQUETA_ENTREGADOR: {
					this.gerarRelatorioEtiquetaEntregador();
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
		System.out.println("--------- Menu Relatórios ----------");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_RELATORIO_VENDAS_CANCELADAS + " - Relatório de Vendas Canceladas");
		System.out.println(OPCAO_MENU_RELATORIO_LISTA_DE_PEDIDOS + " - Relatório de Lista de Pedidos");
		System.out.println(OPCAO_MENU_RELATORIO_ACOMPANHAMENTO_PEDIDOS + " - Relatório de Acompanhamento de Pedidos");
		System.out.println(OPCAO_MENU_RELATORIO_ETIQUETA_ENTREGADOR + " - Relatório de Etiqueta do Entregador");
		System.out.println(OPCAO_MENU_VOLTAR + " - Voltar");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void gerarRelatorioVendasCanceladas() {
		RelatorioController relatorioController = new RelatorioController();
		ArrayList<VendasCanceladaDTO> listaVendasCanceladaDTO = relatorioController.gerarRelatorioVendasCanceladasController();
		System.out.print("\n--------- RESULTADO DA CONSULTA ---------");
		System.out.printf("\n%-20s  %-24s  %12s  %12s  %12s  ", 
				"NOME", "DATA CANCELAMENTO", "SUBTOTAL", "TAXA ENTREGA", "TOTAL");
		for (int i = 0; i < listaVendasCanceladaDTO.size(); i++) {
			listaVendasCanceladaDTO.get(i).imprimir();
		}
		System.out.println();
	}
	
	private void gerarRelatorioListaPedidos() {
		RelatorioController relatorioController = new RelatorioController();
		relatorioController.gerarRelatorioListaPedidosController();
	}
	
	private void gerarRelatorioAcompanhamentoPedidos() {
		VendaVO vendaVO = new VendaVO();
		System.out.print("\nInforme o código da venda: ");
		vendaVO.setIdVenda(Integer.parseInt(teclado.nextLine()));
		RelatorioController relatorioController = new RelatorioController();
		relatorioController.gerarRelatorioAcompanhamentoPedidosController(vendaVO);
	}
	
	private void gerarRelatorioEtiquetaEntregador() {
		VendaVO vendaVO = new VendaVO();
		System.out.print("\nInforme o código da venda: ");
		vendaVO.setIdVenda(Integer.parseInt(teclado.nextLine()));
		RelatorioController relatorioController = new RelatorioController();
		relatorioController.gerarRelatorioEtiquetaEntregadorController(vendaVO);
	}
	
}
