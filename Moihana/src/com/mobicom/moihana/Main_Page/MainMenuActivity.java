package com.mobicom.moihana.Main_Page;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mobicom.moihana.R;
import com.mobicom.moihana.Events.EventsMenuFragment;
import com.mobicom.moihana.Friends.FriendsMenuFragment;
import com.mobicom.moihana.Model.DrawerItem;
import com.mobicom.moihana.Profile.ProfileFragment;

public class MainMenuActivity extends ActionBarActivity {
	
	private DrawerLayout navigation_drawer_layout;
	private ListView navigation_drawer_list_view;
	private ActionBarDrawerToggle navigation_drawer_toggle;
	private Toolbar toolbar;
	private InputMethodManager im;
	
	private ArrayList<DrawerItem> drawer_items;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		// action bar
		toolbar = (Toolbar) findViewById(R.id.main_menu_activity_toolbar);
	    if (toolbar != null) {
	        setSupportActionBar(toolbar);
	    }
	    
	    setDrawer();
	    
		im = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
	    
	    Fragment fragment = new EventsMenuFragment();
	    
	    FragmentManager fragmentManager = getSupportFragmentManager();
	    fragmentManager.beginTransaction()
	                   .replace(R.id.main_menu_activity_content_frame, fragment)
	                   .commit();
	    
	}
	
	protected void setDrawer(){
	 
	    // navigation drawer
	    navigation_drawer_layout = (DrawerLayout) findViewById(R.id.main_menu_activity_drawer_layout);
	    drawer_items = new ArrayList<DrawerItem>();
	    drawer_items.add(new DrawerItem(R.drawable.ic_perm_identity_grey600_36dp, "Profile"));
	    drawer_items.add(new DrawerItem(R.drawable.ic_supervisor_account_grey600_36dp, "Friends"));
	    drawer_items.add(new DrawerItem(R.drawable.ic_today_grey600_36dp, "Events"));
//	    drawer_items.add(new DrawerItem(R.drawable.ic_event_invite_grey600_36dp, "Invited Events"));
//	    drawer_items.add(new DrawerItem(R.drawable.ic_event_available_grey600_36dp, "Finished Events"));
//	    drawer_items.add(new DrawerItem(R.drawable.ic_event_busy_grey600_36dp, "Declined Events"));
	    drawer_items.add(new DrawerItem(R.drawable.ic_exit_to_app_grey600_36dp, "Log Out"));
	    
	    DrawerAdapter navigation_drawer_adapter = new DrawerAdapter(getBaseContext(), R.layout.drawer_item, drawer_items);
	    
	    navigation_drawer_list_view = (ListView) findViewById(R.id.main_menu_activity_drawer_listview);
	    navigation_drawer_list_view.setAdapter(navigation_drawer_adapter);        
	    navigation_drawer_list_view.setOnItemClickListener(navigation_drawer_list_view_ocl);
	    
	    navigation_drawer_toggle = new ActionBarDrawerToggle(this, navigation_drawer_layout, toolbar, R.string.login_button, R.string.login_button);
	    navigation_drawer_toggle.setDrawerIndicatorEnabled(true);
	    
	    navigation_drawer_layout.setDrawerListener(navigation_drawer_toggle);
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    getSupportActionBar().setHomeButtonEnabled(true);
	    
	}
	
	private OnItemClickListener navigation_drawer_list_view_ocl = new OnItemClickListener() {
	
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id){
			
			im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
	        
			if(drawer_items.size()-1 == position)
				finish();
			else {
				selectItem(position);
			}
			
		}
		
		/** Swaps fragments in the main content view */
		private void selectItem(int position) {
		    // Create a new fragment and specify the planet to show based on position
		    Fragment fragment = null;

		    switch(position){
		    case 0:
		    	fragment = new ProfileFragment();	break;
		    case 1:
		    	fragment = new FriendsMenuFragment();	break;
		    case 2:
		    	fragment = new EventsMenuFragment();	break;
//		    case 3:
//		    	fragment = new InvitedEventsFragment();	break;
//		    case 4:
//		    	fragment = new FinishedEventsFragment();	break;
//		    case 5:
//		    	fragment = new DeclinedEventsFragment();	break;
		    }
		    
		    //Bundle args = new Bundle();
		    //args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
		    //fragment.setArguments(args);

		    // Insert the fragment by replacing any existing fragment
		    FragmentManager fragmentManager = getSupportFragmentManager();
		    fragmentManager.beginTransaction()
		                   .replace(R.id.main_menu_activity_content_frame, fragment)
		                   .commit();
	
		    // Highlight the selected item, update the title, and close the drawer
			navigation_drawer_list_view.setItemChecked(position, true);
		    setTitle(drawer_items.get(position).getText());
		    navigation_drawer_layout.closeDrawer(navigation_drawer_list_view);
		    
		}
	
	};
	
	@Override
	public void setTitle(CharSequence title) {
	    getSupportActionBar().setTitle(title);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
	    if (requestCode == 1) {
	    	
	        if(resultCode == RESULT_OK){
	        	//Write your code if there's result
	        }
	        else if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	        
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.events, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (navigation_drawer_toggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	
	 @Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    // Sync the toggle state after onRestoreInstanceState has occurred.
	    navigation_drawer_toggle.syncState();
	}
	 
	 @Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    navigation_drawer_toggle.onConfigurationChanged(newConfig);
	}
	
	 @Override
	public void onBackPressed() {
		super.onBackPressed();
	}

}
