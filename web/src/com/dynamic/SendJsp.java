package com.dynamic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendJsp
 */
//@WebServlet(urlPatterns= ("/SendJsp"), initParams={@WebInitParam(name = "teste", value="10")})
@WebServlet("/SendJsp")
public class SendJsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	String appName ="";
//	int appValue = 0; 
    
	@Override
	public void init() throws ServletException {
//		appName = getInitParameter("name");
//		appValue = (Integer.parseInt(getInitParameter("value").toString()));
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendJsp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String text = request.getParameter("txtTeste");
		PrintWriter writer = response.getWriter();
		writer.println(text);
//		writer.print(appName);
//		writer.print(appValue);
		writer.close();
	}
}
