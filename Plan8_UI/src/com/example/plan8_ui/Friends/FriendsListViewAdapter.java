package com.example.plan8_ui.Friends;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
		
		FriendViewHolder friend_view_holder;
		
		if(null == convertView){
			
			LayoutInflater vi = LayoutInflater.from(getContext());
			convertView = vi.inflate(R.layout.friend_item, parent, false);
			
			friend_view_holder = new FriendViewHolder();
			
			friend_view_holder.set_pic_image_view((ImageView) convertView.findViewById(R.id.friend_item_image_view));
			friend_view_holder.set_first_name_text_view((TextView) convertView.findViewById(R.id.friend_item_first_text_view));
			friend_view_holder.set_last_name_text_view((TextView) convertView.findViewById(R.id.friend_item_last_text_view));
				
			convertView.setTag(friend_view_holder);
			
		}else {
			
			friend_view_holder = (FriendViewHolder) convertView.getTag();
			
		}
		
		Friend i = getItem(position);
		
		Picasso.with(getContext())
				.load("https://graph.facebook.com/"+i.get_information(Friend.id_pic)+"/picture?type=large")
				.resize(100, 100)
				.centerCrop()
				.into(friend_view_holder.get_pic_image_view());
		
		//friend_view_holder.get_pic_image_view().setImageResource(i.get_pic());
		friend_view_holder.get_first_name_text_view().setText(i.get_information(Friend.id_first_name));
		friend_view_holder.get_last_name_text_view().setText(i.get_information(Friend.id_last_name));
		
		return convertView;
		
	}

}
