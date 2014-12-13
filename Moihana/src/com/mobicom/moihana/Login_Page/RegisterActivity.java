package com.mobicom.moihana.Login_Page;
	
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.mobicom.moihana.R;
import com.mobicom.moihana.AsyncTasks.RegisterUser;
import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener;
import com.mobicom.moihana.Model.RegisterResult;

public class RegisterActivity extends ActionBarActivity implements AsyncGetResultTaskCompleteListener<RegisterResult>{

	@InjectView(R.id.register_activity_username_edit_text) EditText username_edit_text;
	@InjectView(R.id.register_activity_email_edit_text) EditText email_edit_text;
	@InjectView(R.id.register_activity_first_name_edit_text) EditText first_name_edit_text;
	@InjectView(R.id.register_activity_last_name_edit_text) EditText last_name_edit_text;
	@InjectView(R.id.register_activity_password_edit_text) EditText password_edit_text;
	@InjectView(R.id.register_activity_confirm_password_edit_text) EditText confirm_password_edit_text;
	
	@InjectView(R.id.register_activity_toolbar) Toolbar toolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ButterKnife.inject(this);
		
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(getApplication()).inflate(R.menu.register, menu);
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
		
		if (id == R.id.register_cancel) {
			
			finish();
			return true;
			
		}else if(id == R.id.register_send){
			
			String[] inputs = {
				username_edit_text.getText().toString(),
				email_edit_text.getText().toString(),
				first_name_edit_text.getText().toString(),
				last_name_edit_text.getText().toString(),
				password_edit_text.getText().toString(),
				confirm_password_edit_text.getText().toString()
			};
			
			new RegisterUser(this).execute(inputs);
			
		}
		
		return super.onOptionsItemSelected(item);
		
	}

	@Override
	public void display_result(RegisterResult result) {
		
		String r = result.get_result();
		Toast.makeText(getBaseContext(), r, Toast.LENGTH_LONG).show();;
		
		if(r.equals(RegisterResult.SUCCESS)){
			finish();
		}
		
	}
}
