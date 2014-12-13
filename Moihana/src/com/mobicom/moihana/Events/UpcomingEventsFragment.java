package com.mobicom.moihana.Events;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnLongClick;

import com.melnykov.fab.FloatingActionButton;
import com.mobicom.moihana.R;
import com.mobicom.moihana.AsyncTasks.FetchUpcomingEvents;
import com.mobicom.moihana.Interfaces.AsyncFetchListTaskCompleteListener;
import com.mobicom.moihana.Model.Event;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link UpcomingEventsFragment#newInstance} factory method to create an
 * instance of this fragment.
 *
 */
public class UpcomingEventsFragment extends Fragment implements AsyncFetchListTaskCompleteListener<ArrayList<Event>>{

	@InjectView(R.id.upcoming_events_fragment_fab) FloatingActionButton create_button;
	@InjectView(R.id.upcoming_events_fragment_list_view) ListView events_list_view;
	
	private EventsListViewAdapter eventsAdapter;
	private View UpcomingEventsFragmentView;
	private ArrayList<Event> events;
	private FetchUpcomingEvents fetchUpcomingEvents;
	private final int REQUEST_CODE_CREATE_EVENT = 0;

	public static UpcomingEventsFragment newInstance(String param1,
			String param2) {
		UpcomingEventsFragment fragment = new UpcomingEventsFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public UpcomingEventsFragment() {
		// Required empty public constructor
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
								ViewGroup container,
								Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		UpcomingEventsFragmentView = inflater.inflate(R.layout.fragment_upcoming_events, container, false);
		ButterKnife.inject(this, UpcomingEventsFragmentView);
		
		events = new ArrayList<Event>();
		eventsAdapter = new EventsListViewAdapter(getActivity().getBaseContext(), R.layout.event_item, events);

		events_list_view.setAdapter(eventsAdapter);
		create_button.attachToListView(events_list_view);

		return UpcomingEventsFragmentView;

	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		fetchUpcomingEvents.cancel(true);
	}
	
	@OnClick(R.id.upcoming_events_fragment_fab)
	public void onClickCreateEvent(){
		Intent i = new Intent();
		i.setClass(getActivity().getBaseContext(), CreateEventActivity.class);
		startActivityForResult(i, REQUEST_CODE_CREATE_EVENT);
	}
	
	@OnLongClick(R.id.upcoming_events_fragment_fab)
	public boolean onLongClickCreateEvent(){	
		Toast.makeText(getActivity().getBaseContext(), "Create An Event", Toast.LENGTH_LONG).show();
		return true;
	}

	@OnItemClick(R.id.upcoming_events_fragment_list_view)
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

		if(fetchUpcomingEvents != null){
			if(fetchUpcomingEvents.isCancelled() == false){
				fetchUpcomingEvents.cancel(true);
			}
		}
	
		fetchUpcomingEvents = new FetchUpcomingEvents(this);
		fetchUpcomingEvents.execute();
		
	}
	
}
