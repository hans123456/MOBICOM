package com.mobicom.moihana.Friends;

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
import com.mobicom.moihana.AsyncTasks.FetchFriendRequests;
import com.mobicom.moihana.Interfaces.AsyncFetchListTaskCompleteListener;
import com.mobicom.moihana.Model.Friend;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link FriendRequestsFragment#newInstance} factory method to create an
 * instance of this fragment.
 *
 */
public class FriendRequestsFragment extends Fragment implements AsyncFetchListTaskCompleteListener<ArrayList<Friend>>{
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
	 * @return A new instance of fragment FriendRequestsFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static FriendRequestsFragment newInstance(String param1,
			String param2) {
		FriendRequestsFragment fragment = new FriendRequestsFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public FriendRequestsFragment() {
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
	
	@InjectView(R.id.friend_requests_fragment_list_view) ListView friend_requests_list_view;
	FriendsListViewAdapter friendRequestsListViewAdapter;
	ArrayList<Friend> friendRequests;
	FetchFriendRequests fetchFriendRequests;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		View FriendRequestFragment = inflater.inflate(R.layout.fragment_friend_requests, container, false);
		ButterKnife.inject(this, FriendRequestFragment);
		
		friendRequests = new ArrayList<Friend>();
		friendRequestsListViewAdapter = new FriendsListViewAdapter(getActivity(), R.layout.friend_item, friendRequests);
		friend_requests_list_view.setAdapter(friendRequestsListViewAdapter);
		
		return FriendRequestFragment;
	
	}
	
	@OnItemClick (R.id.friend_requests_fragment_list_view)
	public void onItemClick(int position) {
		
		Friend f = friendRequests.get(position);
		Intent i = new Intent();
		i.setClass(getActivity().getBaseContext(), FriendProfileActivity.class);
		i.putExtra(Friend.id_id, f.get_information(Friend.id_id));
		i.putExtra(Friend.id_unique_id, f.get_information(Friend.id_unique_id));
		i.putExtra(Friend.id_pic, f.get_information(Friend.id_pic));
		i.putExtra(Friend.id_first_name, f.get_information(Friend.id_first_name));
		i.putExtra(Friend.id_last_name, f.get_information(Friend.id_last_name));
		i.putExtra(FriendProfileActivity.TYPE, FriendProfileActivity.FRIEND_REQUEST);
		startActivity(i);
		
	};
	
	@Override
	public void onResume() {
		super.onResume();
		
		if(fetchFriendRequests != null){
			if(fetchFriendRequests.isCancelled() == false){
				fetchFriendRequests.cancel(true);
			}
		}
	
		fetchFriendRequests = new FetchFriendRequests(this);
		fetchFriendRequests.execute();
		
	}
	
	@Override
	public void update_list(ArrayList<Friend> result) {
		friendRequests.clear();
		friendRequests.addAll(result);
		friendRequestsListViewAdapter.notifyDataSetChanged();		
	}
	
}
