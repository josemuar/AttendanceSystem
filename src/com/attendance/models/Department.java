package com.attendance.models;

import attendance_system.ApacheHttpClientPost;


/**
 * @Department this class represents the departments or division of a company
 */
public class Department {
	
	private String _ID;
	private String _name;
	
	
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
