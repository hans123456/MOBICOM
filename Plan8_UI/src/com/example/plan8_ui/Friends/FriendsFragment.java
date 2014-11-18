package com.example.plan8_ui.Friends;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Model.Friend;
import com.melnykov.fab.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link FriendsFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class FriendsFragment extends Fragment {
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
		
		fs = new ArrayList<Friend>();
		fs.add(new Friend(R.drawable.moi_pic_60x60, "Moira", "Paguiligan"));
		fs.add(new Friend(R.drawable.gege_pic_60x60, "Geraldine", "Cu"));
		fs.add(new Friend(R.drawable.moi_pic_60x60, "Moira", "Paguiligan"));
		fs.add(new Friend(R.drawable.gege_pic_60x60, "Geraldine", "Cu"));
		fs.add(new Friend(R.drawable.moi_pic_60x60, "Moira", "Paguiligan"));
		fs.add(new Friend(R.drawable.gege_pic_60x60, "Geraldine", "Cu"));
		fs.add(new Friend(R.drawable.moi_pic_60x60, "Moira", "Paguiligan"));
		fs.add(new Friend(R.drawable.gege_pic_60x60, "Geraldine", "Cu"));
		fs.add(new Friend(R.drawable.moi_pic_60x60, "Moira", "Paguiligan"));
		fs.add(new Friend(R.drawable.gege_pic_60x60, "Geraldine", "Cu"));
		fs.add(new Friend(R.drawable.moi_pic_60x60, "Moira", "Paguiligan"));
		fs.add(new Friend(R.drawable.gege_pic_60x60, "Geraldine", "Cu"));
		
	}

	private View FriendsFragment;
	private FloatingActionButton addFriends;
	private InputMethodManager im;
	private ListView friends_list_view;
	private FriendsListViewAdapter friends_list_view_adapter;
	private ArrayList<Friend> fs;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		
		FriendsFragment = inflater.inflate(R.layout.fragment_friends, container, false);

		friends_list_view = (ListView) FriendsFragment.findViewById(R.id.friends_listview);
		friends_list_view.setOnItemClickListener(friends_list_view_OICL);

		View padding = new View(getActivity());
		padding.setMinimumHeight(5);
		friends_list_view.removeHeaderView(padding);
		friends_list_view.removeFooterView(padding);
		friends_list_view.addHeaderView(padding);
		friends_list_view.addFooterView(padding);
		
		friends_list_view_adapter = new FriendsListViewAdapter(getActivity(), R.layout.friend_item, fs);
		friends_list_view.setAdapter(friends_list_view_adapter);
		
		addFriends = (FloatingActionButton) FriendsFragment.findViewById(R.id.friends_fab);
		addFriends.setOnClickListener(addFriendsOCL);
		addFriends.attachToListView(friends_list_view);
		
		return FriendsFragment;
		
	}
	
	OnClickListener addFriendsOCL = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Intent i = new Intent();
			i.setClass(getActivity().getBaseContext(), SendFriendRequestActivity.class);
			//i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivityForResult(i, 0);
			
		}
		
	};
	
	OnItemClickListener friends_list_view_OICL = new OnItemClickListener() {

		public void onItemClick(android.widget.AdapterView<?> adapter, View view, int position, long arg3) {
			
			Intent i = new Intent();
			i.setClass(getActivity().getBaseContext(), FriendProfileActivity.class);
			//i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivityForResult(i, 1);
			
		};
		
	};
	
}
