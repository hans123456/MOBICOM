package com.example.plan8_ui.Events;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.plan8_ui.R;
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

	private FloatingActionButton create_button;
	private ListView events_list_view;
	private EventsAdapter events_adapter;
	private View UpcomingEventsFragmentView;
	private ArrayList<Event> events;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		
		UpcomingEventsFragmentView = inflater.inflate(R.layout.fragment_upcoming_events, container, false);
		
		create_button = (FloatingActionButton) UpcomingEventsFragmentView.findViewById(R.id.upcoming_events_fragment_fab);
		create_button.setOnClickListener(create_button_ocl);
		
		events = new ArrayList<Event>();
		events.add(new Event());
		events.add(new Event());
		events.add(new Event());
		events.add(new Event());
		events.add(new Event());
		events.add(new Event());
		events.add(new Event());
		
		events_adapter = new EventsAdapter(getActivity().getBaseContext(), R.layout.event_item, events);

		events_list_view = (ListView) UpcomingEventsFragmentView.findViewById(R.id.upcoming_events_fragment_listview);
		
		View padding = new View(getActivity());
		padding.setMinimumHeight(5);
		events_list_view.removeHeaderView(padding);
		events_list_view.removeFooterView(padding);
		events_list_view.addHeaderView(padding);
		events_list_view.addFooterView(padding);
		
		events_list_view.setOnItemClickListener(events_list_view_OICL);
		events_list_view.setAdapter(events_adapter);

		create_button.attachToListView(events_list_view);

		return UpcomingEventsFragmentView;

	}
	

	OnClickListener create_button_ocl = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			Intent i = new Intent();
			i.setClass(getActivity().getBaseContext(), CreateEventActivity.class);
			//i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivityForResult(i, 0);
			
		}
		
	};

	OnItemClickListener events_list_view_OICL = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position,
				long arg3) {
			// TODO Auto-generated method stub
			
			Intent i = new Intent();
			i.setClass(getActivity().getBaseContext(), EventProfileActivity.class);
			//i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivityForResult(i, 1);
			
		}
		
	};

	@Override
	public void update_list(ArrayList<Event> result) {
		// TODO Auto-generated method stub
		
	}
	
}
