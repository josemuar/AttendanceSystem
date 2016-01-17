package com.attendance.models;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import com.attendance.services.RESTServiceDirectory;

import attendance_system.ApacheHttpClientPost;

/*
 * @this class is intended to represent the Business in the real world which owns the attendance system.
 */
public class Business {
	
	private String _ID;
	private String _name;
	
	/**
	 * Constructor
	 */
	public Business() { 
		 
		this.set_ID( "IDENTIFIERCSRD1234567890" ); //THIS MUST BE A UNIQUE IDENTIFIER FOR EVERY BUSINESS (THIS ID IS JUST FOR TRIAL)  
		this.set_name( "COMPUTER SECURE REUSE DISPOSAL" ); //NAME OF THE COMPANY OR BUSINESS
		
	}
	
	public String get_ID() {
		return _ID;
	}
	
	public void set_ID(String _ID) {
		this._ID = _ID;
	}
	
	public String get_name() {
		return _name;
	}
	
	public void set_name(String _name) {
		this._name = _name;
	}
	
}
