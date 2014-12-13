package com.mobicom.moihana.Friends;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FriendsStatePagerAdapter extends FragmentStatePagerAdapter{
	
	private int number_of_titles = 3;
	private Fragment fragment;
	private String[] titles = { "Friends", "Friend Requests", "Sent Friend Requests" };
	
	public FriendsStatePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
	
		fragment = null;
		
		switch(position){
		case 0:
			fragment = new FriendsFragment();	break;
		case 1:
			fragment = new FriendRequestsFragment(); break;
		case 2:
			fragment = new SentFriendRequestsFragment(); break;
		}
		
		return fragment;
		
	}

	@Override
	public int getCount() {
		return number_of_titles;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}
	
}
