package com.example.plan8_ui.Events;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Model.Event;

public class EventsAdapter extends ArrayAdapter<Event>{

	public EventsAdapter(Context context, int resource, List<Event> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (null == convertView) {	
		    LayoutInflater vi = LayoutInflater.from(getContext());
		    convertView = vi.inflate(R.layout.event_item, parent, false);
		}
		
		Event event = getItem(position);
		
		return convertView;

	}
	
}
