/**
 * 
 */
package com.attendance.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashMap;

import javax.swing.JFrame;

import org.json.JSONObject;

import com.attendance.models.Business;
import com.attendance.models.Employee;
import com.attendance.services.RESTServiceDirectory;
import com.attendance.views.DeleteEmployeeForm;
import com.attendance.views.NewEmployeeForm;
import com.attendance.views.UpdateEmployeeForm;

import attendance_system.ApacheHttpClientPost;
import attendance_system.MainUI;
import attendance_system.TemplateForm;

/**
 * @author Jose Luis Munoz Aricapa
 * * According to GRASP patterns, applying MVC pattern
 */
public class EmployeeController {
	
	
	
	private TemplateForm form; 
	private MainUI mainUI;
	
	
	
	
	
	
	/**
	 * @newEmployeeForm: shows the Form to create a new Employee
	 */
	public void newEmployeeForm()
	{
		NewEmployeeForm  form = new NewEmployeeForm();
		form.setController(this);
		form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		form.setVisible(true);
	}
	

	/**
	 * @updateEmployeeForm: shows the Form to update the information on a specific Employee
	 */
	public void updateEmployeeForm(HashMap hashMap)
	{
		UpdateEmployeeForm  form = new UpdateEmployeeForm();
		form.setController(this);
		form.setHashMap(hashMap);
		form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		form.setVisible(true);
	}
	
	
	/**
	 * @deleteEmployeeForm: shows the Form to delete an Employee from the system
	 */
	public void deleteEmployeeForm(HashMap hashMap)
	{
		DeleteEmployeeForm  form = new DeleteEmployeeForm();
		form.setController(this);
		form.setHashMap(hashMap);
		form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		form.setVisible(true);
	}
	
	
	
	/**
	 * @newEmployee: allows to create a new Employee for the company or business
	 * @return void 
	 * @param employese: the Employee instance about to create or save
	 */
	public void newEmployee(Employee employee)
	{
		
		String line = "";
		String raw_response_from_server = "";
		String outcome = "";
		JSONObject json_response_from_server = new JSONObject();
		Business business = new Business();
		
		
		try
		{
		
			ApacheHttpClientPost client = new ApacheHttpClientPost(RESTServiceDirectory._REGISTER_EMPLOYEE_SERVICE);
			client.addPostParameter("first_name", employee.get_first_name() );
			client.addPostParameter("last_name", employee.get_last_name() );
			client.addPostParameter("email", employee.get_email() );
			client.addPostParameter("business_id", business.get_ID() );
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
						this.getMainUI().updateEmployeeTableComponent();
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
				
	}
	
	
	/**
	 * @updateEmployee: allows to update a employee information
	 * @return void 
	 * @param employese: the Employee instance which contains the new values
	 */
	public void updateEmployee(Employee employee)
	{
		
		String line = "";
		String raw_response_from_server = "";
		String outcome = "";
		JSONObject json_response_from_server = new JSONObject();
		Business business = new Business();
		
		
		try
		{
		
			ApacheHttpClientPost client = new ApacheHttpClientPost(RESTServiceDirectory._UPDATE_EMPLOYEE_INFORMATION_SERVICE);
			client.addPostParameter("email", employee.get_email() );
			client.addPostParameter("first_name", employee.get_first_name() );
			client.addPostParameter("last_name", employee.get_last_name() );
			client.addPostParameter("type", employee.get_type() );
			client.addPostParameter("request_purpose", "update_employee" );
			
			
			if( client.checkRestServiceAvailability( RESTServiceDirectory._UPDATE_EMPLOYEE_INFORMATION_SERVICE) )
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
						this.form.showErrorMessage("THE RECORD WAS NOT UPDATED. PLEASE CONTACT TECHNICAL SUPPORT.", "");
						System.out.println(json_response_from_server.get("error").toString());
						
					}
					
					if (outcome.equals("duplicity_error") )
					{
						
						this.form.showErrorMessage("THE RECORD WAS NOT SAVED. THE EMAIL GIVEN IS ALREADY STORED", "");
					}
					
					if (outcome.equals("true") )
					{
						this.form.showInformationMessage("THE RECORD WAS SUCCESFULLY UPDATED", "");
						this.getMainUI().updateEmployeeTableComponent();
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
				
	}
	
	
	/**
	 * @deleteEmployee: allows to delete an Employee from the system
	 * @return void 
	 * @param employese: the Employee instance with the information of the employee who needs no be deleted
	 */
	public void deleteEmployee(Employee employee)
	{
		
		String line = "";
		String raw_response_from_server = "";
		String outcome = "";
		JSONObject json_response_from_server = new JSONObject();
		Business business = new Business();
		
		
		try
		{
		
			ApacheHttpClientPost client = new ApacheHttpClientPost(RESTServiceDirectory._DELETE_EMPLOYEE_SERVICE);
			client.addPostParameter("email", employee.get_email() );
			client.addPostParameter("first_name", employee.get_first_name() );
			client.addPostParameter("last_name", employee.get_last_name() );
			client.addPostParameter("type", employee.get_type() );
			client.addPostParameter("request_purpose", "delete_employee" );
			
			
			if( client.checkRestServiceAvailability( RESTServiceDirectory._DELETE_EMPLOYEE_SERVICE) )
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
						this.form.showErrorMessage("THE RECORD WAS NOT DELETE. PLEASE CONTACT TECHNICAL SUPPORT.", "");
						System.out.println(json_response_from_server.get("error").toString());						
					}
					
					if (outcome.equals("non_existent_record") )
					{
						this.form.showErrorMessage("NON EXISTENT RECORD", "");
						System.out.println(json_response_from_server.get("error").toString());						
					}
					

					if (outcome.equals("true") )
					{
						this.form.showInformationMessage("THE RECORD WAS SUCCESFULLY DELETE", "");
						this.getMainUI().updateEmployeeTableComponent();
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
				
	}
	
	public TemplateForm getForm() {
		return form;
	}


	public void setForm(TemplateForm form) {
		this.form = form;
	}



	public MainUI getMainUI() {
		return mainUI;
	}



	public void setMainUI(MainUI mainUI) {
		this.mainUI = mainUI;
	}
	
	
	

}
