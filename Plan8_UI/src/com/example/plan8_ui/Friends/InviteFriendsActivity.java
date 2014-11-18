package com.example.plan8_ui.Friends;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Model.Friend;

public class InviteFriendsActivity extends ActionBarActivity {

	private ArrayList<Friend> friends = new ArrayList<Friend>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invite_friends);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.invite_friends_activity_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        
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
}
