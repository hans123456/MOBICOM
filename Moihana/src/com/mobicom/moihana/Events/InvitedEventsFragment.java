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
import com.mobicom.moihana.AsyncTasks.FetchInvitedEvents;
import com.mobicom.moihana.Interfaces.AsyncFetchListTaskCompleteListener;
import com.mobicom.moihana.Model.Event;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link InvitedEventsFragment#newInstance} factory method to create an
 * instance of this fragment.
 *
 */
public class InvitedEventsFragment extends Fragment implements AsyncFetchListTaskCompleteListener<ArrayList<Event>>{

	public static InvitedEventsFragment newInstance(String param1, String param2) {
		InvitedEventsFragment fragment = new InvitedEventsFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public InvitedEventsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
		}
	}

	@InjectView(R.id.invited_events_fragment_list_view) ListView events_list_view;
	
	EventsListViewAdapter eventsAdapter;
	View InvitedEventsFragmentView;
	ArrayList<Event> events;
	FetchInvitedEvents fetchInvitedEvents;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		InvitedEventsFragmentView = inflater.inflate(R.layout.fragment_invited_events, container, false);
		ButterKnife.inject(this, InvitedEventsFragmentView);
		
		events = new ArrayList<Event>();
		eventsAdapter = new EventsListViewAdapter(getActivity().getBaseContext(), R.layout.event_item, events);

		events_list_view.setAdapter(eventsAdapter);

		return InvitedEventsFragmentView;

	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		fetchInvitedEvents.cancel(true);
	}
	
	@OnItemClick (R.id.invited_events_fragment_list_view)
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

		if(fetchInvitedEvents != null){
			if(fetchInvitedEvents.isCancelled() == false){
				fetchInvitedEvents.cancel(true);
			}
		}
	
		fetchInvitedEvents = new FetchInvitedEvents(this);
		fetchInvitedEvents.execute();
		
	}

}
