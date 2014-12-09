package com.example.plan8_ui.Events;

import java.util.List;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Model.Event;
import com.example.plan8_ui.Model.EventViewHolder;
import com.squareup.picasso.Picasso;

public class EventsListViewAdapter extends ArrayAdapter<Event>{
	
	DisplayMetrics metrics;
	
	public EventsListViewAdapter(Context context, int resource, List<Event> objects) {
		super(context, resource, objects);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (null == convertView) {	
			
		    LayoutInflater vi = LayoutInflater.from(getContext());
		    convertView = vi.inflate(R.layout.event_item, parent, false);
		    convertView.setTag(new EventViewHolder(convertView));

		}
		
		Event event = getItem(position);
		EventViewHolder eventViewHolder = (EventViewHolder) convertView.getTag();
		
		Picasso.with(convertView.getContext())
		.load("http://maps.googleapis.com/maps/api/staticmap?zoom=18&size="+
				metrics.widthPixels+"x300&markers=size:large|color:red|"
				+ event.get_information(Event.id_latitude) + ","
				+ event.get_information(Event.id_longitude))
		.into(eventViewHolder.get_image_view());
		
		if(event.has_information(Event.id_title)){
			eventViewHolder.get_title_text_view().setText(event.get_information(Event.id_title));
			eventViewHolder.get_title_text_view().setVisibility(View.VISIBLE);
		}else{
			eventViewHolder.get_title_text_view().setVisibility(View.GONE);
		}
		
		if(event.has_information(Event.id_location)){
			eventViewHolder.get_location_text_view().setText(event.get_information(Event.id_location));
			eventViewHolder.get_location_text_view().setVisibility(View.VISIBLE);
		}else{
			eventViewHolder.get_location_text_view().setVisibility(View.GONE);
		}
		
		eventViewHolder.get_date_start_text_view().setText(event.get_information(Event.id_date_start));
		eventViewHolder.get_time_start_text_view().setText(event.get_information(Event.id_time_start));
		eventViewHolder.get_date_end_text_view().setText(event.get_information(Event.id_date_end));
		eventViewHolder.get_time_end_text_view().setText(event.get_information(Event.id_time_end));
		
		return convertView;

	}
	
}
