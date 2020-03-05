package br.unitins.books.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.application.Util;
import br.unitins.books.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -563227102506849534L;

	private Usuario usuario;
	private List<Usuario> listaUsuario;

	public void incluir() {
//		if (getUsuario().getNome().trim().equals("")) {
//			Util.addErrorMessage("O campo nome deve ser informado.");
//			return;
//		}
	if (getUsuario().getNome().isBlank()) {
		Util.addErrorMessage("O campo nome deve ser informado.");
		return;
	}

			
		getUsuario().setId(proximoId());
		getListaUsuario().add(getUsuario());
		limpar();
 	}

	public void alterar() {
		// obtendo o indice de referencia da lista
		int index = listaUsuario.indexOf(getUsuario());
		// substituindo o objeto da lista pelo indice
		listaUsuario.set(index, getUsuario());
		limpar();
	}

	public void remover() {
		getListaUsuario().remove(getUsuario());
		limpar();
	}
	
	public void editar(Usuario usu) {
		setUsuario(usu.getClone());
	}

	public void limpar() {
		usuario = null;
	}
	
	private int proximoId() {
		int resultado = 0;
		
		for (Usuario usuario : listaUsuario) {
			if (usuario.getId() > resultado)
				resultado = usuario.getId();
		}
		return ++resultado;
	}
	
	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null)
			listaUsuario = new ArrayList<Usuario>();
		return listaUsuario;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
