package br.unitins.books.controller;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.dao.LivroDAO;
import br.unitins.books.model.Livro;

@Named
@ViewScoped
public class LivroController extends Controller<Livro> {

	private static final long serialVersionUID = 1651642114811762868L;
	
	public LivroController() {
		super(new LivroDAO());
		Flash flash = FacesContext.getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("flashLivro");
		entity = (Livro) flash.get("flashLivro");
	}
	
	@Override
	public Livro getEntity() {
		if (entity == null)
			entity = new Livro();
		return entity;
	}
	
}
