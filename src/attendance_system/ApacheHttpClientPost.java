package attendance_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;


public class ApacheHttpClientPost {

	public ApacheHttpClientPost() { }


	/**
	 * @saveTagSerial saves in the Amazon Db the TAG serial passed as parameter. all this through a rest service (saving-smarttragserial-rest-service).
	 * @param serial
	 * @return the response from the server in JSON format
	 */
	public String saveTagSerial(String serial)
	{
		String line = "";
		String response_from_server = "";
		JSONObject json_from_server = new JSONObject();
		
		
		try {
			JSONObject json = new JSONObject();
			json.put("tagserialnumber", serial );    

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost("http://52.10.24.166:3000/saving-smarttragserial-rest-service/");
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("Accept","application/json");
			StringEntity se = new StringEntity(json.toString());
			postRequest.setEntity(se);
			HttpResponse response = httpClient.execute(postRequest);

			
			if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader( new InputStreamReader((response.getEntity().getContent())));

			while ((line = br.readLine()) != null) {
				response_from_server += line;
			}

			json_from_server = new JSONObject(response_from_server); 
			System.out.println( "results  " + json_from_server.get("result") );

			httpClient.getConnectionManager().shutdown();
		
			

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return  "" + json_from_server.get("result");

	}
	
	
	/**
	 * @saveActivationCode saves in the Amazon Db the Activation Code passed as parameter. all this through a rest service (saving-activation-code-rest-service).
	 * @param serial
	 * @return the response from the server in JSON format
	 */
	public String saveActivationCode(String serial)
	{
		String line = "";
		String response_from_server = "";
		JSONObject json_from_server = new JSONObject();

		try {
			JSONObject json = new JSONObject();
			json.put("activation_code", serial );    

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost("http://52.10.24.166:3000/saving-activation-code-rest-service/");
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("Accept","application/json");
			StringEntity se = new StringEntity(json.toString());
			postRequest.setEntity(se);
			HttpResponse response = httpClient.execute(postRequest);

			
			if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader( new InputStreamReader((response.getEntity().getContent())));

			while ((line = br.readLine()) != null) {
				response_from_server += line;
			}

			json_from_server = new JSONObject(response_from_server); 
			System.out.println( "result: " + json_from_server.get("result") );

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		
		return  "" + json_from_server.get("result");

	}
	
	
	/**
	 * @saveActivationCode pairs a TAG with its Activation Code. One Serial TAG is tied to a only Activation Code. all this through a rest service (pairing-tag-rest-service).
	 * @param serial
	 * @return the response from the server in JSON format
	 */
	public String pairingTAG(String tagserialnumber, String activationcodeserialnumber)
	{
		String line = "";
		String response_from_server = "";
		JSONObject json_from_server = new JSONObject();
		
		
		try {
			JSONObject json = new JSONObject();
			json.put("tagserialnumber", tagserialnumber);
			json.put("activation_code", activationcodeserialnumber );  

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost("http://52.10.24.166:3000/pairing-tag-rest-service/");
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("Accept","application/json");
			StringEntity se = new StringEntity(json.toString());
			postRequest.setEntity(se);
			HttpResponse response = httpClient.execute(postRequest);

			
			if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader( new InputStreamReader((response.getEntity().getContent())));

			while ((line = br.readLine()) != null) {
				response_from_server += line;
			}

			json_from_server = new JSONObject(response_from_server); 
			System.out.println( "results  " + json_from_server.get("result") );

			httpClient.getConnectionManager().shutdown();
		
			

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return  "" + json_from_server.get("result");

	}
	
	/**
	 * @getInactiveSmartCardList get the List of all the Smart Card Registered (however inactive).
	 * @return the response from the server in JSON format
	 */
	public JSONObject getInactiveSmartCardList()
	{
		String line = "";
		String response_from_server = "";
		JSONObject json_from_server = new JSONObject();
		JSONObject json_items = new JSONObject();
		
		try {
			JSONObject json = new JSONObject();

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost("http://52.10.24.166:3000/listing-inactive-tag-rest-service/");
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("Accept","application/json");
			StringEntity se = new StringEntity(json.toString());
			postRequest.setEntity(se);
			HttpResponse response = httpClient.execute(postRequest);

			
			if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader( new InputStreamReader((response.getEntity().getContent())));

			while ((line = br.readLine()) != null) {
				response_from_server += line;
			}
			
			json_from_server = new JSONObject(response_from_server); 
			json_items = json_from_server.getJSONObject("result"); 
			
			httpClient.getConnectionManager().shutdown();
			
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return  json_items;

	}
	
	
	/**
	 * @sendMembresyInvitation send a membresy invitation to the smartphone which hold the card or Tag identified by  the serial passed as parameter)
	 */
	public void sendMembresyInvitation(String  tagserialnumber)
	{
		String line = "";
		String response_from_server = "";
		JSONObject json_from_server = new JSONObject();
		JSONObject json_items = new JSONObject();
		
		try {
			JSONObject json = new JSONObject();
			json.put("tagserialnumber", tagserialnumber);

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost("http://52.10.24.166:3000/send-membresy-invitation/");
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("Accept","application/json");
			StringEntity se = new StringEntity(json.toString());
			postRequest.setEntity(se);
			HttpResponse response = httpClient.execute(postRequest);

			
			if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader( new InputStreamReader((response.getEntity().getContent())));

			while ((line = br.readLine()) != null) {
				response_from_server += line;
			}
			
		
			httpClient.getConnectionManager().shutdown();
			
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	
	

}