package com.mobicom.moihana.Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.google.android.gms.maps.GoogleMap;
import com.mobicom.moihana.R;

public class EventViewHolder {

	@InjectView(R.id.event_item_image_view) ImageView image_view;
	@InjectView(R.id.event_item_title_text_view) TextView title_text_view;
	@InjectView(R.id.event_item_location_text_view)  TextView location_text_view;
	@InjectView(R.id.event_item_date_start_text_view) TextView date_start_text_view;
	@InjectView(R.id.event_item_time_start_text_view) TextView time_start_text_view;
	@InjectView(R.id.event_item_date_end_text_view) TextView date_end_text_view;
	@InjectView(R.id.event_item_time_end_text_view) TextView time_end_text_view;

	GoogleMap googleMap;
	
	public EventViewHolder(View v) {
		ButterKnife.inject(this, v);
	}
	
	public ImageView get_image_view(){
		return image_view;
	}
	
	public TextView get_date_start_text_view() {
		return date_start_text_view;
	}
	
	public TextView get_time_start_text_view() {
		return time_start_text_view;
	}
	
	public TextView get_date_end_text_view() {
		return date_end_text_view;
	}
	
	public TextView get_time_end_text_view() {
		return time_end_text_view;
	}
	
	public TextView get_title_text_view() {
		return title_text_view;
	}
	
	public TextView get_location_text_view() {
		return location_text_view;
	}
	
}
