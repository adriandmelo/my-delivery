package controller;

import java.util.ArrayList;

import model.bo.ProdutoBO;
import model.vo.ProdutoVO;
import model.vo.TipoProdutoVO;

public class ProdutoController {

	public ProdutoVO cadastrarProdutoController(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.cadastrarProdutoBO(produtoVO);
	}

	public ArrayList<TipoProdutoVO> consultarTipoProdutos() {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarTiposProdutosBO();
	}

	public boolean atualizarProdutoController(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.atualizarProdutoBO(produtoVO);
	}

	public boolean excluirProdutoController(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.excluirProdutoBO(produtoVO);
	}

	public ArrayList<ProdutoVO> consultarTodosProdutosController() {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarTodosProdutosBO();
	}

	public ProdutoVO consultarProdutoController(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarProdutoBO(produtoVO);
	}

	public ArrayList<ProdutoVO> consultarTodosProdutosVigentesController() {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarTodosProdutosVigentesBO();
	}

}
