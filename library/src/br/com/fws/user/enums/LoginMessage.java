package br.com.fws.user.enums;

public enum LoginMessage {
	SUCCESS(1), FAILURE(2), EXECPTION(3), EMAILEXISTS(4), LOGINEXISTS(5), SUCCESSFULLYCREATED(6), FAILUREONCREATION(7);

	private final int valorEnum;

	LoginMessage(int valor) {
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
		case 4:
			return "Email já cadastrado!";
		case 5:
			return "Login já cadastrado!";
		case 6:
			return "Login criado com sucesso!";
		case 7:
			return "Falha ao criar o login!";
		default:
			return "Not implemented";
		}
	}
}