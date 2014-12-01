package com.example.plan8_ui.Model;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreateEventResult {

	public static final String id_id = "event_id";
	public static final String id_title = "title";
	public static final String id_location = "location";
	public static final String id_description = "description";
	public static final String id_date_start = "date_start";
	public static final String id_time_start = "time_start";
	public static final String id_date_end = "date_end";
	public static final String id_time_end = "time_end";
	public static final String id_latitude = "latitude";
	public static final String id_longitude = "longitude";
	
	private boolean isSuccessful = true;
	
	private Map<String, Boolean> results;
	
	public CreateEventResult() {
		results = new LinkedHashMap<String, Boolean>();
	}

	public void put_result(String key, Boolean value){
		results.put(key, value);
		isSuccessful = false;
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
		
		return sb.toString();
		
	}
	
	public boolean isSuccessful(){
		return isSuccessful;
	}
	
}
