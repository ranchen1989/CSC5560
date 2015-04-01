package com.ibm.cloudoe.samples;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;









import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.GetModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("/hello")
public class HelloResource {
	 
		@POST
		@Consumes({MediaType.TEXT_PLAIN})
		@Produces({MediaType.APPLICATION_JSON})
		public List<GetModel> getKdid(String requestvalue){
		
		final List<GetModel> answerlist = new ArrayList<GetModel>();
		JSONParser parser = new JSONParser();
		 //String data;
		
		try {
            //data = "text=Dylan was born in Duluth. ";
			//data=(String) request.getParameter("stemvalue");
			
            HttpURLConnection con = (HttpURLConnection) new URL("https://gate.d5.mpi-inf.mpg.de/aida/service/disambiguate").openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.getOutputStream().write(requestvalue.getBytes("UTF-8"));

            // Get the inputstream
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

//            // .. and print it
            String tmp;
            while((tmp = reader.readLine()) != null) {
                //System.out.println(tmp);
                
                Object obj = parser.parse(tmp);
                JSONObject jsonObject = (JSONObject) obj;
                String originalFileName = (String) jsonObject.get("originalFileName");
                String originalText = (String) jsonObject.get("originalText");
                String overallTime = (String) jsonObject.get("overallTime");
                JSONArray mentions = (JSONArray) jsonObject.get("mentions");
              
               
                 for(int i=0;i<mentions.size();i++)
                {
                //	System.out.println(mentions.get(i));
                	Object obj2 = parser.parse(mentions.get(i).toString());
                    JSONObject jsonObject2 = (JSONObject) obj2;
                    JSONArray allEntities = (JSONArray) jsonObject2.get("allEntities");
                    
                    for(int j=0;j<allEntities.size();j++)
                    {
                    	Object obj3 = parser.parse(allEntities.get(j).toString());
                    	JSONObject jsonObject3 = (JSONObject) obj3;
                    	String kdId = (String) jsonObject3.get("kbIdentifier");
                    	String disambiguationScore = (String) jsonObject3.get("disambiguationScore");
                    	//System.out.println("KbID: "+kdId + "===="+"score: "+disambiguationScore);
                    	 GetModel kk = new GetModel();
                    	 kk.setKdid(kdId);
                    	 kk.setScore(disambiguationScore);
                    	 answerlist.add(kk);
                    	 
                    }
                    
                }
                
                
           /*     Iterator<JSONArray> iterator2 = mentions.iterator();
                while (iterator2.hasNext()) {
        			
                	JSONArray tempArray =  (JSONArray)iterator2.next();
                	
                	for(int i=0;i<tempArray.size();i++)
                	{
                		System.out.println(tempArray.get(i));
                	}
        		}*/
                
              /*  JSONArray msg = (JSONArray) jsonObject.get("allEntities");
                Iterator<String> iterator = msg.iterator();
        		while (iterator.hasNext()) {
        			System.out.println("Entities: "+iterator.next());
        		}*/
         
                
             //  System.out.println(mentions);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return answerlist;
	}


}