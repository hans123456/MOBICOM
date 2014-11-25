package com.example.plan8_ui.Model;

import android.widget.ImageView;
import android.widget.TextView;

public class FriendViewHolder {

	private ImageView pic_image_view;
	private TextView first_name_text_view;
	private TextView last_name_text_view;
	
	public ImageView get_pic_image_view() {
		return pic_image_view;
	}
	
	public TextView get_first_name_text_view() {
		return first_name_text_view;
	}
	
	public TextView get_last_name_text_view() {
		return last_name_text_view;
	}
	
	public void set_pic_image_view(ImageView pic_image_view) {
		this.pic_image_view = pic_image_view;
	}
	
	public void set_first_name_text_view(TextView first_name_text_view) {
		this.first_name_text_view = first_name_text_view;
	}
	
	public void set_last_name_text_view(TextView last_name_text_view) {
		this.last_name_text_view = last_name_text_view;
	}
	
}
