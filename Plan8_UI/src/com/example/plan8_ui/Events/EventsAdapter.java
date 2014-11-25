package com.example.plan8_ui.Events;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Model.Event;
import com.example.plan8_ui.Model.EventViewHolder;

public class EventsAdapter extends ArrayAdapter<Event>{

	public EventsAdapter(Context context, int resource, List<Event> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (null == convertView) {	
			
		    LayoutInflater vi = LayoutInflater.from(getContext());
		    convertView = vi.inflate(R.layout.event_item, parent, false);
		    
		    EventViewHolder event_view_holder = new EventViewHolder();
		    event_view_holder.set_date_start_text_view((TextView) convertView.findViewById(R.id.event_item_date_start_text_view));
		    event_view_holder.set_time_start_text_view((TextView) convertView.findViewById(R.id.event_item_time_start_text_view));
		    event_view_holder.set_date_end_text_view((TextView) convertView.findViewById(R.id.event_item_date_end_text_view));
		    event_view_holder.set_time_end_text_view((TextView) convertView.findViewById(R.id.event_item_time_end_text_view));
		    event_view_holder.set_title_text_view((TextView) convertView.findViewById(R.id.event_item_title_text_view));
		    event_view_holder.set_location_text_view((TextView) convertView.findViewById(R.id.event_item_location_text_view));
		    
		    convertView.setTag(event_view_holder);
		    
		}
		
		Event event = getItem(position);
		
		return convertView;

	}
	
}
