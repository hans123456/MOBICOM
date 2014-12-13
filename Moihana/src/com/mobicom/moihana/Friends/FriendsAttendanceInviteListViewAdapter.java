package com.mobicom.moihana.Friends;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mobicom.moihana.R;
import com.mobicom.moihana.Model.Friend;
import com.mobicom.moihana.Model.FriendAttendanceInviteViewHolder;
import com.squareup.picasso.Picasso;

public class FriendsAttendanceInviteListViewAdapter extends ArrayAdapter<Friend>{

	private boolean attendance = false;
	
	public FriendsAttendanceInviteListViewAdapter(Context context, int resource, List<Friend> objects, boolean attendance) {
		super(context, resource, objects);
		this.attendance = attendance;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (null == convertView) {	
			
		    LayoutInflater vi = LayoutInflater.from(getContext());
		    convertView = vi.inflate(R.layout.friend_attendance_invite_item, parent, false);
		    convertView.setTag(new FriendAttendanceInviteViewHolder(convertView));

		}
		
		Friend friend = getItem(position);
		FriendAttendanceInviteViewHolder friendAttendanceInviteViewHolder = (FriendAttendanceInviteViewHolder) convertView.getTag();
		friendAttendanceInviteViewHolder.setFriend(friend);
		
		if(attendance == true){
			friendAttendanceInviteViewHolder.get_check_box().setChecked(friend.get_information(Friend.id_checked).equals("true") ? true : false );
		}
		
		Picasso.with(getContext())
				.load("https://graph.facebook.com/"+friend.get_information(Friend.id_pic)+"/picture?type=large")
				.resize(120, 120)
				.centerCrop()
				.into(friendAttendanceInviteViewHolder.get_pic_image_view());
		
		friendAttendanceInviteViewHolder.get_first_name_text_view().setText(friend.get_information(Friend.id_first_name));
		friendAttendanceInviteViewHolder.get_last_name_text_view().setText(friend.get_information(Friend.id_last_name));
		
		
		return convertView;
		
	}
	
}
