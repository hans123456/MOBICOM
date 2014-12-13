package com.mobicom.moihana.Friends;

import java.util.ArrayList;

import android.app.ProgressDialog;
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

import com.mobicom.moihana.R;
import com.mobicom.moihana.AsyncTasks.FetchFriendsNotYetInvited;
import com.mobicom.moihana.Interfaces.AsyncFetchListTaskCompleteListener;
import com.mobicom.moihana.Model.Event;
import com.mobicom.moihana.Model.Friend;

public class InviteFriendsActivity extends ActionBarActivity implements AsyncFetchListTaskCompleteListener<ArrayList<Friend>>{

	public static final String id_friends_ids = "friends_ids";
	
	@InjectView(R.id.invite_friends_activity_toolbar) Toolbar toolbar;
	@InjectView(R.id.invite_friends_activity_list_view) ListView friends_list_view;
	
	private FriendsAttendanceInviteListViewAdapter friendsAttendanceInviteAdapter;
	private ArrayList<Friend> friends;
	private ProgressDialog progressDialog;
	
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
		
		progressDialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
		//progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("Fetching Friends Please Wait");
		
		Intent i = getIntent();
		Bundle b = i.getExtras();
		
		FetchFriendsNotYetInvited friendsNotYetInvited = new FetchFriendsNotYetInvited(this);
		
		String event_id = b.getString(Event.id_id);
		friendsNotYetInvited.execute(event_id);
		progressDialog.show();
		
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
			Intent i = new Intent();
			setResult(RESULT_CANCELED, i);
			progressDialog.dismiss();
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

			progressDialog.dismiss();
			finish();
			
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void update_list(ArrayList<Friend> result) {
		friends.clear();
		friends.addAll(result);
		friendsAttendanceInviteAdapter.notifyDataSetChanged();
		progressDialog.dismiss();
	}
	
}
