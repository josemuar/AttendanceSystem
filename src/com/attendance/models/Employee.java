package com.attendance.models;

public class Employee {
	
	private String _first_name;
	private String _last_name;
	private String _email;
	private String _type;
	
	
	public String get_first_name() {
		return _first_name;
	}
	public void set_first_name(String _first_name) {
		this._first_name = _first_name;
	}
	public String get_last_name() {
		return _last_name;
	}
	public void set_last_name(String _last_name) {
		this._last_name = _last_name;
	}
	public String get_email() {
		return _email;
	}
	public void set_email(String _email) {
		this._email = _email;
	}
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}

	
}
