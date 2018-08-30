package com.fws.br.profile.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String login = request.getParameter("login");
		// String nome = request.getParameter("nome");
		// String email = request.getParameter("email");
		// String password = request.getParameter("pwd");
		//
		// UserInfo userInfo = new UserInfo("", login, password, nome, email,
		// true, false);
		// br.com.fws.user.User core = new br.com.fws.user.User();
		//
		// String out = "";
		//
		// try {
		//
		// if (core.addUser(userInfo)) {
		// out = "Inclusão realizada com sucesso!";
		// } else {
		// out = "A inclusão não pode ser realizada, por favor tente
		// novamente!";
		// }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// PrintWriter writer = response.getWriter();
		// writer.println(out);
		// writer.close();
	}
}
