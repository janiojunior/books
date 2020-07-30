package br.unitins.books.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.application.Session;
import br.unitins.books.application.Util;
import br.unitins.books.dao.VendaDAO;
import br.unitins.books.model.ItemVenda;
import br.unitins.books.model.Usuario;
import br.unitins.books.model.Venda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable{

	private static final long serialVersionUID = 780477496476930858L;

	private Venda venda;

	public Venda getVenda() {
		if (venda == null) 
			venda = new Venda();
		
		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = 
				(ArrayList<ItemVenda>)Session.getInstance().getAttribute("carrinho");
		
		// adicionando os itens do carrinho na venda
		if (carrinho == null)
			carrinho = new ArrayList<ItemVenda>();
		venda.setListaItemVenda(carrinho);
		
		return venda;
	}
	
	public void remover(int idProduto) {
		// alunos vao implementar
	}
	
	public void finalizar() {
		Usuario usuario = (Usuario)Session.getInstance().getAttribute("usuarioLogado");
		if (usuario == null) {
			Util.addWarningMessage("Eh preciso estar logado para realizar uma venda. Faca o Login!!");
			return;
		}
		// montar a venda
		Venda venda = new Venda();
		venda.setData(LocalDate.now());
		venda.setUsuario(usuario);
		List<ItemVenda> carrinho = (ArrayList<ItemVenda>)  Session.getInstance().getAttribute("carrinho");
		venda.setListaItemVenda(carrinho);
		// salvar no banco
		VendaDAO dao = new VendaDAO();
		if (dao.create(venda)) {
			Util.addInfoMessage("Venda realizada com sucesso.");
			// limpando o carrinho
			Session.getInstance().setAttribute("carrinho", null);
		} else {
			Util.addErrorMessage("Erro ao finalizar a Venda.");
		}
		
	}

	public void setVenda(Venda venda) {
		
		this.venda = venda;
	}
	
	
	
}
