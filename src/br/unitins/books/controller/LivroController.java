package br.unitins.books.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.dao.LivroDAO;
import br.unitins.books.model.Livro;

@Named
@ViewScoped
public class LivroController extends Controller<Livro> implements Serializable {


	private static final long serialVersionUID = 1651642114811762868L;
	
	private List<Livro> listaLivro;
	
	public LivroController() {
		super(new LivroDAO());
	}
	
	@Override
	public Livro getEntity() {
		if (entity == null)
			entity = new Livro();
		return entity;
	}
	
	@Override
	public void limpar() {
		super.limpar();
		listaLivro = null;
	}

	public List<Livro> getListaLivro() {
		if (listaLivro == null) {
			LivroDAO dao = new LivroDAO();
			listaLivro = dao.findAll();
		}
		return listaLivro;
	}

}
