package view;

import java.util.Scanner;

import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class Menu {
	
	private static final int OPCAO_MENU_VENDA = 1;
	private static final int OPCAO_MENU_CADASTRO = 2;
	private static final int OPCAO_MENU_RELATORIO = 3;
	private static final int OPCAO_MENU_USUARIO = 4;
	private static final int OPCAO_MENU_VOLTAR = 9;
	
	Scanner teclado = new Scanner(System.in);

	public void apresentarMenu(UsuarioVO usuarioVO) {
		int opcao = this.apresentarOpcoesMenu(usuarioVO);
		while (opcao != OPCAO_MENU_VOLTAR) {
			switch (opcao) {
				case OPCAO_MENU_VENDA: {
					System.out.println("Acessando o menu de Vendas...");
					break;
				}
				case OPCAO_MENU_CADASTRO: {
					System.out.println("Acessando menu de Cadastro...");
					break;
				}
				case OPCAO_MENU_RELATORIO: {
					System.out.println("Acessando o menu de Relatórios...");
					break;
				}
				case OPCAO_MENU_USUARIO: {
					if(usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ADMINISTRADOR)) {
						MenuUsuario menuUsuario = new MenuUsuario();
						menuUsuario.apresentarMenuUsuario();
					}
					break;
				}
				default: {
					System.out.println("\nOpção Inválida!!!");
				}
			}
			opcao = this.apresentarOpcoesMenu(usuarioVO);
		}
	}

	private int apresentarOpcoesMenu(UsuarioVO usuarioVO) {
		System.out.println("\n-------- Sistema Foodtruck ---------");
		System.out.println("--------- Menu Principal ----------");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_VENDA + " - Menu de Vendas");
		if(usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.FUNCIONARIO) || usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ADMINISTRADOR)) {
			System.out.println(OPCAO_MENU_CADASTRO + " - Menu de Cadastros");
			System.out.println(OPCAO_MENU_RELATORIO + " - Menu de Relatórios");
		}
		if(usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ADMINISTRADOR)) {
			System.out.println(OPCAO_MENU_USUARIO + " - Menu de Usuários");
		}
		System.out.println(OPCAO_MENU_VOLTAR + " - Voltar");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
}