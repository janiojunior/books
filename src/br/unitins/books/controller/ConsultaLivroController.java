package br.unitins.books.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.dao.LivroDAO;
import br.unitins.books.model.Livro;

@Named
@ViewScoped
public class ConsultaLivroController implements Serializable{
	
	private static final long serialVersionUID = 5971844866316069324L;
	
	private int tipoFiltro = 1;
	private String filtro;
	private List<Livro> listaLivro;
	
	public void pesquisar() {
		LivroDAO dao = new LivroDAO();
		if (tipoFiltro == 1)
			listaLivro = dao.findByDescricao(getFiltro());
		else 
			listaLivro = dao.findByIsbn(getFiltro());
	}
	
	public String novoLivro() {
		return "livro.xhtml?faces-redirect=true";
	}
	
	public String editar(Livro livro) {
		LivroDAO dao = new LivroDAO();
		livro = dao.findById(livro.getId());
		
		Flash flash = FacesContext.getCurrentInstance().
						getExternalContext().getFlash();
		
		flash.put("flashLivro", livro);
		return "livro.xhtml?faces-redirect=true";
	}

	public List<Livro> getListaLivro() {
		if (listaLivro == null) {
			listaLivro = new ArrayList<Livro>();
		}
		return listaLivro;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public int getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

}
