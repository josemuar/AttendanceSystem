package com.attendance.tools;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.attendance.controller.EmployeeController;
import com.attendance.models.Business;
import com.attendance.services.RESTServiceDirectory;

import attendance_system.ApacheHttpClientPost;
import attendance_system.TemplateForm;


public class EmployeeTable extends JTable {
	
	private JTableModel model;
	private TemplateForm form; 
	
	public EmployeeController controller;
	
	/**
	 * Constructor
	 */
	public EmployeeTable()
	{
	
		
	}
	
	
	/**
	 * @populate: populate the table with elements. Registered employees in the system.
	 * @return void 
	 */
	public void populate()
	{

		HttpPost postRequest;
		HttpResponse httpResponse;
		HttpClient httpClient;
		JSONObject json_http_params = new JSONObject();
		
		String raw_response_from_server = "";
		JSONObject json_response_from_server = new JSONObject();
		JSONObject json_response_data = new JSONObject();
		JSONArray json_response_items = new JSONArray();		
		String line = ""; //aux var
		Business business = new Business();
		
		try 
		{		
			httpClient = HttpClientBuilder.create().build();
			postRequest = new HttpPost( ApacheHttpClientPost._SERVER + ":" + ApacheHttpClientPost._PORT + "/" + RESTServiceDirectory._LISTING_EMPLOYEES_SERVICE );
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("Accept","application/json");	  
			json_http_params.put("business_id", business.get_ID() ); 
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
			json_response_data  =  (JSONObject) json_response_from_server.get("data");
			json_response_items =  (JSONArray) json_response_data.get("Items");
			
			
			if (  json_response_items.length() > 0 )
			{
				//Model declaration
				model = new JTableModel( json_response_items.length() );				
				
				
				for(int i = 0; i < json_response_items.length(); i++)
				{
				    
				    JSONObject object = (JSONObject) json_response_items.get(i); 
				    String email = ((JSONObject) object.get("email")).get("S").toString() ;
				    String business_id = ((JSONObject) object.get("business_id")).get("S").toString() ;
				    String date = ((JSONObject) object.get("date")).get("S").toString() ;
				    String first_name = ((JSONObject) object.get("first_name")).get("S").toString() ;
				    String last_name = ((JSONObject) object.get("last_name")).get("S").toString() ;
				    String timestamp = ((JSONObject) object.get("timestamp")).get("S").toString() ;
				    String type = ((JSONObject) object.get("type")).get("S").toString() ;
				    					
					String[] row = { first_name, last_name, email, "", type, "Edit", "Delete" };
					
					//add the row to the Model.
					model.addRow( row, i );
					
					this.repaint();	
				}
				
				this.setModel(model);				
			}
		}
		
		catch( ClientProtocolException e )
		{
			System.out.println(e.getMessage());
		}
		
		catch( IOException e ) 
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


	public EmployeeController getController() {
		return controller;
	}


	public void setController(EmployeeController controller) {
		this.controller = controller;
	}
	
	
	
}

