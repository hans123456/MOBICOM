package com.example.plan8_ui.Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.plan8_ui.R;

public class FriendViewHolder {

	@InjectView(R.id.friend_item_image_view) ImageView pic_image_view;
	@InjectView(R.id.friend_item_first_name_text_view) TextView first_name_text_view;
	@InjectView(R.id.friend_item_last_name_text_view) TextView last_name_text_view;
	
	public FriendViewHolder(View v) {
		ButterKnife.inject(this, v);
	}
	
	public ImageView get_pic_image_view() {
		return pic_image_view;
	}
	
	public TextView get_first_name_text_view() {
		return first_name_text_view;
	}
	
	public TextView get_last_name_text_view() {
		return last_name_text_view;
	}
	
}
