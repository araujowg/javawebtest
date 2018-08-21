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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String data ="";
		PrintWriter writer = response.getWriter();
		
		com.dynamic.data.profile.users user = new com.dynamic.data.profile.users();
		
		List<com.dynamic.ent.Usuario> lstUsers = new ArrayList<com.dynamic.ent.Usuario>();
		
		lstUsers.add(new Usuario(2, "teste02", false));
		lstUsers.add(new Usuario(1, "teste01", true));
		lstUsers.add(new Usuario(4, "teste04", true));
		lstUsers.add(new Usuario(3, "teste03", false));
		
		try {
			data = user.addUser(lstUsers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
//		JSONArray saida = new JSONArray(data);
		
		writer.println(data);
		writer.close();

	
	}
}
