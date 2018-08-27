package com.fws.br.profile.commom.enums;

public enum EnumLogin {
	SUCCESS(1), FAILURE(2), EXECPTION(3);

	private final int valorEnum;

	EnumLogin(int valor) {
		valorEnum = valor;
	}

	public int getValor() {
		return valorEnum;
	}

	public String getMessage() {

		switch (valorEnum) {
		case 1:
			return "Login realizado com sucesso!";
		case 2:
			return "Login ou senha inválida!";
		case 3:
			return "Ocorreu um erro ao tentar realizar o login!";
		default:
			return "Not implemented";
		}
	}
}