package com.example.plan8_ui.Friends;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Model.Friend;
import com.squareup.picasso.Picasso;

public class FriendProfileActivity extends ActionBarActivity {
	
	private final String TAG = "Friend Profile";
	Friend friend;
	
	@InjectView(R.id.friend_profile_unique_id_text_view) TextView unique_id_text_view;
	@InjectView(R.id.friend_profile_pic_image_view) ImageView pic_image_view;
	@InjectView(R.id.friend_profile_first_name_text_view) TextView first_name_text_view;
	@InjectView(R.id.friend_profile_last_name_text_view) TextView last_name_text_view;
	@InjectView(R.id.friend_profile_activity_toolbar) Toolbar toolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_profile);
		ButterKnife.inject(this);
		
        Bundle b = getIntent().getExtras();
        friend = new Friend();
        
        for(String i : b.keySet()){
        	friend.put_information(i, b.getString(i));
        }
        
        Picasso.with(getBaseContext())
			.load("https://graph.facebook.com/"+friend.get_information(Friend.id_pic)+"/picture?type=large")
			.resize(300, 300)
			.centerCrop()
			.into(pic_image_view);
        
        toolbar.setTitle(friend.get_information(Friend.id_first_name) + "'s profile");
        
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
		
        unique_id_text_view.setText(friend.get_information(Friend.id_unique_id));
        first_name_text_view.setText(friend.get_information(Friend.id_first_name));
        last_name_text_view.setText(friend.get_information(Friend.id_last_name));

	}

	@Override
	protected void onResume() {
		super.onResume();

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		new MenuInflater(getApplication()).inflate(R.menu.friend_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.friend_profile_cancel) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
