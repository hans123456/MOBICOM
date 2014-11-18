package com.example.plan8_ui.Friends;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.example.plan8_ui.R;

public class SendFriendRequestActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_friend_request);

		Toolbar toolbar = (Toolbar) findViewById(R.id.send_friend_request_activity_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		new MenuInflater(getApplication()).inflate(R.menu.send_friend_request, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
		
		if (id == R.id.send_friend_request_cancel) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
