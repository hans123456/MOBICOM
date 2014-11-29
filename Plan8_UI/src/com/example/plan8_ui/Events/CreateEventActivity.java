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
import android.widget.TimePicker;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Friends.InviteFriendsActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class CreateEventActivity extends ActionBarActivity {

	@InjectView(R.id.create_event_activity_title_edit_text) EditText title_edit_text;
	@InjectView(R.id.create_event_activity_location_edit_text) EditText location_edit_text;
	@InjectView(R.id.create_event_activity_description_edit_text) EditText description_edit_text;
	@InjectView(R.id.create_event_activity_date_start_edit_text) EditText date_start_edit_text;
	@InjectView(R.id.create_event_activity_time_start_edit_text) EditText time_start_edit_text;
	@InjectView(R.id.create_event_activity_date_end_edit_text) EditText date_end_edit_text;
	@InjectView(R.id.create_event_activity_time_end_edit_text) EditText time_end_edit_text;
	
	@InjectView(R.id.create_event_activity_invite_button) Button inviteButton;
	
	@InjectView(R.id.create_event_activity_activity_toolbar) Toolbar toolbar;
	
	private SimpleDateFormat dateFormatter, timeFormatter;
	private EditText tempET;
	private GoogleMap googleMap;
	private MarkerOptions markerOptions;
	private Marker marker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		ButterKnife.inject(this);
		
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        
        dateFormatter = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        timeFormatter = new SimpleDateFormat("HH:mm", Locale.US);
        
        initializeMap();
        
	}
	
	private void initializeMap(){
		
		if (googleMap == null) {

            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
                    R.id.create_event_activity_map_fragment)).getMap();
            
            markerOptions = new MarkerOptions().draggable(true);
            
            LatLng latLng = new LatLng(14.565041, 120.993936);
            markerOptions.position(latLng);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
            googleMap.moveCamera(cameraUpdate);
            googleMap.setOnMapClickListener(map_OMCL);
            marker = googleMap.addMarker(markerOptions.draggable(true));

		}

	}
	
	private OnMapClickListener map_OMCL = new OnMapClickListener() {
		
		@Override
		public void onMapClick(LatLng point) {

			marker.remove();
			markerOptions.position(point);
			marker = googleMap.addMarker(markerOptions.draggable(true));
			googleMap.animateCamera(CameraUpdateFactory.newLatLng(point));
			
		}
		
	};
	
	@OnClick(R.id.create_event_activity_invite_button)
	public void onClickInvite(View v) {
		// TODO Auto-generated method stub
		
		Intent i = new Intent();
		i.setClass(getBaseContext(), InviteFriendsActivity.class);
		startActivityForResult(i, 0);
		
	}
	
	private void showDatePicker(){
		
		Calendar newCalendar = Calendar.getInstance();
		DatePickerDialog dpd = new DatePickerDialog(CreateEventActivity.this, new OnDateSetListener() {

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
		
		TimePickerDialog tpd = new TimePickerDialog(CreateEventActivity.this, new OnTimeSetListener() {

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
	
	@OnClick(R.id.create_event_activity_date_start_edit_text)
	public void onClickDateStart(View v) {
		// TODO Auto-generated method stub
		tempET = date_start_edit_text;
		showDatePicker();
	}
	
	@OnClick(R.id.create_event_activity_date_end_edit_text)
	public void onClickDateEnd(View arg0) {
		// TODO Auto-generated method stub
		tempET = date_end_edit_text;
		showDatePicker();
	}
	
	@OnClick(R.id.create_event_activity_time_start_edit_text)
	public void onClickTimeStart(View v) {
		// TODO Auto-generated method stub
		tempET = time_start_edit_text;
		showTimePicker();
	}
	
	@OnClick(R.id.create_event_activity_time_end_edit_text)
	public void onClickTimeEnd(View v) {
		// TODO Auto-generated method stub
		tempET = time_end_edit_text;
		showTimePicker();
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		new MenuInflater(getApplication()).inflate(R.menu.create_event, menu);
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
		
		if (id == R.id.create_event_cancel) {
			finish();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
		
	}

}
