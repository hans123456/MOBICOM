package com.mobicom.moihana.Friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobicom.moihana.R;
import com.mobicom.moihana.Model.Event;
import com.mobicom.moihana.Model.Friend;

public class AttendeeLocationActivity extends ActionBarActivity {
	
	@InjectView(R.id.attendee_activity_toolbar) Toolbar toolbar;
	
	private GoogleMap googleMap;
	private MarkerOptions markerOptions1, markerOptions2;
	private Marker marker2;
	
	private String attendee_first_name, attendee_last_name;
	private LatLng latLng1;
	private LatLng latLng2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attendee_location);
		ButterKnife.inject(this);
		
		if (toolbar != null) {
			setSupportActionBar(toolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		Intent i = getIntent();
		Bundle b = i.getExtras();

		attendee_first_name = b.getString("friend_" + Friend.id_first_name);
		attendee_last_name = b.getString("friend_" + Friend.id_last_name);
		latLng1 = new LatLng(Double.valueOf(b.getString("event_" + Event.id_latitude)), Double.valueOf(b.getString("event_" + Event.id_longitude)));
		latLng2 = new LatLng(Double.valueOf(b.getString("friend_" + Friend.id_latitude)), Double.valueOf(b.getString("friend_" + Friend.id_longitude)));
		
		setTitle(attendee_first_name + "'s Location");
		initializeMap();
//		14.563935, 120.994237
		
	}
	
	private void initializeMap(){
		
		if (googleMap == null) {
		
			googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
					R.id.attendee_activity_location_map)).getMap();
			
			markerOptions1 = new MarkerOptions();
			markerOptions2 = new MarkerOptions().title(attendee_first_name + " " + attendee_last_name).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
			
			markerOptions1.position(latLng1);
			markerOptions2.position(latLng2);
			googleMap.addMarker(markerOptions1);
			marker2 = googleMap.addMarker(markerOptions2);
			marker2.showInfoWindow();
			
			CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng1, 20);
			googleMap.moveCamera(cameraUpdate);
			
			googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
				
				@Override
				public void onMapLoaded() {
			
					LatLngBounds.Builder builder = LatLngBounds.builder().include(latLng1).include(latLng2);
					
					LatLngBounds bounds = builder.build();
					
					googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
					
				}
				
			});

		}

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		new MenuInflater(getApplication()).inflate(R.menu.attendee_location, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (android.R.id.home == id) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
