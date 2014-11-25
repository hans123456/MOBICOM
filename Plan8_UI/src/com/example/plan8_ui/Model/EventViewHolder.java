package com.example.plan8_ui.Model;

import android.widget.TextView;

public class EventViewHolder {

	private TextView date_start_text_view;
	private TextView time_start_text_view;
	private TextView date_end_text_view;
	private TextView time_end_text_view;
	private TextView title_text_view;
	private TextView location_text_view;
	
	public TextView get_date_start_text_view() {
		return date_start_text_view;
	}
	
	public void set_date_start_text_view(TextView date_start_text_view) {
		this.date_start_text_view = date_start_text_view;
	}
	
	public TextView get_time_start_text_view() {
		return time_start_text_view;
	}
	
	public void set_time_start_text_view(TextView time_start_text_view) {
		this.time_start_text_view = time_start_text_view;
	}
	
	public TextView get_date_end_text_view() {
		return date_end_text_view;
	}
	
	public void set_date_end_text_view(TextView date_end_text_view) {
		this.date_end_text_view = date_end_text_view;
	}
	
	public TextView get_time_end_text_view() {
		return time_end_text_view;
	}
	
	public void set_time_end_text_view(TextView time_end_text_view) {
		this.time_end_text_view = time_end_text_view;
	}
	
	public TextView get_title_text_view() {
		return title_text_view;
	}
	
	public void set_title_text_view(TextView title_text_view) {
		this.title_text_view = title_text_view;
	}
	
	public TextView get_location_text_view() {
		return location_text_view;
	}
	
	public void set_location_text_view(TextView location_text_view) {
		this.location_text_view = location_text_view;
	}
	
}
