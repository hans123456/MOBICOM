package com.mobicom.moihana.Friends;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.melnykov.fab.FloatingActionButton;
import com.mobicom.moihana.R;
import com.mobicom.moihana.AsyncTasks.FetchFriendInfo;
import com.mobicom.moihana.AsyncTasks.SendFriendRequest;
import com.mobicom.moihana.Interfaces.AsyncGetInfoTaskCompleteListener;
import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener;
import com.mobicom.moihana.Model.Friend;
import com.squareup.picasso.Picasso;

public class SendFriendRequestActivity extends ActionBarActivity implements AsyncGetInfoTaskCompleteListener<Friend>, AsyncGetResultTaskCompleteListener<Boolean>{

	@InjectView(R.id.send_friend_request_activity_toolbar) Toolbar toolbar;
	@InjectView(R.id.send_friend_request_activity_fab) FloatingActionButton fab;
	@InjectView(R.id.send_friend_request_activity_unique_id_edit_text) EditText unique_id_edit_text;
	@InjectView(R.id.send_friend_request_activity_profile_pic) ImageView pic_image_view;
	@InjectView(R.id.send_friend_request_activity_first_name_text_view) TextView first_name_text_view;
	@InjectView(R.id.send_friend_request_activity_last_name_text_view) TextView last_name_text_view;
	
	Friend friend;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_friend_request);
		ButterKnife.inject(this);
		
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        
	}

	@OnClick(R.id.send_friend_request_activity_fab)
	public void onClickSearch(){

		String unique_id = unique_id_edit_text.getText().toString();
        new FetchFriendInfo(this).execute(unique_id);
        
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
		}else if (id == R.id.send_friend_request_save) {
			
			if(friend != null){
				if(friend.isEmpty() == false){
					new SendFriendRequest(this).execute(friend.get_information(Friend.id_id).toString());
				}
			}
			
			return true;
		}
		
		return super.onOptionsItemSelected(item);
		
	}

	@Override
	public void display_info(Friend info) {
		
		friend = info;
		
		if(friend.isEmpty() == false){

			Picasso.with(getBaseContext())
					.load("https://graph.facebook.com/"+friend.get_information(Friend.id_pic)+"/picture?type=large")
					.resize(300, 300)
					.centerCrop()
					.into(pic_image_view);
			
			pic_image_view.setVisibility(View.VISIBLE);
			first_name_text_view.setText(friend.get_information(Friend.id_first_name));
			last_name_text_view.setText(friend.get_information(Friend.id_last_name));
			
		}else {
			
			pic_image_view.setVisibility(View.INVISIBLE);
			first_name_text_view.setText("");
			last_name_text_view.setText("");
			
		}
	}

	@Override
	public void display_result(Boolean result) {
		finish();
	}
	
}
