package com.example.plan8_ui.Model;

import java.util.HashMap;

public class Event {

	public static final String id_id = "id";
	public static final String id_title = "title";
	public static final String id_description = "description";
	public static final String id_location = "location";
	public static final String id_date_start = "date_start";
	public static final String id_time_start = "time_start";
	public static final String id_date_end = "date_end";
	public static final String id_time_end = "time_end";
	public static final String id_latitude = "latitude";
	public static final String id_longitude = "longitude";
	public static final String id_created_by = "created_by";
	public static final String id_you = "you";
	
	private HashMap<String, String> info;
		
	public Event(){
		info = new HashMap<String, String>();
	}
	
	public void put_information(String key, String value){
		info.put(key, value);
	}
	
	public String get_information(String key){
		return info.get(key);
	}
	
}
