package br.unitins.books.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

public class Usuario extends Entity<Usuario> {

	private static final long serialVersionUID = 3107387025516112284L;

	private String nome;
	
	@Past(message = "A data n�o pode estar no futuro.")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "O login deve ser informado.")
	private String login;
	
	private String senha;
	
	@Email(message= "E-mail inv�lido.")
	@NotEmpty(message = "O E-mail deve ser informado.")
	private String email;
	
	private TipoUsuario tipoUsuario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
