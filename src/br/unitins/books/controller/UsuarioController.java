package br.unitins.books.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.books.application.Util;
import br.unitins.books.model.TipoUsuario;
import br.unitins.books.model.Usuario;

@Named
@ApplicationScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -563227102506849534L;

	private Usuario usuario;
	private List<Usuario> listaUsuario;
	
	public void incluir() {
		if (getUsuario().getNome().isBlank()) {
			Util.addErrorMessage("O campo nome deve ser informado.");
			return;
		}
			
		getUsuario().setId(proximoId());
		getListaUsuario().add(getUsuario());
		limpar();
		Util.addInfoMessage("Inclusão realizada com sucesso.");
 	}

	public void alterar() {
		// obtendo o indice de referencia da lista
		int index = listaUsuario.indexOf(getUsuario());
		// substituindo o objeto da lista pelo indice
		listaUsuario.set(index, getUsuario());
		limpar();
		Util.addInfoMessage("Alteração realizada com sucesso.");
	}

	public void remover() {
		getListaUsuario().remove(getUsuario());
		limpar();
		Util.addInfoMessage("Remoção realizada com sucesso.");
	}
	
	public void editar(Usuario usu) {
		setUsuario(usu.getClone());
	}

	public void limpar() {
		usuario = null;
	}
	
	private int proximoId() {
		int resultado = 0;
		
		for (Usuario usuario : getListaUsuario()) {
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
	
	public TipoUsuario[] getListaTipoUsuario() {
		return TipoUsuario.values();
	}

}
