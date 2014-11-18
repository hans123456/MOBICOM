package com.example.plan8_ui.Login_Page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Main_Page.MainMenuActivity;
	
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		// action bar
		Toolbar toolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

		Button login_button = (Button) findViewById(R.id.main_activity_login_button);
		login_button.setOnClickListener(login_button_ocl);
		
		Button register_button = (Button) findViewById(R.id.main_activity_register_button);
		register_button.setOnClickListener(register_button_ocl);
		
	}
	
	private OnClickListener login_button_ocl = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			
			Intent i = new Intent();
			i.setClass(getBaseContext(), MainMenuActivity.class);
			//i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(i);
			
		}
		
	};
	
	private OnClickListener register_button_ocl = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			
			Intent i = new Intent();
			i.setClass(getBaseContext(), RegisterActivity.class);
			//i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivityForResult(i, 0);
			
		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
		
	}
}
