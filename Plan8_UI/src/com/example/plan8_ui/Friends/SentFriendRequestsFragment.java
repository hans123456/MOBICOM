package com.example.plan8_ui.Friends;

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
import com.example.plan8_ui.AsyncTasks.FetchSentFriendRequests;
import com.example.plan8_ui.Interfaces.AsyncFetchListTaskCompleteListener;
import com.example.plan8_ui.Model.Friend;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link SentFriendRequestsFragment#newInstance} factory method to create an
 * instance of this fragment.
 *
 */
public class SentFriendRequestsFragment extends Fragment implements AsyncFetchListTaskCompleteListener<ArrayList<Friend>>{
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
//	private String mParam1;
//	private String mParam2;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 *
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment SentFriendRequestFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static SentFriendRequestsFragment newInstance(String param1,
			String param2) {
		SentFriendRequestsFragment fragment = new SentFriendRequestsFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public SentFriendRequestsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
//			mParam1 = getArguments().getString(ARG_PARAM1);
//			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}
	
	@InjectView(R.id.sent_friend_requests_fragment_list_view) ListView sent_frient_requests_list_view;
	FriendsListViewAdapter sentFriendRequestsListViewAdapter;
	ArrayList<Friend> sentFriendRequests;
	FetchSentFriendRequests fetchSentFriendRequests;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		View SentFriendRequestsFragment = inflater.inflate(R.layout.fragment_sent_friend_requests, container, false);
		ButterKnife.inject(this, SentFriendRequestsFragment);
		
		sentFriendRequests = new ArrayList<Friend>();
		sentFriendRequestsListViewAdapter = new FriendsListViewAdapter(getActivity(), R.layout.friend_item, sentFriendRequests);
		sent_frient_requests_list_view.setAdapter(sentFriendRequestsListViewAdapter);
		
		return SentFriendRequestsFragment;
		
	}

	@OnItemClick (R.id.sent_friend_requests_fragment_list_view)
	public void onItemClick(int position) {
		
		Friend f = sentFriendRequests.get(position);
		Intent i = new Intent();
		i.setClass(getActivity().getBaseContext(), FriendProfileActivity.class);
		i.putExtra(Friend.id_id, f.get_information(Friend.id_id));
		i.putExtra(Friend.id_unique_id, f.get_information(Friend.id_unique_id));
		i.putExtra(Friend.id_pic, f.get_information(Friend.id_pic));
		i.putExtra(Friend.id_first_name, f.get_information(Friend.id_first_name));
		i.putExtra(Friend.id_last_name, f.get_information(Friend.id_last_name));
		i.putExtra(FriendProfileActivity.TYPE, FriendProfileActivity.SENT_FRIEND_REQUEST);
		startActivity(i);
		
	};
	
	@Override
	public void onResume() {
		super.onResume();

		if(fetchSentFriendRequests != null){
			if(fetchSentFriendRequests.isCancelled() == false){
				fetchSentFriendRequests.cancel(true);
			}
		}
	
		fetchSentFriendRequests = new FetchSentFriendRequests(this);
		fetchSentFriendRequests.execute();
		
	}

	@Override
	public void update_list(ArrayList<Friend> result) {
		sentFriendRequests.clear();
		sentFriendRequests.addAll(result);
		sentFriendRequestsListViewAdapter.notifyDataSetChanged();		
	}

}
