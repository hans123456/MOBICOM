package com.mobicom.moihana.Events;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
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
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobicom.moihana.R;
import com.mobicom.moihana.AsyncTasks.DeleteEvent;
import com.mobicom.moihana.AsyncTasks.EditEventInfo;
import com.mobicom.moihana.AsyncTasks.FetchEventInfo;
import com.mobicom.moihana.AsyncTasks.InviteFriends;
import com.mobicom.moihana.AsyncTasks.SetInvitationStatus;
import com.mobicom.moihana.Friends.EventAttendeesActivity;
import com.mobicom.moihana.Friends.InviteFriendsActivity;
import com.mobicom.moihana.Interfaces.AsyncGetInfoTaskCompleteListener;
import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener;
import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener2;
import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener3;
import com.mobicom.moihana.Model.CreateEventResult;
import com.mobicom.moihana.Model.Event;

public class EventProfileActivity extends ActionBarActivity implements AsyncGetInfoTaskCompleteListener<Event>, AsyncGetResultTaskCompleteListener<CreateEventResult>, AsyncGetResultTaskCompleteListener2<Boolean>, AsyncGetResultTaskCompleteListener3<Boolean> {
	
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
	private boolean editable = false;
	private MenuItem edit, cancel, save, delete;
	private boolean you = true;
	private ProgressDialog progressDialog;
	
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
			
			markerOptions = new MarkerOptions();

		}

		title_edit_text.setEnabled(false);
		date_start_edit_text.setEnabled(false);
		time_start_edit_text.setEnabled(false);
		date_end_edit_text.setEnabled(false);
		time_end_edit_text.setEnabled(false);
		location_edit_text.setEnabled(false);
		description_edit_text.setEnabled(false);
		
		Intent i = getIntent();
		Bundle b = i.getExtras();
		
		String id = b.getString(Event.id_id);
		
		progressDialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("Fetching Event Information Please Wait");
		new FetchEventInfo(this).execute(id);
		progressDialog.show();
		
	}
	
	private OnMapClickListener map_OMCL = new OnMapClickListener() {
		
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
		i.putExtra(Event.id_id, event.get_information(Event.id_id));
		startActivityForResult(i, 0);
	}
	
	@OnClick(R.id.event_profile_activity_attendees_button)
	public void onClickAttendees(View v) {
		Intent i = new Intent();
		i.setClass(getBaseContext(), EventAttendeesActivity.class);
		i.putExtra(Event.id_id, event.get_information(Event.id_id));
		i.putExtra(Event.id_latitude, event.get_information(Event.id_latitude));
		i.putExtra(Event.id_longitude, event.get_information(Event.id_longitude));
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
				newDate.set(Calendar.HOUR_OF_DAY, hour);
				newDate.set(Calendar.MINUTE, minute);
                tempET.setText(timeFormatter.format(newDate.getTime()));
			}
 
        },newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), true);

		tpd.show();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		new MenuInflater(getApplication()).inflate(R.menu.event_profile, menu);

		edit = menu.findItem(R.id.event_profile_edit);
		cancel = menu.findItem(R.id.event_profile_cancel);
		save = menu.findItem(R.id.event_profile_save);
		delete = menu.findItem(R.id.event_profile_delete);
		
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
		
		if(android.R.id.home == id) {
			progressDialog.dismiss();
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
			
			if(you){
			
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
				
				initialize();
			
			}else {
				
				new SetInvitationStatus(this).execute(event.get_information(Event.id_id), String.valueOf(2));
				
			}
				
		}
		
		if(R.id.event_profile_save == id){
			
			if(you){
			
				String[] inputs = {
						event.get_information(Event.id_id),
						title_edit_text.getText().toString(),
						description_edit_text.getText().toString(),
						location_edit_text.getText().toString(),
						date_start_edit_text.getText().toString(),
						time_start_edit_text.getText().toString(),
						date_end_edit_text.getText().toString(),
						time_end_edit_text.getText().toString(),
						String.valueOf(marker.getPosition().latitude),
						String.valueOf(marker.getPosition().longitude)
				};
				
				new EditEventInfo(this).execute(inputs);
			
			}else {

				new SetInvitationStatus(this).execute(event.get_information(Event.id_id), String.valueOf(1));
				
			}
				
		}
		
		if(R.id.event_profile_delete == id){
			
			if(you){
				new DeleteEvent(this).execute(event.get_information(Event.id_id));
			}
			
		}
		
		return super.onOptionsItemSelected(item);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == 0){
			if(resultCode == RESULT_OK){
				Bundle b = data.getExtras();
				new InviteFriends().execute(event.get_information(Event.id_id), b.getString(InviteFriendsActivity.id_friends_ids));
			}
		}
		
	}
	
	public void initialize(){

		getSupportActionBar().setTitle(event.get_information(Event.id_title));
		title_edit_text.setText(event.get_information(Event.id_title));
		location_edit_text.setText(event.get_information(Event.id_location));
		description_edit_text.setText(event.get_information(Event.id_description));
		date_start_edit_text.setText(event.get_information(Event.id_date_start));
		time_start_edit_text.setText(event.get_information(Event.id_time_start));
		date_end_edit_text.setText(event.get_information(Event.id_date_end));
		time_end_edit_text.setText(event.get_information(Event.id_time_end));
		
        double latitude = Double.parseDouble(event.get_information(Event.id_latitude));
        double longitude = Double.parseDouble(event.get_information(Event.id_longitude));
        
        LatLng latLng = new LatLng(latitude, longitude);
        
        markerOptions.position(latLng);
        googleMap.clear();
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
		googleMap.animateCamera(cameraUpdate);
        marker = googleMap.addMarker(markerOptions);
		
	}
	
	@Override
	public void display_info(Event info) {
		
		this.event = info;
		initialize();
		
		double latitude = Double.parseDouble(event.get_information(Event.id_latitude));
        double longitude = Double.parseDouble(event.get_information(Event.id_longitude));
        
        LatLng latLng = new LatLng(latitude, longitude);
		
		markerOptions.position(latLng);
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
		googleMap.moveCamera(cameraUpdate);
		
		if(event.get_information(Event.id_you).equals("false") == true){
			you = false;
			edit.setVisible(false);
			cancel.setVisible(true);
			save.setVisible(true);
		}
		
		progressDialog.dismiss();
		
	}

	@Override
	public void display_result(CreateEventResult result) {

		if(result.isSuccessful()){
			Intent i = new Intent();
			setResult(RESULT_OK, i);
			Toast.makeText(getBaseContext(), "Event Edited Successfully", Toast.LENGTH_LONG).show();
			progressDialog.dismiss();
			finish();
		}else{
			Toast.makeText(getBaseContext(), result.get_result(), Toast.LENGTH_LONG).show();
		}
	
	}

	@Override
	public void display_result2(Boolean result) {

		Intent i = new Intent();
		setResult(RESULT_OK, i);
		progressDialog.dismiss();
		finish();
		
	}

	@Override
	public void display_result3(Boolean result) {
		
		Intent i = new Intent();
		setResult(RESULT_OK, i);
		progressDialog.dismiss();
		Toast.makeText(getBaseContext(), "Event Deleted Successfully", Toast.LENGTH_LONG).show();
		finish();
		
	}
	
}
