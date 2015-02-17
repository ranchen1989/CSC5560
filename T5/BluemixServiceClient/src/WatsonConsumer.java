

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.ektorp.CouchDbConnector;
import org.ektorp.http.HttpClient;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.personafusion.db.CloudantClient;
import com.ibm.personafusion.model.Person;
import com.ibm.personafusion.model.Trait;
import com.ibm.personafusion.service.WatsonUserModeller;


public class WatsonConsumer {
	private HttpClient httpClient;
	private CouchDbConnector dbc;
	
	
	
	
	private String username;
	private String password;
	private String base_url;
	private String profile_api;
	private Executor executor;

	
	private JSONArray cloudant;
	private JSONObject cloudantInstance;
	private JSONObject cloudantCredentials;
	
	public WatsonConsumer()
	{
		this.httpClient = null;
		 
		/* try {
           		String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
			JSONObject vcap;
			vcap = (JSONObject) JSONObject.parse(VCAP_SERVICES);
			
			cloudant = (JSONArray) vcap.get("cloudantNoSQLDB");
			cloudantInstance = (JSONObject) cloudant.get(0);
			cloudantCredentials = (JSONObject) cloudantInstance.get("credentials");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		this.port = Config.CLOUDANT_PORT;
		this.host = (String) cloudantCredentials.get("host");
		this.username = (String) cloudantCredentials.get("username");
		this.password = (String) cloudantCredentials.get("password");
		this.name = Config.CLOUDANT_NAME;
		this.dbc = this.createDBConnector();*/
		
		
		
		
		
		
		this.username = "cc96130e-52ac-4e3f-a585-76bff946947d";
		this.password="IlZhDPdB2iyo";
		this.base_url = "https://gateway.watsonplatform.net/systemu/service/";
		this.profile_api="/api/v2/profile";
		this.executor = Executor.newInstance().auth("cc96130e-52ac-4e3f-a585-76bff946947d", "IlZhDPdB2iyo");
		
	}
	
	
	public static void main(String[] args) throws Exception
	{
		WatsonUserModeller WUM = new WatsonUserModeller();
		
		List<Trait> traits = WUM.getTraitsList("Call me Ishmael. Some years ago-never mind how long precisely-"+
				"having little or no money in my purse, and nothing particular to interest me "+
				"on shore, I thought I would sail about a little and see the watery part of "+
				"the world. It is a way I have of driving off the spleen and regulating the "+
				"circulation. Whenever I find myself growing grim about the mouth; whenever it "+
				"is a damp, drizzly November in my soul; whenever I find myself involuntarily "+
				"pausing before coffin warehouses, and bringing up the rear of every funeral I "+
				"meet; and especially whenever my hypos get such an upper hand of me, that it "+
				"requires a strong moral principle to prevent me from deliberately stepping "+
				"into the street, and methodically knocking people's hats off-then, I account "+
				"it high time to get to sea as soon as I can.");
		
		for(int i=0;i<traits.size();i++)
		{
			System.out.println(traits.get(i));
		}
	}
	
	private String makePOST(String base, String suffix,String content)
	{
		System.out.println(content);
		try
		{
			URI uri = new URI(base + suffix).normalize();
			
			String profileJSON = executor.execute(Request.Post(uri)
					.setHeader("Accept", "application/json")
					.bodyString(content,ContentType.APPLICATION_JSON)
					).returnContent().asString();
			return profileJSON;
		} catch(Exception e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return "An error occured on POST to " + base + suffix;
	}
	
}
