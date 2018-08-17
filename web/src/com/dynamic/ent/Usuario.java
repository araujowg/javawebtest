package com.dynamic.ent;

public class Usuario {
	private int usuarioId;
	private String nome;
	private boolean ativo;
	
	public Usuario(int usuarioId, String nome, boolean ativo){
		setUsuarioId(usuarioId);
		setNome(nome);
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
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	

}
