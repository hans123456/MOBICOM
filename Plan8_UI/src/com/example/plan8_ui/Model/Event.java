package com.example.plan8_ui.Model;

public class Event {

	private String id;
	private String title; 
	private String body; 
	private String dateFrom;
	private String dateTo;
	private String timeFrom; 
	private String timeTo; 
	private String location; 
	private String createdBy = "";	
	private String latitude = "14.566402";
	private String longitude = "120.993179";
	private boolean creator;
	
	public Event(){
		
	}
	
	public Event(String id,
					String title,
					String body,
					String dateFrom, 
					String dateTo, 
					String timeFrom,
					String timeTo, 
					String location,
					String latitude,
					String longtitude,
					String createdBy,
					boolean creator){
		
		this.id = id;
		this.title = title;
		this.body = body;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longtitude;
		this.createdBy = createdBy;
		this.creator = creator;
		
	}
	
	public Event(
			String title,
			String body,
			String dateFrom, 
			String dateTo, 
			String timeFrom,
			String timeTo,
			String location,
			String latitude,
			String longtitude){
		
		this.title = title;
		this.body = body;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longtitude;
		
	}

	public boolean isCreator() {
		return creator;
	}

	public String getTitle(){
		return title;
	}

	public String getDateFrom(){
		return dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public String getTimeFrom() 
	{
		return timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public String getLocation() {
		return location;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public String getId() {
		return id;
	}

	public String getBody() {
		return body;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
