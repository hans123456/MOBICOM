package com.example.plan8_ui.Model;

import java.util.LinkedHashMap;
import java.util.Map;

public class RegisterResult {

	public static final String id_username = "username";
	public static final String id_email_address = "email_address";
	public static final String id_first_name = "first_name";
	public static final String id_last_name = "last_name";
	public static final String id_password = "password";
	public static final String id_confirm_password = "confirm_password";

	private Map<String, Boolean> results;
	
	public RegisterResult() {
		results = new LinkedHashMap<String, Boolean>();
	}

	public void put_result(String key, Boolean value){
		results.put(key, value);
	}
	
	public Boolean get_result(String key){
		return results.get(key);
	}
	
	public String get_invalids(){
		
		StringBuilder sb = new StringBuilder();

		boolean first = true;
		for(String i : results.keySet()){
				
			if(results.get(i) == false){
				
				if(first == false){
					sb.append('\n');
				}
				
				sb.append("Invalid " + i.replace('_', ' '));
				first = false;
				
			}
			
		}
		
//		sb.append( (results.get(id_username) == false) ? "Invalid Username" : "" );
//		sb.append( (results.get(id_email_address) == false) ? "Invalid Email Address" : "" );
//		sb.append( (results.get(id_password) == false) ? "Invalid Password" : "" );
//		sb.append( (results.get(id_confirm_password) == false) ? "Invalid Confirm Password" : "" );
//		sb.append( (results.get(id_first_name) == false) ? "Invalid First Name" : "" );
//		sb.append( (results.get(id_last_name) == false) ? "Invalid Last Name" : "" );
		
		return sb.toString();
		
	}
	
}
