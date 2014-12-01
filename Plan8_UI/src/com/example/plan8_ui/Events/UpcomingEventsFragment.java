package com.example.plan8_ui.Events;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

import com.example.plan8_ui.R;
import com.example.plan8_ui.AsyncTasks.FetchUpcomingEvents;
import com.example.plan8_ui.Interfaces.AsyncFetchListTaskCompleteListener;
import com.example.plan8_ui.Model.Event;
import com.melnykov.fab.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link UpcomingEventsFragment#newInstance} factory method to create an
 * instance of this fragment.
 *
 */
public class UpcomingEventsFragment extends Fragment implements AsyncFetchListTaskCompleteListener<ArrayList<Event>>{
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	// lazy to find view by id 
	@InjectView(R.id.upcoming_events_fragment_fab) FloatingActionButton create_button;
	@InjectView(R.id.upcoming_events_fragment_list_view) ListView events_list_view;
	
	private EventsListViewAdapter eventsAdapter;
	private View UpcomingEventsFragmentView;
	private ArrayList<Event> events;
	private FetchUpcomingEvents fetchUpcomingEvents;
	private final int REQUEST_CODE_CREATE_EVENT = 0;
	
	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 *
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment UpcomingEventsFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static UpcomingEventsFragment newInstance(String param1,
			String param2) {
		UpcomingEventsFragment fragment = new UpcomingEventsFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
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
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
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

		fetchUpcomingEvents = new FetchUpcomingEvents(this);
		fetchUpcomingEvents.execute();
		
		return UpcomingEventsFragmentView;

	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		fetchUpcomingEvents.cancel(true);
	}
	
	@OnClick (R.id.upcoming_events_fragment_fab)
	public void onClickCreateEvent(){

		Intent i = new Intent();
		i.setClass(getActivity().getBaseContext(), CreateEventActivity.class);
		startActivityForResult(i, REQUEST_CODE_CREATE_EVENT);
		
	}

	@OnItemClick (R.id.upcoming_events_fragment_list_view)
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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == REQUEST_CODE_CREATE_EVENT){
			if(resultCode == Activity.RESULT_OK){
				fetchUpcomingEvents = new FetchUpcomingEvents(this);
				fetchUpcomingEvents.execute();
			}
		}
		
	}
	
}
