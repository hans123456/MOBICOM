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

	public static final String id_friends_ids = "friends_ids";
	
	@InjectView(R.id.invite_friends_activity_toolbar) Toolbar toolbar;
	@InjectView(R.id.invite_friends_activity_list_view) ListView friends_list_view;
	
	private FriendsAttendanceInviteListViewAdapter friendsAttendanceInviteAdapter;
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
		friendsAttendanceInviteAdapter = new FriendsAttendanceInviteListViewAdapter(getBaseContext(), R.layout.friend_attendance_invite_item, friends, false);
		
		friends_list_view.setAdapter(friendsAttendanceInviteAdapter);

		Intent i = getIntent();
		Bundle b = i.getExtras();
		
		FetchFriendsNotYetInvited friendsNotYetInvited = new FetchFriendsNotYetInvited(this);
		
		String event_id = b.getString(Event.id_id);
		friendsNotYetInvited.execute(event_id);
	
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
		}else if(R.id.invite_friends_save == id){
			
			StringBuilder sb = new StringBuilder("");
			boolean first = true;
			
			for(Friend i : friends){
				
				if(i.contains_information(Friend.id_checked) == true){
					
					if(i.get_information(Friend.id_checked).equals("true")){
						
						if(first == false){
							sb.append(",");
						}
						
						sb.append(i.get_information(Friend.id_id));
						first = false;
						
					}
					
				}
				
			}
			
			Intent i = new Intent();
			i.putExtra(id_friends_ids, sb.toString());
			setResult(RESULT_OK, i);

			finish();
			
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void update_list(ArrayList<Friend> result) {
		friends.clear();
		friends.addAll(result);
		friendsAttendanceInviteAdapter.notifyDataSetChanged();
	}
	
}
