	package com.example.plan8_ui.Model;

import java.util.HashMap;

public class Friend {

	public static final String id_id = "id";
	public static final String id_unique_id = "unique_id";
	public static final String id_pic = "pic";
	public static final String id_first_name = "first_name";
	public static final String id_last_name = "last_name";
	public static final String id_latitude = "latitude";
	public static final String id_longitude = "longitude";
	public static final String id_checked = "checked";
	
	private boolean empty = true;
	private HashMap<String, String> info;
	
	public Friend() {
		info = new HashMap<String, String>();
	}
	
	public Friend(String pic,  String first_name, String last_name) {
		info = new HashMap<String, String>();
		this.info.put(id_pic, pic);
		this.info.put(id_first_name, first_name);
		this.info.put(id_last_name, last_name);
	}
	
	public void put_information(String key, String value){
		info.put(key, value);
		empty = false;
	}
	
	public String get_information(String key){
		return info.get(key);
	}
	
	public boolean contains_information(String key){
		return info.containsKey(key);
	}
	
	public boolean isEmpty(){
		return empty;
	}

}
