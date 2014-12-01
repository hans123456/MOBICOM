package com.example.plan8_ui.Events;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Model.Event;
import com.example.plan8_ui.Model.EventViewHolder;

public class EventsListViewAdapter extends ArrayAdapter<Event>{

	public EventsListViewAdapter(Context context, int resource, List<Event> objects) {
		super(context, resource, objects);
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
		
		eventViewHolder.get_title_text_view().setText(event.get_information(Event.id_title));
		eventViewHolder.get_location_text_view().setText(event.get_information(Event.id_location));
		eventViewHolder.get_date_start_text_view().setText(event.get_information(Event.id_date_start));
		eventViewHolder.get_time_start_text_view().setText(event.get_information(Event.id_time_start));
		eventViewHolder.get_date_end_text_view().setText(event.get_information(Event.id_date_end));
		eventViewHolder.get_time_end_text_view().setText(event.get_information(Event.id_time_end));
		
		return convertView;

	}
	
}
