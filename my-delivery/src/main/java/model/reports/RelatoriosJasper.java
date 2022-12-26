package model.reports;

import java.io.IOException;
import java.util.HashMap;

import model.dao.Banco;
import model.vo.VendaVO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class RelatoriosJasper {

	public void gerarRelatorioListaPedidosJasper() {
        try {
            String currentPath = "";
            try {
                currentPath = new java.io.File(".").getCanonicalPath();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            System.out.println(currentPath);
            JasperRunManager.runReportToPdfFile(currentPath + "/REL01.jasper", currentPath + "/REL01.pdf", null, Banco.getConnection());
            System.out.println("Relatorio gerado em " + currentPath + "/REL01.pdf");
        } catch (JRException ex) {
            System.out.println("Não foi possivel imprimir, por favor verifique o modelo de impressão");
        }
		
	}

	public void gerarRelatorioAcompanhamentoPedidosJasper(VendaVO vendaVO) {
        try {
            String currentPath = "";
            try {
                currentPath = new java.io.File(".").getCanonicalPath();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            System.out.println(currentPath);
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("IDVENDA", vendaVO.getIdVenda());
            JasperRunManager.runReportToPdfFile(currentPath + "/REL02.jasper", currentPath + "/REL02.pdf", parameters, Banco.getConnection());
            System.out.println("Relatorio gerado em " + currentPath + "/REL02.pdf");
        } catch (JRException ex) {
            System.out.println("Não foi possivel imprimir, por favor verifique o modelo de impressão");
            System.out.println(ex.getMessage());
        }
	}

	public void gerarRelatorioEtiquetaEntregadorJasper(VendaVO vendaVO) {
		try {
            String currentPath = "";
            try {
                currentPath = new java.io.File(".").getCanonicalPath();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            System.out.println(currentPath);
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("IDVENDA", vendaVO.getIdVenda());
            JasperRunManager.runReportToPdfFile(currentPath + "/REL03.jasper", currentPath + "/REL03.pdf", parameters, Banco.getConnection());
            System.out.println("Relatorio gerado em " + currentPath + "/REL03.pdf");
        } catch (JRException ex) {
            System.out.println("Não foi possivel imprimir, por favor verifique o modelo de impressão");
            System.out.println(ex.getMessage());
        }
	}

}
