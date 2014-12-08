package com.example.plan8_ui.Events;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import com.example.plan8_ui.R;
import com.example.plan8_ui.AsyncTasks.FetchFinishedEvents;
import com.example.plan8_ui.Interfaces.AsyncFetchListTaskCompleteListener;
import com.example.plan8_ui.Model.Event;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link FinishedEventsFragment#newInstance} factory method to create an
 * instance of this fragment.
 *
 */
public class FinishedEventsFragment extends Fragment implements AsyncFetchListTaskCompleteListener<ArrayList<Event>>{
	
	@InjectView(R.id.finished_events_fragment_list_view) ListView events_list_view;
	
	EventsListViewAdapter eventsAdapter;
	View FinishedEventsFragmentView;
	ArrayList<Event> events;
	FetchFinishedEvents fetchFinishedEvents;
	
	public static FinishedEventsFragment newInstance(String param1, String param2) {
		FinishedEventsFragment fragment = new FinishedEventsFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public FinishedEventsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		FinishedEventsFragmentView = inflater.inflate(R.layout.fragment_finished_events, container, false);
		ButterKnife.inject(this, FinishedEventsFragmentView);
		
		events = new ArrayList<Event>();
		eventsAdapter = new EventsListViewAdapter(getActivity().getBaseContext(), R.layout.event_item, events);

		events_list_view.setAdapter(eventsAdapter);

		return FinishedEventsFragmentView;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		fetchFinishedEvents.cancel(true);
	}
	
	@OnItemClick (R.id.finished_events_fragment_list_view)
	public void onItemClick(int position){

		Intent i = new Intent();
		i.setClass(getActivity().getBaseContext(), EventProfileActivity.class);
		i.putExtra(Event.id_id, events.get(position).get_information(Event.id_id));
		startActivityForResult(i, 1);

	}
	
	@Override
	public void update_list(ArrayList<Event> result) {
		events.clear();
		events.addAll(result);
		eventsAdapter.notifyDataSetChanged();
	}

	@Override
	public void onResume() {
		
		super.onResume();

		if(fetchFinishedEvents != null){
			if(fetchFinishedEvents.isCancelled() == false){
				fetchFinishedEvents.cancel(true);
			}
		}
	
		fetchFinishedEvents = new FetchFinishedEvents(this);
		fetchFinishedEvents.execute();
		
	}
	
}
