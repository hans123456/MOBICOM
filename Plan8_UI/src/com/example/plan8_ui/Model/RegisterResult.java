package com.example.plan8_ui.Model;

import java.util.HashMap;

public class RegisterResult {

	public static final String id_username = "username";
	public static final String id_email_address = "email_address";
	public static final String id_first_name = "first_name";
	public static final String id_last_name = "last_name";
	public static final String id_password = "password";
	public static final String id_confirm_password = "confirm_password";

	private HashMap<String, Boolean> results;
	
	public RegisterResult() {
		results = new HashMap<String, Boolean>();
	}

	public void put_result(String key, Boolean value){
		results.put(key, value);
	}
	
	public Boolean get_result(String key){
		return results.get(key);
	}
	
}
