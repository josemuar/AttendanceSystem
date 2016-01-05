/**
 * 
 */
package com.attendance.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.json.JSONObject;

import com.attendance.models.Employee;

import attendance_system.ApacheHttpClientPost;

/**
 * @author Jose Luis Munoz Aricapa
 * * According to GRASP patterns, applying MVC pattern
 */
public class EmployeeController {
	
	
	/**
	 * @newEmployee: allows to create a new Employee for the company or business
	 * @return void 
	 * @param employese: the Employee instance about to create or save
	 */
	public void newEmployee(Employee employee)
	{
		
		String line = ""; //aux var
		String raw_response_from_server = "";
		JSONObject json_response_from_server = new JSONObject();
		
		try
		{
		
			ApacheHttpClientPost client = new ApacheHttpClientPost();
			client.addPostParameter("first_name", employee.get_first_name() );
			client.addPostParameter("last_name", employee.get_last_name() );
			client.addPostParameter("email", employee.get_email() );
			client.addPostParameter("type", employee.get_type() );
			
			client.sendHttpPostRequest();
	
			if (client.getHttpResponse().getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ client.getHttpResponse().getStatusLine().getStatusCode());
			}
	
			BufferedReader br = new BufferedReader( new InputStreamReader((client.getHttpResponse().getEntity().getContent())));
	
			while ((line = br.readLine()) != null){
				raw_response_from_server += line;
			}
	
			json_response_from_server = new JSONObject(raw_response_from_server); 
			System.out.println( "results  " + json_response_from_server.get("result") );
			
			if (json_response_from_server.get("outcome") == "technical_error" )
			{
				
				
			}
			
			if (json_response_from_server.get("outcome") == "duplicity_error" )
			{
				
				
			}
			
			if (json_response_from_server.get("outcome") == "true" )
			{
				
				
			}
		
	
			client.get_httpClient().getConnectionManager().shutdown();
		} 
		
		catch (Exception e) 
		
		{

			System.out.println(e.getMessage());
		}
		
		
		//System.out.println( json_from_server.get("result") );
		
		
	}
	
	
	

}
