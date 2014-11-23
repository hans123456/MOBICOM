package com.example.plan8_ui.Model;

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
	
	private String id;
	private String title; 
	private String description; 
	private String date_start;
	private String date_end;
	private String time_start; 
	private String time_end; 
	private String location; 
	private String createdBy = "";	
	private String latitude = "14.566402";
	private String longitude = "120.993179";
	private boolean creator;
	
	public Event(){
		
	}
	
	public Event(String id,
					String title,
					String location,
					String date_start, 
					String time_start,
					String date_end,
					String time_end){

		this.id = id;
		this.title = title;
		this.location = location;
		this.date_start = date_start;
		this.date_end = date_end;
		this.time_start = time_start;
		this.time_end = time_end;

	}
	
	public Event(String title,
					String description,
					String location,
					String date_start, 
					String time_start,
					String date_end, 
					String time_end,
					String latitude,
					String longtitude){
		
		this.title = title;
		this.description = description;
		this.location = location;
		this.date_start = date_start;
		this.time_start = time_start;
		this.date_end = date_end;
		this.time_end = time_end;
		this.latitude = latitude;
		this.longitude = longtitude;
		
	}

	public boolean is_creator() {
		return creator;
	}

	public String get_title(){
		return title;
	}

	public String get_date_start(){
		return date_start;
	}

	public String get_date_end() {
		return date_end;
	}

	public String get_time_start() 
	{
		return time_start;
	}

	public String get_time_end() {
		return time_end;
	}

	public String get_location() {
		return location;
	}

	public String get_latitude() {
		return latitude;
	}

	public String get_longitude() {
		return longitude;
	}
	
	public String get_CreatedBy() {
		return createdBy;
	}

	public String get_id() {
		return id;
	}

	public String get_description() {
		return description;
	}

	public void set_title(String title) {
		this.title = title;
	}

	public void set_description(String description) {
		this.description = description;
	}

	public void set_date_start(String date_start) {
		this.date_start = date_start;
	}

	public void set_date_end(String date_end) {
		this.date_end = date_end;
	}

	public void set_time_start(String time_start) {
		this.time_start = time_start;
	}

	public void set_time_end(String time_end) {
		this.time_end = time_end;
	}

	public void set_location(String location) {
		this.location = location;
	}

	public void set_latitude(String latitude) {
		this.latitude = latitude;
	}

	public void set_longitude(String longitude) {
		this.longitude = longitude;
	}

}
