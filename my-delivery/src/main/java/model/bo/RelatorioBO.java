package model.bo;

import java.util.ArrayList;

import model.dao.RelatorioDAO;
import model.dto.VendasCanceladaDTO;
import model.reports.RelatoriosJasper;
import model.vo.VendaVO;

public class RelatorioBO {
	
	public ArrayList<VendasCanceladaDTO> gerarRelatorioVendasCanceladasBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		return relatorioDAO.gerarRelatorioVendasCanceladasDAO();
	}

	public void gerarRelatorioListaPedidosBO() {
		RelatoriosJasper relatorio = new RelatoriosJasper();
		relatorio.gerarRelatorioListaPedidosJasper();
	}

	public void gerarRelatorioAcompanhamentoPedidosBO(VendaVO vendaVO) {
		RelatoriosJasper relatorio = new RelatoriosJasper();
		relatorio.gerarRelatorioAcompanhamentoPedidosJasper(vendaVO);
	}

	public void gerarRelatorioEtiquetaEntregadorBO(VendaVO vendaVO) {
		RelatoriosJasper relatorio = new RelatoriosJasper();
		relatorio.gerarRelatorioEtiquetaEntregadorJasper(vendaVO);
	}

}
