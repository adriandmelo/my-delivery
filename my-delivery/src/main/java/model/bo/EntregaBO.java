package model.bo;

import java.util.ArrayList;
import java.util.Random;

import model.dao.EntregaDAO;
import model.dao.UsuarioDAO;
import model.dao.VendaDAO;
import model.vo.EntregaVO;
import model.vo.SituacaoEntregaVO;
import model.vo.UsuarioVO;
import model.vo.VendaVO;

public class EntregaBO {

	public boolean cadastrarEntregaBO(int idVenda) {
		boolean retorno = true;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<UsuarioVO> listaEntregadores = usuarioDAO.consultarListaEntregadores();
		if(listaEntregadores.isEmpty()) {
			System.out.println("Não existem entregadores cadastrados na base de dados!");
			retorno = false;
		} else {
			Random gerador = new Random();
			UsuarioVO entregador = listaEntregadores.get(gerador.nextInt(listaEntregadores.size()));
			EntregaVO entregaVO = new EntregaVO(0, idVenda, entregador.getIdUsuario(), SituacaoEntregaVO.PEDIDO_REALIZADO, null);
			EntregaDAO entregaDAO = new EntregaDAO();
			boolean resultado = entregaDAO.cadastrarEntregaDAO(entregaVO);
			if(!resultado) {
				System.out.println("Houve um problema ao tentar cadastrar a entrega!");
				retorno = false;
			}
		}
		return retorno;
	}

	//Verificar através do vendaBO.verificarVendaParaAtualizarSituacaoEntrega(vendaVO) se pode atualizar.
	public boolean atualizarSituacaoEntregaBO(VendaVO vendaVO) {
		boolean retorno = false;
		EntregaDAO entregaDAO = new EntregaDAO();
		VendaBO vendaBO = new VendaBO();
		boolean resultado = vendaBO.verificarVendaParaAtualizarSituacaoEntrega(vendaVO);
		if(resultado) {
			retorno = entregaDAO.atualizarSituacaoEntregaDAO(vendaVO);
		}
		return retorno;
	}

	//Verificar se a venda existe na base de dados.
	//Verificar se a venda já está cancelada na base de dados.
	//Verificar se a data de cancelamento é posterior a dada de venda.
	//Se houver entrega verificar se a entrega já foi realizada ou se esta em rota de entrega.
	public boolean cancelarEntregaBO(VendaVO vendaVO) {
		boolean retorno = false;
		EntregaDAO entregaDAO = new EntregaDAO();
		VendaDAO vendaDAO = new VendaDAO();
		VendaVO venda = vendaDAO.consultarVendaDAO(vendaVO);
		if(venda != null) {
			if(venda.getDataCancelamento() == null) {
				if(venda.getDataVenda().isBefore(vendaVO.getDataCancelamento())) {
					if(venda.isFlagEntrega()) {
						EntregaVO entregaVO = entregaDAO.consultarEntregaPorIdVendaDAO(vendaVO.getIdVenda());
						if(entregaVO.getSituacaoEntrega().getValor() <= SituacaoEntregaVO.PREPARANDO_PEDIDO.getValor()) {
							retorno = entregaDAO.cancelarEntregaDAO(vendaVO, SituacaoEntregaVO.ENTREGA_CANCELADA.getValor());
							if(!retorno) {
								System.out.println("\nNão foi possível alterar a situação da entrega para cancelada.");
							}
						} else {
							System.out.println("\nO pedido já se encontra em processo de entrega/entregue.");
						}
					} else {
						System.out.println("\nEsta venda não possui entrega.");
					}
				} else {
					System.out.println("\nA data de cancelamento da entrega é anterior a data de cadastro da venda.");
				}
			} else {
				System.out.println("\nVenda já se encontra cancelada na base da dados, logo a entrega também já está cancelada.");
			}
		} else {
			System.out.println("\nVenda não existe na base da dados, logo não existe entrega a ser cancelada.");
		}
		return retorno;
	}

}
