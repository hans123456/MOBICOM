package com.example.plan8_ui.Friends;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

import com.example.plan8_ui.R;
import com.example.plan8_ui.AsyncTasks.FetchFriends;
import com.example.plan8_ui.Interfaces.AsyncFetchListTaskCompleteListener;
import com.example.plan8_ui.Model.Friend;
import com.melnykov.fab.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link FriendsFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class FriendsFragment extends Fragment implements AsyncFetchListTaskCompleteListener<ArrayList<Friend>>{
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
	public static FriendsFragment newInstance(String param1, String param2) {
		
		FriendsFragment fragment = new FriendsFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
		
	}

	public FriendsFragment() {
		// Required empty public constructor
	}

	@InjectView(R.id.friends_fragment_fab) FloatingActionButton addFriends;
	@InjectView(R.id.friends_fragment_list_view) ListView friends_list_view;
	FriendsListViewAdapter friendsListViewAdapter;
	ArrayList<Friend> friends;
	FetchFriends fetchFriends;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		View FriendsFragment = inflater.inflate(R.layout.fragment_friends, container, false);
		ButterKnife.inject(this, FriendsFragment);
		
		friends = new ArrayList<Friend>();
		friendsListViewAdapter = new FriendsListViewAdapter(getActivity(), R.layout.friend_item, friends);
		friends_list_view.setAdapter(friendsListViewAdapter);
		
		addFriends.attachToListView(friends_list_view);
		
		return FriendsFragment;
		
	}
	
	@Override
	public void onResume() {
		super.onResume();

		if(fetchFriends != null){
			if(fetchFriends.isCancelled() == false){
				fetchFriends.cancel(true);
			}
		}
	
		fetchFriends = new FetchFriends(this);
		fetchFriends.execute();
		
	}
	
	@OnClick (R.id.friends_fragment_fab)
	public void onClick() {
		
		Intent i = new Intent();
		i.setClass(getActivity().getBaseContext(), SendFriendRequestActivity.class);
		startActivityForResult(i, 0);
		
	}
	
	@OnItemClick (R.id.friends_fragment_list_view)
	public void onItemClick(int position) {
		
		Friend f = friends.get(position);
		Intent i = new Intent();
		i.setClass(getActivity().getBaseContext(), FriendProfileActivity.class);
		i.putExtra(Friend.id_id, f.get_information(Friend.id_id));
		i.putExtra(Friend.id_unique_id, f.get_information(Friend.id_unique_id));
		i.putExtra(Friend.id_pic, f.get_information(Friend.id_pic));
		i.putExtra(Friend.id_first_name, f.get_information(Friend.id_first_name));
		i.putExtra(Friend.id_last_name, f.get_information(Friend.id_last_name));
		startActivity(i);
		
	};
	
	@Override
	public void update_list(ArrayList<Friend> result) {
		friends.clear();
		friends.addAll(result);
		friendsListViewAdapter.notifyDataSetChanged();
	}
	
}
