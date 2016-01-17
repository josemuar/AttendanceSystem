package attendance_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import com.attendance.services.RESTServiceDirectory;


public class ApacheHttpClientPost {
	
	
	//public static String _SERVER = "http://52.10.24.166";
	public static String _SERVER = "http://127.0.0.1";
	public static String _PORT = "3000";
	private HttpPost _postRequest;
	private HttpResponse _httpResponse;
	private HttpClient _httpClient;
	private JSONObject _json_http_params = new JSONObject();
	
	
	/**
	 * Constructor
	 */
	public ApacheHttpClientPost() { 
		
		try {
			
			this._httpClient = HttpClientBuilder.create().build();
			this._postRequest = new HttpPost( ApacheHttpClientPost._SERVER + ":" + ApacheHttpClientPost._PORT + "/" + RESTServiceDirectory._REGISTER_EMPLOYEE_SERVICE  );
			this.get_postRequest().addHeader("content-type", "application/json");
			this.get_postRequest().addHeader("Accept","application/json");	
			
		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
		
		
	}
	
	
	/**
	 * @addPostParameter add a parameter to the Http Request object.
	 * @return void 
	 * @param key: The name of the parameter
	 * @param value: The value itself
	 */
	public void addPostParameter(String key, String value)
	{ 
		if ( key.isEmpty() == false & value.isEmpty() == false )
		{
			this.get_json_http_params().put(key, value );    	
		}	
	}
	
	
	
	/**
	 * @addPostParameter sent to the server the Http Post request along with the Json object.  (The Json object encapsulates the params) 
	 * The response from the server is stored in  _httpResponse class attribute. 
	 */
	public void sendHttpPostRequest()
	{ 
		try 
		{	
			StringEntity se = new StringEntity(this.get_json_http_params().toString());
			this.get_postRequest().setEntity(se);
			this._httpResponse = this.get_httpClient().execute( this.get_postRequest());
			
		}catch (IOException e) {

			e.printStackTrace();
		}
	}
	

	/**
	 * @checkRestServiceAvailability this function allows to know if the service passed as parameter is available for use.
	 * @param service_name whatever public  static attribute from the class @com.attendance.services.RESTServiceDirectory
	 * @return true if service is available and false otherwise 
	 */
	public boolean checkRestServiceAvailability(String service_name) {
	   
		boolean result = true;
		HttpPost postRequest;
		HttpResponse httpResponse;
		HttpClient httpClient;
		JSONObject json_http_params = new JSONObject();
		
		String raw_response_from_server = "";
		JSONObject json_response_from_server = new JSONObject();
		String line = ""; //aux var
		
		try 
		{		
			httpClient = HttpClientBuilder.create().build();
			postRequest = new HttpPost( ApacheHttpClientPost._SERVER + ":" + ApacheHttpClientPost._PORT + "/" + service_name );
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("Accept","application/json");	
			json_http_params.put("request_purpose", "test_service" );  
			StringEntity se = new StringEntity(json_http_params.toString());
			postRequest.setEntity(se);
			
			httpResponse = httpClient.execute( postRequest);	
			 
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ httpResponse.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader( new InputStreamReader((httpResponse.getEntity().getContent())));

			while ((line = br.readLine()) != null) {
				raw_response_from_server += line;
			}

			json_response_from_server = new JSONObject(raw_response_from_server); 
		}
		
		catch( ClientProtocolException e )
		{
			result = false;
		}
		
		catch( IOException e ) 
		{
			result = false;
		}
		
		return result;
		
	}
	
	public boolean checkRestListingService(String service_name) {
		   
		boolean result = true;
		HttpPost postRequest;
		HttpResponse httpResponse;
		HttpClient httpClient;
		JSONObject json_http_params = new JSONObject();
		
		String raw_response_from_server = "";
		JSONObject json_response_from_server = new JSONObject();
		String line = ""; //aux var
		
		try 
		{		
			httpClient = HttpClientBuilder.create().build();
			postRequest = new HttpPost( ApacheHttpClientPost._SERVER + ":" + ApacheHttpClientPost._PORT + "/" + service_name );
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("Accept","application/json");	  
			json_http_params.put("business_id", "IDENTIFIER1234567890" ); 
			StringEntity se = new StringEntity(json_http_params.toString());
			postRequest.setEntity(se);
			
			httpResponse = httpClient.execute( postRequest);	
			 
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ httpResponse.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader( new InputStreamReader((httpResponse.getEntity().getContent())));

			while ((line = br.readLine()) != null) {
				raw_response_from_server += line;
			}

			json_response_from_server = new JSONObject(raw_response_from_server);
			System.out.println(raw_response_from_server);
		}
		
		catch( ClientProtocolException e )
		{
			result = false;
		}
		
		catch( IOException e ) 
		{
			result = false;
		}
		
		return result;
		
	}



	public HttpPost get_postRequest() {
		return _postRequest;
	}


	public void set_postRequest(HttpPost _postRequest) {
		this._postRequest = _postRequest;
	}


	public HttpClient get_httpClient() {
		return _httpClient;
	}


	public void set_httpClient(HttpClient _httpClient) {
		this._httpClient = _httpClient;
	}


	public JSONObject get_json_http_params() {
		return _json_http_params;
	}


	public void set_json_http_params(JSONObject _json_http_params) {
		this._json_http_params = _json_http_params;
	}


	public HttpResponse getHttpResponse() {
		return _httpResponse;
	}


	public void setHttpResponse(HttpResponse httpResponse) {
		this._httpResponse = httpResponse;
	}
	
	
	

}