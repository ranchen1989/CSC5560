package com.ibm.cloudoe.samples;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;


//@Path("/hello")
public class HelloResource2 extends HttpServlet {

	@GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);


	}
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String stemm = request.getParameter("stemvalue");
		 String answer = stem(stemm);
		 request.setAttribute("answer", answer);
		 System.out.println(request.getAttribute(answer));
		 
		 request.getRequestDispatcher("main.jsp").forward(request, response);

		}


	public static String stem(String string) throws IOException {
		 TokenStream tokenizer = new StandardTokenizer(Version.LUCENE_44, new StringReader(string));
	    tokenizer = new StandardFilter(Version.LUCENE_44, tokenizer);
	    tokenizer = new LowerCaseFilter(Version.LUCENE_44, tokenizer);
	    tokenizer = new PorterStemFilter(tokenizer);

	    CharTermAttribute token = tokenizer.getAttribute(CharTermAttribute.class);

	    tokenizer.reset();

	    StringBuilder stringBuilder = new StringBuilder();

	    while(tokenizer.incrementToken()) {
	        if(stringBuilder.length() > 0 ) {
	            stringBuilder.append(" ");
	        }

	        stringBuilder.append(token.toString());
	    }

	    tokenizer.end();
	    tokenizer.close();

	    return stringBuilder.toString();
	}
	
}