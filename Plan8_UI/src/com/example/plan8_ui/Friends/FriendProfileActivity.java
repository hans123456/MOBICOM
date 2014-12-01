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
import butterknife.OnClick;

import com.example.plan8_ui.R;
import com.example.plan8_ui.AsyncTasks.AcceptFriend;
import com.example.plan8_ui.AsyncTasks.DeleteFriend;
import com.example.plan8_ui.Interfaces.AsyncGetResultTaskCompleteListener;
import com.example.plan8_ui.Model.Friend;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class FriendProfileActivity extends ActionBarActivity implements AsyncGetResultTaskCompleteListener<Boolean>{
	
	public static final String TYPE = "TYPE";
	public static final String FRIEND_REQUEST = "FRIEND REQUEST";
	public static final String FRIEND = "FRIEND";
	public static final String SENT_FRIEND_REQUEST = "SENT FRIEND REQUEST";

	@InjectView(R.id.friend_profile_activity_unique_id_text_view) TextView unique_id_text_view;
	@InjectView(R.id.friend_profile_activity_pic_image_view) ImageView pic_image_view;
	@InjectView(R.id.friend_profile_activity_first_name_text_view) TextView first_name_text_view;
	@InjectView(R.id.friend_profile_activity_last_name_text_view) TextView last_name_text_view;
	@InjectView(R.id.friend_profile_activity_toolbar) Toolbar toolbar;
	@InjectView(R.id.friend_profile_activity_fab) FloatingActionButton fab;
	
	Friend friend;
	String type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_profile);
		ButterKnife.inject(this);
		
        Bundle b = getIntent().getExtras();
        friend = new Friend();
        
        for(String i : b.keySet()){
        	if(i.equals(TYPE) == false){
        		friend.put_information(i, b.getString(i));
        	}else {
        		type = b.getString(i);
        	}
        }
        
        Picasso.with(getBaseContext())
			.load("https://graph.facebook.com/"+friend.get_information(Friend.id_pic)+"/picture?type=large")
			.resize(300, 300)
			.centerCrop()
			.into(pic_image_view);
        
        if (toolbar != null) {
            toolbar.setTitle(friend.get_information(Friend.id_first_name) + "'s profile");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        unique_id_text_view.setText(friend.get_information(Friend.id_unique_id));
        first_name_text_view.setText(friend.get_information(Friend.id_first_name));
        last_name_text_view.setText(friend.get_information(Friend.id_last_name));

        if(type.equals(FRIEND_REQUEST)){
        	fab.setImageResource(R.drawable.ic_add_white_24dp);
        }
        
	}

	@OnClick(R.id.friend_profile_activity_fab)
	public void onClickFAB(){
		
		if(type.equals(FRIEND_REQUEST)){
			new AcceptFriend(this).execute(friend.get_information(Friend.id_id));
		}else {
			new DeleteFriend(this).execute(friend.get_information(Friend.id_id));
		}
		
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
		if (android.R.id.home == id) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
		
	}

	@Override
	public void display_result(Boolean result) {
		finish();
	}
}
