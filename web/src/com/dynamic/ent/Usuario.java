package com.dynamic.ent;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonRootName("user")
public class Usuario {

	private int usuarioId;
	private String nome;
	private String login;
	private String birthDate;
	private boolean ativo;

	public Usuario() {
	}

	public Usuario(int usuarioId, String nome, String login, String birthDate, boolean ativo) {
		setUsuarioId(usuarioId);
		setNome(nome);
		setLogin(login);
		setBirthDate(birthDate);
		setAtivo(ativo);
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
