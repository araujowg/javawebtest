package com.fws.br.profile.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// String login = request.getParameter("login");
		// String pwd = request.getParameter("pwd");
		// String out = "";
		// br.com.fws.user.Login core = new br.com.fws.user.Login();
		//
		// try {
		// EnumLogin info = core.doLogin(login, pwd);
		// out = info.getMessage();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// response.setContentType("application/json");
		// PrintWriter writer = response.getWriter();
		// writer.println(out);
		// writer.close();
	}
}
