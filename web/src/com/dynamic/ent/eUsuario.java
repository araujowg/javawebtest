package com.dynamic.ent;

public class eUsuario {
	private int usuarioId;
	private String nome;
	private boolean ativo;
	
	public eUsuario(int usuarioId, String nome, boolean ativo){
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
