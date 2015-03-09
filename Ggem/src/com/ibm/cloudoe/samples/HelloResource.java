package com.ibm.cloudoe.samples;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;



//@Path("/hello")
public class HelloResource extends HttpServlet {

	
	 /**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	static final int N = 2;
	
	    @GET
		public void  doGet(HttpServletRequest request, HttpServletResponse response)    throws IOException, ServletException{
			doPost(request, response);
		}
		public void  doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
			String paragraph=(String) request.getParameter("stemvalue");
			
			ClassLoader classLoader = getClass().getClassLoader();
			FileInputStream AAA = new FileInputStream(classLoader.getResource("en-token.bin").getFile());
			TokenizerModel tm=new TokenizerModel(AAA);
			TokenizerME wordBreaker=new TokenizerME(tm);
			
			
			 POSModel pm = new POSModel(new FileInputStream(classLoader.getResource("en-pos-maxent.bin").getFile()));
		     POSTaggerME posme = new POSTaggerME(pm);
		     
		     InputStream modelIn = new FileInputStream(classLoader.getResource("en-chunker.bin").getFile());
		      ChunkerModel chunkerModel = new ChunkerModel(modelIn);
		      ChunkerME chunkerME = new ChunkerME(chunkerModel);
			

//			 TokenizerModel tm = new TokenizerModel(aaa);
//		      TokenizerME wordBreaker = new TokenizerME(tm);
//		      POSModel pm = new POSModel(new FileInputStream("en-pos-maxent.bin"));
//		      POSTaggerME posme = new POSTaggerME(pm);
//		      InputStream modelIn = new FileInputStream("en-chunker.bin");
//		      ChunkerModel chunkerModel = new ChunkerModel(modelIn);
//		      ChunkerME chunkerME = new ChunkerME(chunkerModel);
		      //this is your sentence
//		      String sentence = "Barack Hussein Obama II is the 44th and current President of the United States, and the first African American to hold the office.";
//		      System.out.println(sentence);
//		      System.out.println("==============================================================");
//		  	System.out.println("After Processing:");
		      //words is the tokenized sentence
		      String[] words = wordBreaker.tokenize(paragraph);
		      //posTags are the parts of speech of every word in the sentence (The chunker needs this info of course)
		      String[] posTags = posme.tag(words);
		      //chunks are the start end "spans" indices to the chunks in the words array
		      Span[] chunks = chunkerME.chunkAsSpans(words, posTags);
		      //chunkStrings are the actual chunks
		      String[] chunkStrings = Span.spansToStrings(chunks, words);
		      for (int i = 0; i < chunks.length; i++) {
		        if (chunks[i].getType().equals("NP")) {
		          System.out.println("NP: \n\t" + chunkStrings[i]);
		          String[] split = chunkStrings[i].split(" ");

		          List<String> ngrams = ngram(Arrays.asList(split), N, " ");
//		          System.out.println("ngrams:");
		          
		          
		          request.setAttribute("answer", ngrams);
		         
		 		 for (String gram : ngrams) {
		            System.out.println(request.getAttribute("\t" + gram));
		          }		        
		}
		        request.getRequestDispatcher("main.jsp").forward(request, response);
	}
		}
		 public static List<String> ngram(List<String> input, int n, String separator) {
			    if (input.size() <= n) {
			      return input;
			    }
			    List<String> outGrams = new ArrayList<String>();
			    for (int i = 0; i < input.size() - (n - 2); i++) {
			      String gram = "";
			      if ((i + n) <= input.size()) {
			        for (int x = i; x < (n + i); x++) {
			          gram += input.get(x) + separator;
			        }
			        gram = gram.substring(0, gram.lastIndexOf(separator));
			        outGrams.add(gram);
			      }
			    }
			    return outGrams;
			  }
}