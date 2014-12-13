package com.mobicom.moihana.Events;

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

import com.mobicom.moihana.R;
import com.mobicom.moihana.AsyncTasks.FetchDeclinedEvents;
import com.mobicom.moihana.Interfaces.AsyncFetchListTaskCompleteListener;
import com.mobicom.moihana.Model.Event;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link DeclinedEventsFragment#newInstance} factory method to create an
 * instance of this fragment.
 *
 */
public class DeclinedEventsFragment extends Fragment implements AsyncFetchListTaskCompleteListener<ArrayList<Event>>{
	
	// lazy to find view by id 
	@InjectView(R.id.declined_events_fragment_list_view) ListView events_list_view;
	
	EventsListViewAdapter eventsAdapter;
	View DeclinedEventsFragmentView;
	ArrayList<Event> events;
	FetchDeclinedEvents fetchDeclinedEvents;
	
	public static DeclinedEventsFragment newInstance(String param1,
			String param2) {
		DeclinedEventsFragment fragment = new DeclinedEventsFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public DeclinedEventsFragment() {
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
		DeclinedEventsFragmentView = inflater.inflate(R.layout.fragment_declined_events, container, false);
		ButterKnife.inject(this, DeclinedEventsFragmentView);
		
		events = new ArrayList<Event>();
		eventsAdapter = new EventsListViewAdapter(getActivity().getBaseContext(), R.layout.event_item, events);

		events_list_view.setAdapter(eventsAdapter);

		return DeclinedEventsFragmentView;
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		fetchDeclinedEvents.cancel(true);
	}
	
	@OnItemClick (R.id.declined_events_fragment_list_view)
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

		if(fetchDeclinedEvents != null){
			if(fetchDeclinedEvents.isCancelled() == false){
				fetchDeclinedEvents.cancel(true);
			}
		}
	
		fetchDeclinedEvents = new FetchDeclinedEvents(this);
		fetchDeclinedEvents.execute();
		
	}

}
