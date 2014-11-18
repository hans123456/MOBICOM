package com.example.plan8_ui.Model;

import android.widget.ImageView;
import android.widget.TextView;

public class FriendViewHolder {

	ImageView image_view_pic;
	TextView text_view_first_name;
	TextView text_view_last_name;
	
	public ImageView getImage_view_pic() {
		return image_view_pic;
	}
	
	public TextView getText_view_first_name() {
		return text_view_first_name;
	}
	
	public TextView getText_view_last_name() {
		return text_view_last_name;
	}
	
	public void setImage_view_pic(ImageView image_view_pic) {
		this.image_view_pic = image_view_pic;
	}
	
	public void setText_view_first_name(TextView text_view_first_name) {
		this.text_view_first_name = text_view_first_name;
	}
	
	public void setText_view_last_name(TextView text_view_last_name) {
		this.text_view_last_name = text_view_last_name;
	}
	
}
