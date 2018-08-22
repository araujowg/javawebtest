package com.dynamic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.dynamic.ent.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ServletGetDynamoDB
 */
@WebServlet("/ServletGetDynamoDB")
public class ServletGetDynamoDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletGetDynamoDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String data = "";
		response.setContentType("application/json");

		PrintWriter writer = response.getWriter();
		List<com.dynamic.ent.Usuario> lstUsers = new ArrayList<com.dynamic.ent.Usuario>();

		try {

			com.dynamic.data.profile.users user = new com.dynamic.data.profile.users();

			// Include Methods

			lstUsers.add(new Usuario(2, "teste02", "log2", "12/12/1994", false));
			lstUsers.add(new Usuario(1, "teste01", "log1", "11/12/1994", true));
			lstUsers.add(new Usuario(4, "teste04", "log4", "14/12/1994", true));
			lstUsers.add(new Usuario(3, "teste03", "log3", "13/12/1994", false));
			user.addUser(lstUsers);

			lstUsers.add(new Usuario(7, "teste07", "log7", "17/12/1994", false));
			lstUsers.add(new Usuario(5, "teste05", "log5", "15/12/1994", true));
			lstUsers.add(new Usuario(8, "teste08", "log8", "18/12/1994", false));
			lstUsers.add(new Usuario(9, "teste09", "log9", "19/12/1994", false));
			lstUsers.add(new Usuario(6, "teste06", "log6", "16/12/1994", true));
			user.addUserWithAttributeValuesMethod(lstUsers);

			// Queries and JsonReturn
			Usuario usuario = user.getUserByIdAndLogin(4, "log8");
			ObjectMapper objMapper = new ObjectMapper();
			String out = objMapper.writeValueAsString(usuario);
			writer.println(out);

			List<Usuario> users = user.getUserByName("8");
			JSONArray saida = new JSONArray(users);
			writer.println(saida);

			users = user.getUserByQueryId(4);
			out = objMapper.writeValueAsString(usuario);
			writer.println(out);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		writer.close();

	}
}
