package com.example.plan8_ui.Events;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TimePicker;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Friends.EventAttendeesActivity;
import com.example.plan8_ui.Friends.InviteFriendsActivity;
import com.example.plan8_ui.Interfaces.AsyncResultTaskCompleteListener;
import com.example.plan8_ui.Model.Event;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventProfileActivity extends ActionBarActivity implements AsyncResultTaskCompleteListener<Event> {
	
	@InjectView(R.id.event_profile_activity_title_edit_text) EditText title_edit_text;
	@InjectView(R.id.event_profile_activity_date_start_edit_text) EditText date_start_edit_text;
	@InjectView(R.id.event_profile_activity_time_start_edit_text) EditText time_start_edit_text;
	@InjectView(R.id.event_profile_activity_date_end_edit_text) EditText date_end_edit_text;
	@InjectView(R.id.event_profile_activity_time_end_edit_text) EditText time_end_edit_text;
	@InjectView(R.id.event_profile_activity_location_edit_text) EditText location_edit_text;
	@InjectView(R.id.event_profile_activity_description_edit_text) EditText description_edit_text;
	
	@InjectView(R.id.event_profile_activity_invite_button) Button invite_button;
	@InjectView(R.id.event_profile_activity_attendees_button) Button attendees_button;
	
	@InjectView(R.id.event_profile_activity_activity_toolbar) Toolbar toolbar;
	
	@InjectView(R.id.event_profile_scroll_view) ScrollView scroll_view;
	
	private GoogleMap googleMap;
	private MarkerOptions markerOptions;
	private Marker marker;
	private EditText tempET;
	private SimpleDateFormat dateFormatter, timeFormatter;
	private Event event;
	private Menu menu;
	private boolean first = true, editable = false;
	private MenuItem edit, cancel, save, delete;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_profile);
		ButterKnife.inject(this);
		
		InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
		
		if (toolbar != null) {
			setSupportActionBar(toolbar);
		    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		dateFormatter = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
		timeFormatter = new SimpleDateFormat("HH:mm", Locale.US);
		
		if (googleMap == null) {

            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
                    R.id.event_profile_activity_map_fragment)).getMap();
            
            googleMap.setOnMapClickListener(map_OMCL);
            
		}

		title_edit_text.setEnabled(false);
		date_start_edit_text.setEnabled(false);
		time_start_edit_text.setEnabled(false);
		date_end_edit_text.setEnabled(false);
		time_end_edit_text.setEnabled(false);
		location_edit_text.setEnabled(false);
		description_edit_text.setEnabled(false);
		
//		Intent i = getIntent();
//		Bundle b = i.getExtras();
//		
//		String id = b.getString(Event.id_id);
//		
//		new FetchEventInfo(this).execute(id);

	}
	
	OnMapClickListener map_OMCL = new OnMapClickListener() {
		
		@Override
		public void onMapClick(LatLng point) {

			if(editable == true){
			
				marker.remove();
				markerOptions.position(point);
				marker = googleMap.addMarker(markerOptions.draggable(true));
				googleMap.animateCamera(CameraUpdateFactory.newLatLng(point));
            
			}
				
		}
		
	};
	
	@OnClick(R.id.event_profile_activity_invite_button)
	public void onClickInvite(View v) {
		Intent i = new Intent();
		i.setClass(getBaseContext(), InviteFriendsActivity.class);
		startActivityForResult(i, 0);
	}
	
	@OnClick(R.id.event_profile_activity_attendees_button)
	public void onClickAttendees(View v) {
		Intent i = new Intent();
		i.setClass(getBaseContext(), EventAttendeesActivity.class);
		startActivity(i);
	}

	@OnClick(R.id.event_profile_activity_date_start_edit_text)
	public void onClickDateStart(View arg0) {
		tempET = date_start_edit_text;
		showDatePicker();
	}
	
	@OnClick(R.id.event_profile_activity_time_start_edit_text)
	public void onClickTimeStart(View v) {
		tempET = time_start_edit_text;
		showTimePicker();
	}

	@OnClick(R.id.event_profile_activity_date_end_edit_text)
	public void onClickDateEnd(View arg0) {
		tempET = date_end_edit_text;
		showDatePicker();
	}
	
	@OnClick(R.id.event_profile_activity_time_end_edit_text)
	public void onClickTimeEnd(View v) {
		tempET = time_end_edit_text;
		showTimePicker();
	}
		
	private void showDatePicker(){
		
		Calendar newCalendar = Calendar.getInstance();
		DatePickerDialog dpd = new DatePickerDialog(EventProfileActivity.this, new OnDateSetListener() {

			@Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tempET.setText(dateFormatter.format(newDate.getTime()));
            }
 
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
		dpd.show();
		
	}
	
	private void showTimePicker(){
		
		Calendar newCalendar = Calendar.getInstance();
		
		TimePickerDialog tpd = new TimePickerDialog(EventProfileActivity.this, new OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hour, int minute) {
				Calendar newDate = Calendar.getInstance();
				newDate.set(Calendar.HOUR, hour);
				newDate.set(Calendar.MINUTE, minute);
                tempET.setText(timeFormatter.format(newDate.getTime()));
			}
 
        },newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);
		
		tpd.show();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		new MenuInflater(getApplication()).inflate(R.menu.event_profile, menu);
		this.menu = menu;
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
		
		if(true == first){
			edit = menu.findItem(R.id.event_profile_edit);
			cancel = menu.findItem(R.id.event_profile_cancel);
			save = menu.findItem(R.id.event_profile_save);
			delete = menu.findItem(R.id.event_profile_delete);
			first = false;
		}
		
		if(android.R.id.home == id) {
			finish();
			return true;
		}

		if(R.id.event_profile_edit == id){
			
			edit.setVisible(false);
			title_edit_text.setEnabled(true);
			date_start_edit_text.setEnabled(true);
			time_start_edit_text.setEnabled(true);
			date_end_edit_text.setEnabled(true);
			time_end_edit_text.setEnabled(true);
			location_edit_text.setEnabled(true);
			description_edit_text.setEnabled(true);
			
			cancel.setVisible(true);
	        save.setVisible(true);
	        delete.setVisible(true);
	        editable = true;
            
		}
		
		if(R.id.event_profile_cancel == id){
			
			cancel.setVisible(false);
	        save.setVisible(false);
	        delete.setVisible(false);
			edit.setVisible(true);
			
			title_edit_text.setEnabled(false);
			date_start_edit_text.setEnabled(false);
			time_start_edit_text.setEnabled(false);
			date_end_edit_text.setEnabled(false);
			time_end_edit_text.setEnabled(false);
			location_edit_text.setEnabled(false);
			description_edit_text.setEnabled(false);
			editable = false;
			
		}
		
		return super.onOptionsItemSelected(item);
		
	}

	@Override
	public void display_result(Event result) {
		
		this.event = result;
		
		title_edit_text.setText(result.get_information(Event.id_title));
		location_edit_text.setText(result.get_information(Event.id_location));
		description_edit_text.setText(result.get_information(Event.id_description));
		date_start_edit_text.setText(result.get_information(Event.id_date_start));
		time_start_edit_text.setText(result.get_information(Event.id_time_start));
		date_end_edit_text.setText(result.get_information(Event.id_date_end));
		time_end_edit_text.setText(result.get_information(Event.id_time_end));
		
        markerOptions = new MarkerOptions();
        
        double latitude = Double.parseDouble(result.get_information(Event.id_latitude));
        double longitude = Double.parseDouble(result.get_information(Event.id_longitude));
        
        LatLng latLng = new LatLng(latitude, longitude);
        
        markerOptions.position(latLng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        googleMap.moveCamera(cameraUpdate);
        marker = googleMap.addMarker(markerOptions);
		
	}
	
}
