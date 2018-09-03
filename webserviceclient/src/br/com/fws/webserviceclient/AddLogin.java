package br.com.fws.webserviceclient;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

import br.com.fws.user.core.Login;
import br.com.fws.user.core.LoginServiceLocator;

/**
 * Servlet implementation class AddLogin
 */
@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/Login.wsdl")
@WebServlet("/AddLogin")
public class AddLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddLogin() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("addLogin");
		String email = request.getParameter("addEmail");
		String password = request.getParameter("addPassword");
		String out = "";
		PrintWriter writer = response.getWriter();

		try {

			Login service = new LoginServiceLocator().getLogin();
			out = service.addLogin(login, password, email);

			writer.println(out);

		} catch (Exception e) {
			e.printStackTrace();
			writer.println(e);
		}

	}
}
