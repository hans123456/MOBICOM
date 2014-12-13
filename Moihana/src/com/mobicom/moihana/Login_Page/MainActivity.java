package com.mobicom.moihana.Login_Page;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.mobicom.moihana.R;
import com.mobicom.moihana.AsyncTasks.LoginUser;
import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener;
import com.mobicom.moihana.Main_Page.MainMenuActivity;
import com.mobicom.moihana.Model.HTML;
import com.mobicom.moihana.Model.User;
	
public class MainActivity extends ActionBarActivity implements AsyncGetResultTaskCompleteListener<User>{

	@InjectView(R.id.main_activity_toolbar) Toolbar toolbar;
	@InjectView(R.id.main_activity_username_edit_text) EditText username_edit_text;
	@InjectView(R.id.main_activity_password_edit_text) EditText password_edit_text;
	
	private Intent i;
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);
		
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

		HTML.userProfile = null;
        username_edit_text.setText("administrator");
        password_edit_text.setText("password");
        
        progressDialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("Logging In Please Wait");

	}
	
	@OnClick(R.id.main_activity_login_button)
	public void onClickLogin(View arg0) {
		
		i = new Intent();
		i.setClass(getBaseContext(), MainMenuActivity.class);
		
		String username =  username_edit_text.getText().toString();
		String password =  password_edit_text.getText().toString();
		new LoginUser(this).execute(username, password);
		progressDialog.show();
        
	}
	
	@OnClick(R.id.main_activity_register_button)
	public void onClickRegister(View arg0) {
		
		i = new Intent();
		i.setClass(getBaseContext(), RegisterActivity.class);
		startActivityForResult(i, 0);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		new MenuInflater(getApplication()).inflate(R.menu.main, menu);
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

	@Override
	public void display_result(User result) {
		
		if(result.is_valid()){
			HTML.userProfile = result;
			progressDialog.dismiss();
			startActivity(i);
		}else{
			HTML.userProfile = null;
			Toast.makeText(getBaseContext(), "Invalid Login Credentials", Toast.LENGTH_LONG).show();
		}
		
	}
	
}
