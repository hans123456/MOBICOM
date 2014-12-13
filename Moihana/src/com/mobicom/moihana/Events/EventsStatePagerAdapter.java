package com.mobicom.moihana.Events;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class EventsStatePagerAdapter extends FragmentStatePagerAdapter{

	private int number_of_titles = 4;
	private Fragment fragment;
	private String[] titles = { "Upcoming", "Invited", "Finished", "Declined" };
	
	public EventsStatePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	Fragment up = new UpcomingEventsFragment(), de = new DeclinedEventsFragment(), in = new InvitedEventsFragment(), fi = new FinishedEventsFragment();
	
	@Override
	public Fragment getItem(int position) {

		fragment = null;

		switch(position){
		case 0:
			fragment = new UpcomingEventsFragment(); break;
		case 1:
			fragment = new InvitedEventsFragment(); break;
		case 2:
			fragment = new FinishedEventsFragment(); break;
		case 3:
			fragment = new DeclinedEventsFragment(); break;
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
