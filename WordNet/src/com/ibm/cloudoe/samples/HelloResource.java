package com.ibm.cloudoe.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rita.wordnet.RiWordnet;


@Path("/hello")
public class HelloResource extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);


	}
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Would pass in a PApplet normally, but we don't need to here
			RiWordnet wordnet = new RiWordnet(null);

			// Demo finding parts of speech
			String word = request.getParameter("steminput");
			request.setAttribute("answer",word);
			System.out.println(request.getAttribute("\nFinding parts of speech for " + word + "."));
			//System.out.println("\nFinding parts of speech for " + word + ".");
			String[] partsofspeech = wordnet.getPos(word);
			for (int i = 0; i < partsofspeech.length; i++) {
				request.setAttribute("answer1",partsofspeech[i]);
				System.out.println(request.getAttribute(partsofspeech[i]));        	
			}
		 
		 request.getRequestDispatcher("main.jsp").forward(request, response);

		}
}