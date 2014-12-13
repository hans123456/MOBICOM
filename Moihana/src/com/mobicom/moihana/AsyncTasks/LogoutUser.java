package com.mobicom.moihana.AsyncTasks;

import org.jsoup.Jsoup;

import android.os.AsyncTask;
import android.util.Log;

import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener;
import com.mobicom.moihana.Model.HTML;

public class LogoutUser extends AsyncTask<Void, Void, String>{

	private final String TAG = "Logout User";
	private AsyncGetResultTaskCompleteListener<String> listener;
	
	public LogoutUser(AsyncGetResultTaskCompleteListener<String> listener) {
		this.listener = listener;
	}
	
	@Override
	protected String doInBackground(Void... params) {
		
		String result = null;
		
		try {
			
			Jsoup.connect(HTML.website + HTML.logout)
						  .userAgent(HTML.user_agent)
						  .execute();
			
			HTML.SessionID = null;
			result = "Successfully Logged Out";
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage() + " e");
		}
		
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		listener.display_result(result);
	}

}
