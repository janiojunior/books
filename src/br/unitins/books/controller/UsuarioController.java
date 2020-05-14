package br.unitins.books.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.application.Util;
import br.unitins.books.dao.UsuarioDAO;
import br.unitins.books.model.TipoUsuario;
import br.unitins.books.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -563227102506849534L;

	private Usuario usuario;
	private List<Usuario> listaUsuario;
	
	public void incluir() {
		if (getUsuario().getNome().isBlank()) {
			Util.addErrorMessage("O campo nome deve ser informado.");
			return;
		}
		
		UsuarioDAO dao = new UsuarioDAO();
		if (dao.create(getUsuario())) {
			limpar();
			Util.addInfoMessage("Inclusão realizada com sucesso.");
		} else {
			Util.addInfoMessage("Erro ao incluir no banco de dados.");
		}
		
 	}

	public void alterar() {
		if (getUsuario().getNome().isBlank()) {
			Util.addErrorMessage("O campo nome deve ser informado.");
			return;
		}
		
		UsuarioDAO dao = new UsuarioDAO();
		if (dao.update(getUsuario())) {
			limpar();
			Util.addInfoMessage("Alteração realizada com sucesso.");
		} else {
			Util.addInfoMessage("Erro ao alterar no banco de dados.");
		}
	}

	public void remover() {
		UsuarioDAO dao = new UsuarioDAO();
		if (dao.delete(getUsuario().getId())) {
			limpar();
			Util.addInfoMessage("Remoção realizada com sucesso.");
		} else {
			Util.addInfoMessage("Erro ao remover no banco de dados.");
		}
	}
	
	public void editar(Usuario usu) {
		UsuarioDAO dao = new UsuarioDAO();
		usu = dao.findById(usu.getId());
		setUsuario(usu);
	}

	public void limpar() {
		usuario = null;
		listaUsuario = null;
	}
	

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuario = dao.findAll();
		}
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
