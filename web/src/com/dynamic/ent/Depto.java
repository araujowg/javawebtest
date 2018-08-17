package com.dynamic.ent;

import javax.xml.bind.annotation.XmlElement;

public class Depto {

	private int id;
	private String nome;
	private boolean ativo;
	
	public Depto(int id, String nome, boolean ativo){
		setId(id);
		setNome(nome);
		setAtivo(ativo);
	}
	
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@XmlElement
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
