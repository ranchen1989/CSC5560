package com.ibm.cloudoe.samples;
/*
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;
@Path("/hello")
public class HelloResource {

	
	public static void main(String args[]) throws InvalidFormatException, IOException
	{
		SentenceDetector();
		//Tokenize();
		//findPersonName();
		//findLocationName();
		//findOrganizationName();
		//POSTag();
		//Parse();
	}
	@GET*/







import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;


@Path("/api/hello")
public class HelloResource 
{
	String r = SentenceDetector() ;
//	SentenceDetector() 
	//Tokenize();
	//findPersonName();
	//findLocationName();
	//findOrganizationName();
	//POSTag();
	//Parse();
	
	@GET
	public Response handle()
	{
		return Response.ok("r").build();
	}
		
		public String SentenceDetector(){
				String paragraph = "Hi. How are you? This is Mike.";
				
				// always start with a model, a model is learned from training data
				InputStream is;
				try {
					is = new FileInputStream("en-sent.bin");
					SentenceModel model = null;
					try {
						model = new SentenceModel(is);
					} catch (InvalidFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					SentenceDetectorME sdetector = new SentenceDetectorME(model);
					
					String sentences[] = sdetector.sentDetect(paragraph);
					
					System.out.println("Before Processing: ");
					System.out.println(paragraph);
					
					System.out.println("============================");
					
					System.out.println("After Processing: ");
					System.out.println(sentences[0]);
					System.out.println(sentences[1]);
					is.close();
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			return "";
				}
		

}






