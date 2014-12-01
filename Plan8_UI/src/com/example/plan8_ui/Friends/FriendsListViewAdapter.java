package com.example.plan8_ui.Friends;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Model.Friend;
import com.example.plan8_ui.Model.FriendViewHolder;
import com.squareup.picasso.Picasso;

public class FriendsListViewAdapter extends ArrayAdapter<Friend>{

	public FriendsListViewAdapter(Context context, int resource, List<Friend> objects) {
		super(context, resource, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(null == convertView){
			
			LayoutInflater vi = LayoutInflater.from(getContext());
			convertView = vi.inflate(R.layout.friend_item, parent, false);
			convertView.setTag(new FriendViewHolder(convertView));
			
		}
		
		Friend i = getItem(position);
		FriendViewHolder friendViewHolder = (FriendViewHolder) convertView.getTag();
		
		Picasso.with(getContext())
				.load("https://graph.facebook.com/"+i.get_information(Friend.id_pic)+"/picture?type=large")
				.resize(120, 120)
				.centerCrop()
				.into(friendViewHolder.get_pic_image_view());
		
		friendViewHolder.get_first_name_text_view().setText(i.get_information(Friend.id_first_name));
		friendViewHolder.get_last_name_text_view().setText(i.get_information(Friend.id_last_name));
		
		return convertView;
		
	}

}
