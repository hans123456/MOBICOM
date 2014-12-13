package com.mobicom.moihana.Friends;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnLongClick;

import com.mobicom.moihana.R;
import com.mobicom.moihana.AsyncTasks.FetchEventAttendees;
import com.mobicom.moihana.AsyncTasks.SendLocation;
import com.mobicom.moihana.Interfaces.AsyncFetchListTaskCompleteListener;
import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener;
import com.mobicom.moihana.Model.Event;
import com.mobicom.moihana.Model.Friend;

public class EventAttendeesActivity extends ActionBarActivity implements AsyncFetchListTaskCompleteListener<ArrayList<Friend>>, AsyncGetResultTaskCompleteListener<Boolean>{

	@InjectView(R.id.event_attendees_activity_toolbar) Toolbar toolbar;
	@InjectView(R.id.event_attendees_activity_list_view) ListView event_attendees_listview;
	
	private ArrayList<Friend> attendees;
	private FriendsListViewAdapter adapter;
	private ProgressDialog progressDialog;
	private LocationManager locationManager;
	private String event_id, event_latitude, event_longitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_attendees);
		ButterKnife.inject(this);
		
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        attendees = new ArrayList<Friend>();
        adapter = new FriendsListViewAdapter(getBaseContext(), R.layout.friend_item, attendees); 
        event_attendees_listview.setAdapter(adapter);

		progressDialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("Fetching Event Attendees Please Wait");
       
		Intent i = getIntent();
        Bundle b = i.getExtras();
        
        event_id = b.getString(Event.id_id);
        event_latitude = b.getString(Event.id_latitude);
        event_longitude = b.getString(Event.id_longitude);
        
        new FetchEventAttendees(this).execute(event_id);
        progressDialog.show();
        
	}

	float previous = Float.MAX_VALUE;
	private LocationListener locationListener = new LocationListener() {

		@Override
		public void onProviderDisabled(String arg0) {
		}

		@Override
		public void onProviderEnabled(String arg0) {
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		}

		@Override
		public void onLocationChanged(Location location) {

			if(previous == Float.MAX_VALUE){
				previous = location.getAccuracy();
			}else {
				if(location.getAccuracy()-previous<=10){
					new SendLocation(EventAttendeesActivity.this).execute(event_id, location.getLatitude()+"", location.getLongitude()+"");
					locationManager.removeUpdates(locationListener);
				}
			}
			
		}
		
	};
	
	@OnItemClick(R.id.event_attendees_activity_list_view)
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long arg3) {
		
		Friend attendee = attendees.get(position);
		
		if(attendee.get_information(Friend.id_latitude).equals("null") == false){
			Intent i = new Intent();
			i.setClass(getBaseContext(), AttendeeLocationActivity.class);
			i.putExtra("event_" + Event.id_latitude, event_latitude);
			i.putExtra("event_" + Event.id_longitude, event_longitude);
			i.putExtra("friend_" + Friend.id_first_name, attendee.get_information(Friend.id_first_name));
			i.putExtra("friend_" + Friend.id_last_name, attendee.get_information(Friend.id_last_name));
			i.putExtra("friend_" + Friend.id_latitude, attendee.get_information(Friend.id_latitude));
			i.putExtra("friend_" + Friend.id_longitude, attendee.get_information(Friend.id_longitude));
			startActivity(i);
		}else {
			Toast.makeText(getBaseContext(), "Not yet received " + attendee.get_information(Friend.id_first_name) + "'s location", Toast.LENGTH_LONG).show();;
		}
		
	}
	
	@OnClick(R.id.event_attendees_activity_fab)
	public void onClickSendLocation(View view){
		progressDialog.setMessage("Sending Location Please Wait");
		previous = Float.MAX_VALUE;
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		progressDialog.show();
	}

	@OnLongClick(R.id.event_attendees_activity_fab)
	public boolean onLongClickSendLocation(View view){
        Toast.makeText(view.getContext(), "Send Your Location", Toast.LENGTH_SHORT).show();
		return true;
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(getApplication()).inflate(R.menu.event_attendees, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (android.R.id.home == id) {
			progressDialog.dismiss();
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void update_list(ArrayList<Friend> result) {
		attendees.clear();
		attendees.addAll(result);
		progressDialog.hide();
	}

	@Override
	public void display_result(Boolean result) {
		Toast.makeText(getBaseContext(), "Location Successfully Sent", Toast.LENGTH_LONG).show();
		progressDialog.hide();
		new FetchEventAttendees(this).execute(event_id);
		progressDialog.setMessage("Updating List");
        progressDialog.show();
	}
	
}
