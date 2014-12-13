package com.mobicom.moihana.Model;

import java.util.HashMap;

public class User {
	
	public static final String id_pic = "pic";
	public static final String id_first_name = "first_name";
	public static final String id_last_name = "last_name";
	
	private HashMap<String, String> info;
	
	public User() {
		info = new HashMap<String, String>();
	}
	
	public void put_information(String key, String value){
		info.put(key, value);
	}
	
	public String get_information(String key){
		return info.get(key);
	}
	
	public boolean is_valid(){
		return info.size()>0;
	}

}
