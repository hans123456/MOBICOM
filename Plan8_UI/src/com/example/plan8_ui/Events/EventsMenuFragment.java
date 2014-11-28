package com.example.plan8_ui.Events;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.android.common.view.SlidingTabLayout;
import com.example.plan8_ui.R;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link EventsMenuFragment#newInstance} factory method to create an instance
 * of this fragment.
 *
 */
public class EventsMenuFragment extends Fragment {
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
	 * @return A new instance of fragment EventsMenuFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static EventsMenuFragment newInstance(String param1, String param2) {
		EventsMenuFragment fragment = new EventsMenuFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public EventsMenuFragment() {
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

	@InjectView(R.id.events_pager) ViewPager events_view_pager;
	@InjectView(R.id.events_sliding_tabs) SlidingTabLayout tabLayout;
	
	View EventsFragmentView;
	EventsStatePagerAdapter events_state_pager_adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		EventsFragmentView = inflater.inflate(R.layout.fragment_events_menu, container, false);
		ButterKnife.inject(this, EventsFragmentView);
		
		events_state_pager_adapter = new EventsStatePagerAdapter(getFragmentManager());
		events_view_pager.setAdapter(events_state_pager_adapter);
		
		tabLayout.setViewPager(events_view_pager);
		
		return EventsFragmentView;
		
	}
	
}
