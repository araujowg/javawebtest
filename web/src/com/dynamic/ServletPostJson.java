package com.dynamic;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.dynamic.ent.eUsuario;

/**
 * Servlet implementation class SendJsp
 */
//@WebServlet(urlPatterns= ("/SendJsp"), initParams={@WebInitParam(name = "test", value="10")})
@WebServlet("/SendJsp")
public class ServletPostJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	String appName ="";
//	int appValue = 0; 

//	@Override
//	public void init() throws ServletException {
//		appName = getInitParameter("name");
//		appValue = (Integer.parseInt(getInitParameter("value").toString()));
//	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPostJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text = request.getParameter("txtTeste");
		PrintWriter writer = response.getWriter();
		
		ArrayList<com.dynamic.ent.eUsuario> listaUsers = new ArrayList<com.dynamic.ent.eUsuario>();
		
		listaUsers.add(new eUsuario(1,"2", true));
		listaUsers.add(new eUsuario(2,"2", false));
		listaUsers.add(new eUsuario(3,"3", false));
		listaUsers.add(new eUsuario(4,text, false));
		response.setContentType("application/json");
		JSONArray saida = new JSONArray(listaUsers);
		
		writer.println(saida);
//		writer.print(appName);
//		writer.print(appValue);
		writer.close();
	}

}


