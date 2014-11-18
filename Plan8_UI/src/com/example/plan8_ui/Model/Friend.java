package com.example.plan8_ui.Model;

public class Friend {

	private String id;
	private String unique_id;
	private String pic_url;
	private String first_name;
	private String last_name;
	private String latitude;
	private String longitude;
	private int resource;
	
	public int getPic(){
		return resource;
	}
	
	public Friend(int resource,  String first_name, String last_name) {
		this.resource = resource;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public String getId() {
		return id;
	}

	public String getPic_url() {
		return pic_url;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getUnique_id() {
		return unique_id;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	
}
