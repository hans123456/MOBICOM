package com.example.plan8_ui.AsyncTasks;

import com.example.plan8_ui.Interfaces.AsyncGetResultTaskCompleteListener;

import android.os.AsyncTask;

public class SendLocation extends AsyncTask<String, Void, Boolean>{

	AsyncGetResultTaskCompleteListener<Boolean> listener;
	
	public SendLocation(AsyncGetResultTaskCompleteListener<Boolean> listener) {
		this.listener = listener;
	}
	
	@Override
	protected Boolean doInBackground(String... arg) {
		return true;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		listener.display_result(true);
	}
	
}
