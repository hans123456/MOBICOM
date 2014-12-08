package com.example.plan8_ui.Friends;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Interfaces.AsyncFetchListTaskCompleteListener;
import com.example.plan8_ui.Model.Friend;

public class EventAttendeesActivity extends ActionBarActivity implements AsyncFetchListTaskCompleteListener<ArrayList<Friend>>{

	@InjectView(R.id.event_attendees_toolbar) Toolbar toolbar;
	
	private ArrayList<Friend> attendees;
	private FriendsListViewAdapter adapter;
	@InjectView(R.id.event_attendees_list_view) ListView event_attendees_listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_attendees);
		ButterKnife.inject(this);
		
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        attendees = new ArrayList<Friend>();
        attendees.add(new Friend("moiradenise.paguiligan", "Moira", "Paguiligan"));
        	
        attendees.add(new Friend("geraldine.cu.1", 
        							"Geraldine", 
        							"Cu"));
        
        adapter = new FriendsListViewAdapter(getBaseContext(), R.layout.friend_item, attendees); 
        event_attendees_listview.setAdapter(adapter);
        
	}

	
	@OnItemClick(R.id.event_attendees_list_view)
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long arg3) {
		
		Intent i = new Intent();
		i.setClass(getBaseContext(), AttendeeLocationActivity.class);
		startActivity(i);
		
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(getApplication()).inflate(R.menu.event_attendees, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (android.R.id.home == id) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void update_list(ArrayList<Friend> result) {
		attendees.clear();
		attendees.addAll(result);
	}
}
