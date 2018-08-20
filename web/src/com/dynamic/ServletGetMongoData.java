package com.dynamic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.json.JSONArray;

/**
 * Servlet implementation class ServletGetMongoData
 */
@WebServlet("/ServletGetMongoData")
public class ServletGetMongoData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGetMongoData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		
		com.dynamic.data.MongoBase mongo = new com.dynamic.data.MongoBase();
		
		List<Document> data = mongo.getAll();
		
		response.setContentType("application/json");
		JSONArray saida = new JSONArray(data);
		
		writer.println(saida);
		writer.close();
	}

}
