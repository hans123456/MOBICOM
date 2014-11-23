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
			
			friend_view_holder.setImage_view_pic((ImageView) convertView.findViewById(R.id.friend_item_image_view));
			friend_view_holder.setText_view_first_name((TextView) convertView.findViewById(R.id.friend_item_first_text_view));
			friend_view_holder.setText_view_last_name((TextView) convertView.findViewById(R.id.friend_item_last_text_view));
			
			convertView.setTag(friend_view_holder);
			
		}else {
			
			friend_view_holder = (FriendViewHolder) convertView.getTag();
			
		}
		
		Friend i = getItem(position);
		
		friend_view_holder.getImage_view_pic().setImageResource(i.get_pic());
		friend_view_holder.getText_view_first_name().setText(i.get_first_name());
		friend_view_holder.getText_view_last_name().setText(i.get_last_name());
		
		return convertView;
		
	}

}
