package br.com.fws.webserviceclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.WebServiceRef;

import br.com.fws.user.core.User;
import br.com.fws.user.core.UserServiceLocator;
import br.com.fws.user.entity.UserInfo;

/**
 * Servlet implementation class Users
 */
@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/User.wsdl")
@WebServlet("/Users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<UserInfo> userList = new ArrayList<>();
		try {
			User service = new UserServiceLocator().getUser();

			Object[] objects = service.getAllUsers();

			if (userList != null)
				for (Object object : objects) {
					UserInfo user = (UserInfo) object;
					userList.add(user);
				}

			request.setAttribute("userList", userList);
			request.getRequestDispatcher("users.jsp").forward(request, response);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("UserId");
		String name = request.getParameter("Name");
		String birthDate = request.getParameter("birthDate");
		try {
			User service = new UserServiceLocator().getUser();
			service.updateUser(new UserInfo(birthDate, null, null, name, userId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
