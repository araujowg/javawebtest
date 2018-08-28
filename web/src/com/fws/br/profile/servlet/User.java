package com.fws.br.profile.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fws.br.user.entity.UserInfo;

@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String password = request.getParameter("pwd");

		UserInfo userInfo = new UserInfo("", login, password, nome, email, true, false);
		com.fws.br.user.User core = new com.fws.br.user.User();

		String out = "";

		try {

			if (core.addUser(userInfo)) {
				out = "Inclusão realizada com sucesso!";
			} else {
				out = "A inclusão não pode ser realizada, por favor tente novamente!";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		PrintWriter writer = response.getWriter();
		writer.println(out);
		writer.close();
	}
}
