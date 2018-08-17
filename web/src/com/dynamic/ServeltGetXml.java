package com.dynamic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.dynamic.ent.Depto;
import com.dynamic.ent.Deptos;


@WebServlet("/GetXml")
public class ServeltGetXml extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ServeltGetXml(){
		super();
	}
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
		
		PrintWriter writer = response.getWriter();
		List<Depto> lstDepto = new ArrayList<Depto>(); 
		
		lstDepto.add(new Depto(1, "nome1", true));
		lstDepto.add(new Depto(2, "nome2", false));
		lstDepto.add(new Depto(3, "nome3", true));
		
		Deptos deptosData = new Deptos();
		deptosData.setDepto(lstDepto);
		
		String retorno = "";
		
		try {
			
			JAXBContext context = JAXBContext.newInstance(Deptos.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sWriter = new StringWriter();
			m.marshal(deptosData, sWriter);
			retorno = sWriter.toString();
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/xml; charset=UTF-8");
//		
//		JSONArray saida = new JSONArray(listaUsers);		
//		
		writer.println(retorno);
		writer.close();
	}
}
