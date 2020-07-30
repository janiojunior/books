package br.unitins.books.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.application.Session;
import br.unitins.books.application.Util;
import br.unitins.books.dao.LivroDAO;
import br.unitins.books.model.ItemVenda;
import br.unitins.books.model.Livro;

@Named
@ViewScoped
public class VendaLivroController implements Serializable {

	private static final long serialVersionUID = -8413311224021825448L;

	private String descricao;
	private List<Livro> listaLivro = null;
	
	public void pesquisar() {
		listaLivro = null;
	}
	
	public void adicionar(int idLivro) {
		LivroDAO dao = new LivroDAO();
		Livro Livro = dao.findById(idLivro);
		// verifica se existe um carrinho na sessao
		if (Session.getInstance().getAttribute("carrinho") == null) {
			// adiciona um carrinho (de itens de venda) na sessao
			Session.getInstance().setAttribute("carrinho", 
					new ArrayList<ItemVenda>());
		}
		
		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = 
				(ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		
		// criando um item de venda para adicionar no carrinho
		ItemVenda item = new ItemVenda();
		item.setLivro(Livro);
		item.setValor(Livro.getPreco());
		
		// adicionando o item no carrinho (variavel local) 
		carrinho.add(item);
		
		// atualizando o carrinho na sessao
		Session.getInstance().setAttribute("carrinho", carrinho);
		
		Util.addInfoMessage("Livro adicionado no carrinho. "
				+ "Quantidade de Itens: " + carrinho.size());
		
	}

	public List<Livro> getListaLivro() {
		if (listaLivro == null) {
			LivroDAO dao = new LivroDAO();
			listaLivro = dao.findByDescricao(getDescricao());
			if (listaLivro == null)
				listaLivro = new ArrayList<Livro>();
		}
		return listaLivro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
