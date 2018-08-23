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
import com.fasterxml.jackson.core.JsonProcessingException;
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

		response.setContentType("application/json");

		PrintWriter writer = response.getWriter();
		List<com.dynamic.ent.Usuario> lstUsers = new ArrayList<com.dynamic.ent.Usuario>();

		try {

			com.dynamic.data.profile.users nUser = new com.dynamic.data.profile.users();
			ObjectMapper objMapper = new ObjectMapper();

			// Include Methods
			addUser(writer, lstUsers, nUser);

			addUserWithAttributeValuesMethod(writer, lstUsers, nUser);

			getUserByIdAndLogin(writer, nUser, objMapper);

			getUserByName(writer, nUser);

			getUserByQueryId(writer, nUser, objMapper);

			updateUser(writer, nUser, objMapper);

			getAllUsers(writer, nUser);

			getDisabledUsers(writer, nUser);

			deleteDisabledUsers(writer, nUser);

			getAllUsers(writer, nUser);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		writer.close();

	}

	private void deleteDisabledUsers(PrintWriter writer, com.dynamic.data.profile.users nUser) throws Exception {
		writer.println("Inicio....");
		writer.println("Delete getAllUsers with ScanRequest example");
		nUser.deleteDisabledUsers();
		writer.println("Término....");
		writer.println("<br/>");
	}

	private void updateUser(PrintWriter writer, com.dynamic.data.profile.users nUser, ObjectMapper objMapper)
			throws Exception {
		Usuario user;
		String out;
		writer.println("Inicio....");
		writer.println("Update Update with UpdateItemSpec example");
		user = new Usuario(4, "teste08", "log10", "10/12/1994", true);
		out = objMapper.writeValueAsString(user);
		writer.println("before:" + out);
		user = nUser.updateUser(new Usuario(4, "teste10", "log10", "", false));
		out = objMapper.writeValueAsString(user);
		writer.println("after:" + out);
		writer.println("Término....");
	}

	private void addUser(PrintWriter writer, List<com.dynamic.ent.Usuario> lstUsers,
			com.dynamic.data.profile.users nUser) throws Exception {
		JSONArray saida;
		// Note : (ignora inclusão se chaves duplicadas)
		writer.println("Inicio....");
		writer.println("Insert utilizando table.putItem    ");
		lstUsers.add(new Usuario(2, "teste02", "log2", "12/12/1994", false));
		lstUsers.add(new Usuario(1, "teste01", "log1", "11/12/1994", true));
		lstUsers.add(new Usuario(4, "teste04", "log4", "14/12/1994", true));
		lstUsers.add(new Usuario(3, "teste03", "log3", "13/12/1994", false));
		lstUsers.add(new Usuario(4, "teste08", "log10", "10/12/1994", true));

		nUser.addUser(lstUsers);
		saida = new JSONArray(lstUsers);
		writer.println(saida);
		writer.println("Término....");
	}

	private void getUserByQueryId(PrintWriter writer, com.dynamic.data.profile.users nUser, ObjectMapper objMapper)
			throws Exception {
		List<Usuario> users;

		String out;
		writer.println("Inicio....");
		writer.println("Query getUserByQueryId with QuerySpec example");
		users = nUser.getUserByQueryId(4);
		out = objMapper.writeValueAsString(users);
		writer.println(out);
		writer.println("Término....");
	}

	private void addUserWithAttributeValuesMethod(PrintWriter writer, List<com.dynamic.ent.Usuario> lstUsers,
			com.dynamic.data.profile.users nUser) throws Exception {
		JSONArray saida;
		writer.println("Inicio....");
		writer.println("Insert utilizando client/WithAttributeValues");
		lstUsers.clear();
		lstUsers.add(new Usuario(7, "teste07", "log7", "17/12/1994", false));
		lstUsers.add(new Usuario(5, "teste05", "log5", "15/12/1994", true));
		lstUsers.add(new Usuario(8, "teste08", "log8", "18/12/1994", false));
		lstUsers.add(new Usuario(9, "teste09", "log9", "19/12/1994", false));
		lstUsers.add(new Usuario(6, "teste06", "log6", "16/12/1994", true));
		nUser.addUserWithAttributeValuesMethod(lstUsers);
		saida = new JSONArray(lstUsers);
		writer.println(saida);
		writer.println("Término....");
	}

	private void getUserByName(PrintWriter writer, com.dynamic.data.profile.users nUser) throws Exception {
		List<Usuario> users;
		JSONArray saida;
		writer.println("Inicio....");
		writer.println("Query getUserByName with ScanRequest example");
		users = nUser.getUserByName("8");
		saida = new JSONArray(users);
		writer.println(saida);
		writer.println("Término....");
	}

	private void getUserByIdAndLogin(PrintWriter writer, com.dynamic.data.profile.users nUser, ObjectMapper objMapper)
			throws Exception, JsonProcessingException {
		Usuario user;
		String out;
		writer.println("Inicio....");
		writer.println("Query  getUserByIdAndLogin (primary keys) with GetItemSpec example");
		// Queries and JsonReturn
		// Note: é necessário utilizar todas as chaves se houver mais de uma
		user = nUser.getUserByIdAndLogin(4, "log10");
		out = objMapper.writeValueAsString(user);
		writer.println(out);
		writer.println("Término....");
	}

	private void getAllUsers(PrintWriter writer, com.dynamic.data.profile.users nUser) throws Exception {
		List<Usuario> users;
		JSONArray saida;
		writer.println("Inicio....");
		writer.println("Query getAllUsers with ScanRequest example");
		users = nUser.getAllUsers();
		saida = new JSONArray(users);
		writer.println(saida);
		writer.println("Término....");
	}

	private void getDisabledUsers(PrintWriter writer, com.dynamic.data.profile.users nUser) throws Exception {
		List<Usuario> users;
		JSONArray saida;
		writer.println("Inicio....");
		writer.println("Query getDisabledUsers with ScanRequest example");
		users = nUser.getDisabledUsers();
		saida = new JSONArray(users);
		writer.println(saida);
		writer.println("Término....");
	}

}
