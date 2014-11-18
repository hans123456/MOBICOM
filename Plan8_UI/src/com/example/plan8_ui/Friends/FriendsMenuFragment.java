package com.example.plan8_ui.Friends;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.common.view.SlidingTabLayout;
import com.example.plan8_ui.R;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link FriendsMenuFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class FriendsMenuFragment extends Fragment {
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
	 * @return A new instance of fragment FriendsFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static FriendsMenuFragment newInstance(String param1, String param2) {
		FriendsMenuFragment fragment = new FriendsMenuFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public FriendsMenuFragment() {
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

	private View FriendsFragmentView;
	private ViewPager friends_view_pager;
	private FriendsStatePagerAdapter friends_state_pager_adapter; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		super.onCreateView(inflater, container, savedInstanceState);
		
		FriendsFragmentView = inflater.inflate(R.layout.fragment_friends_menu, container, false);
		
		friends_state_pager_adapter = new FriendsStatePagerAdapter(getFragmentManager());
		
		friends_view_pager = (ViewPager) FriendsFragmentView.findViewById(R.id.friends_pager);
		friends_view_pager.setAdapter(friends_state_pager_adapter);
		
		SlidingTabLayout tabLayout = (SlidingTabLayout) FriendsFragmentView.findViewById(R.id.friends_sliding_tabs);
		
		tabLayout.setViewPager(friends_view_pager);
		
		return FriendsFragmentView;
		
	}

}
