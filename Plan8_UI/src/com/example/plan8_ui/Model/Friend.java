package com.example.plan8_ui.Model;

import java.util.HashMap;

public class Friend {

	public static final String id_id = "id";
	public static final String id_pic = "pic";
	public static final String id_first_name = "first_name";
	public static final String id_last_name = "last_name";
	public static final String id_latitude = "latitude";
	public static final String id_longitude = "longitude";
	
	private HashMap<String, String> info;
	private int resource;
	
	public Friend() {
		info = new HashMap<String, String>();
	}
	
//	public Friend(int resource,  String first_name, String last_name) {
//		this.resource = resource;
//		info = new HashMap<String, String>();
//		this.info.put(id_first_name, first_name);
//		this.info.put(id_last_name, last_name);
//	}
	
	public Friend(String pic,  String first_name, String last_name) {
		info = new HashMap<String, String>();
		this.info.put(id_pic, pic);
		this.info.put(id_first_name, first_name);
		this.info.put(id_last_name, last_name);
	}
	
	public void put_information(String key, String value){
		info.put(key, value);
	}
	
	public String get_information(String key){
		return info.get(key);
	}

}
