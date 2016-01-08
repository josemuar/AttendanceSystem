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
import com.attendance.services.RESTServiceDirectory;

import attendance_system.ApacheHttpClientPost;
import attendance_system.TemplateForm;

/**
 * @author Jose Luis Munoz Aricapa
 * * According to GRASP patterns, applying MVC pattern
 */
public class EmployeeController {
	
	
	
	private TemplateForm form; 
	
	
	/**
	 * @newEmployee: allows to create a new Employee for the company or business
	 * @return void 
	 * @param employese: the Employee instance about to create or save
	 */
	public void newEmployee(Employee employee)
	{
		
		String line = ""; //aux var
		String raw_response_from_server = "";
		String outcome = "";
		JSONObject json_response_from_server = new JSONObject();
		
		
		try
		{
		
			ApacheHttpClientPost client = new ApacheHttpClientPost();
			client.addPostParameter("first_name", employee.get_first_name() );
			client.addPostParameter("last_name", employee.get_last_name() );
			client.addPostParameter("email", employee.get_email() );
			client.addPostParameter("type", employee.get_type() );
			client.addPostParameter("type", employee.get_type() );
			client.addPostParameter("request_purpose", "register_employee" );
			
			
			if( client.checkRestServiceAvailability( RESTServiceDirectory._REGISTER_EMPLOYEE_SERVICE) )
			{
				
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
				
				if ( json_response_from_server.has("outcome") )
				{
					outcome = json_response_from_server.get("outcome").toString();
					
					if (outcome.equals("technical_error") )
					{
						this.form.showErrorMessage("THE RECORD WAS NOT SAVED. PLEASE CONTACT TECHNICAL SUPPORT.", "");
						
					}
					
					if (outcome.equals("duplicity_error") )
					{
						
						this.form.showErrorMessage("THE RECORD WAS NOT SAVED. THE EMAIL GIVEN IS ALREADY STORE", "");
					}
					
					if (outcome.equals("true") )
					{
						this.form.showInformationMessage("THE RECORD WAS SAVED SUCCESFULLY", "");
						this.form.clerAllFields();
					}
				}
				else
				{
					
					this.form.showErrorMessage("NO RESPONSE WAS RECEIVED FROM THE SERVICE. PLEASE CONTACT TECHNICAL SUPPORT", "");
				}
				
		
				client.get_httpClient().getConnectionManager().shutdown();
			}
			
			else
				
			{
				this.form.showErrorMessage("THE REQUIRED SERVICE IS CURRENTLY DOWN", "");
			}
				
			
		} 
		
		catch (Exception e) 
		
		{

			System.out.println(e.getMessage());
		}
		
		
		//System.out.println( json_from_server.get("result") );
		
		
	}
	
	
	public TemplateForm getForm() {
		return form;
	}


	public void setForm(TemplateForm form) {
		this.form = form;
	}
	
	
	

}
