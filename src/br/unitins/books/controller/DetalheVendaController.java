package br.unitins.books.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.model.Venda;

@Named
@ViewScoped
public class DetalheVendaController implements Serializable{

	private static final long serialVersionUID = -6719949836747729139L;

	private Venda venda;
	
	public DetalheVendaController() {
		Flash flash = FacesContext.
				getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("detalheVenda");
		venda = (Venda) flash.get("detalheVenda");
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}
