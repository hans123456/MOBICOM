package com.mobicom.moihana.Model;

import java.util.LinkedHashMap;
import java.util.Map;

public class RegisterResult {

	public static final String id_username = "username";
	public static final String id_email_address = "email_address";
	public static final String id_first_name = "first_name";
	public static final String id_last_name = "last_name";
	public static final String id_password = "password";
	public static final String id_confirm_password = "confirm_password";

	public static final String SUCCESS = "Registered Successfully";
	
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
	
	public String get_result(){
		
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
		
		if(sb.toString().equals("")){
			sb.append(SUCCESS);
		}
		
		return sb.toString();
		
	}
	
}
