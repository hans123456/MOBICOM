package com.mobicom.moihana.AsyncTasks;

import android.os.AsyncTask;

import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener;

public class EditUser extends AsyncTask<String, Void, Boolean>{

	private AsyncGetResultTaskCompleteListener<Boolean> listener;
	
	public EditUser(AsyncGetResultTaskCompleteListener<Boolean> listener) {
		this.listener = listener;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		return true;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		listener.display_result(result);
	}

}
