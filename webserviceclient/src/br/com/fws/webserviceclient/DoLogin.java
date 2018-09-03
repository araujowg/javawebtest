package br.com.fws.webserviceclient;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.WebServiceRef;

import br.com.fws.user.core.Login;
import br.com.fws.user.core.LoginServiceLocator;

/**
 * Servlet implementation class login
 */
@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/Login.wsdl")
@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoLogin() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String out = "";

		PrintWriter writter = response.getWriter();
		try {

			Login service = new LoginServiceLocator().getLogin();

			out = service.doLogin(login, password);
			writter.println(out);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			writter.println(e);
		}
	}
}
