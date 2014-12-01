package com.example.plan8_ui.Friends;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import com.example.plan8_ui.R;
import com.example.plan8_ui.AsyncTasks.FetchFriendsNotYetInvited;
import com.example.plan8_ui.Interfaces.AsyncFetchListTaskCompleteListener;
import com.example.plan8_ui.Model.Event;
import com.example.plan8_ui.Model.Friend;

public class InviteFriendsActivity extends ActionBarActivity implements AsyncFetchListTaskCompleteListener<ArrayList<Friend>>{

	@InjectView(R.id.invite_friends_activity_toolbar) Toolbar toolbar;
	@InjectView(R.id.invite_friends_activity_list_view) ListView friends_list_view;
	
	private FriendsListViewAdapter friendsAdapter;
	private ArrayList<Friend> friends;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invite_friends);
		ButterKnife.inject(this);
		
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        
        friends = new ArrayList<Friend>();
        friendsAdapter = new FriendsListViewAdapter(getBaseContext(), R.layout.friend_item, friends);
        
        friends_list_view.setAdapter(friendsAdapter);
        
        Intent i = getIntent();
        Bundle b = i.getExtras();
        
        if(b.containsKey(Event.id_id)){
        
	        String event_id = b.getString(Event.id_id);
	        
	        new FetchFriendsNotYetInvited(this).execute(event_id);
        
        }
	        
	}
	
	@OnItemClick(R.id.invite_friends_activity_list_view)
	public void onItemClickFriends(int position){
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		new MenuInflater(getApplication()).inflate(R.menu.invite_friends, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (R.id.invite_friends_cancel == id) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void update_list(ArrayList<Friend> result) {
		friends.clear();
		friends.addAll(result);
		friendsAdapter.notifyDataSetChanged();
	}
}
