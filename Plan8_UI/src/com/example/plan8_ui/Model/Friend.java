package com.example.plan8_ui.Model;

public class Friend {

	public static final String id_id = "id";
	public static final String id_pic = "pic";
	public static final String id_first_name = "first_name";
	public static final String id_last_name = "last_name";
	
	private String id;
	private String unique_id;
	private String pic_url;
	private String first_name;
	private String last_name;
	private String latitude;
	private String longitude;
	private int resource;
	
	public Friend(String id,  String first_name, String last_name) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public Friend(int resource,  String first_name, String last_name) {
		this.resource = resource;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public void set_first_name(String first_name) {
		this.first_name = first_name;
	}

	public void set_last_name(String last_name) {
		this.last_name = last_name;
	}

	public void set_latitude(String latitude) {
		this.latitude = latitude;
	}

	public void set_longitude(String longitude) {
		this.longitude = longitude;
	}

	public int get_pic(){
		return resource;
	}
	
	public String get_id() {
		return id;
	}

	public String get_pic_url() {
		return pic_url;
	}

	public String get_first_name() {
		return first_name;
	}

	public String get_last_name() {
		return last_name;
	}

	public String get_unique_id() {
		return unique_id;
	}

	public String get_latitude() {
		return latitude;
	}

	public String get_longitude() {
		return longitude;
	}
	
}
