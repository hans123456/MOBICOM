package com.mobicom.moihana.AsyncTasks;

import org.jsoup.Jsoup;

import android.os.AsyncTask;
import android.util.Log;

import com.mobicom.moihana.Model.HTML;

public class InviteFriends extends AsyncTask<String, Void, String>{
	
	private final String TAG = "Invite Friends";
	
	@Override
	protected String doInBackground(String... arg) {
		
		String event_id = arg[0];
		String imploded_text = arg[1];
		
		String result = null;
		
		try {
			
			Jsoup.connect(HTML.website + HTML.invite_friends)
				.data(HTML.post_event_id, event_id)
				.data(HTML.post_invited_friends, imploded_text)
				.userAgent(HTML.user_agent)
				.cookie(HTML.session_id, HTML.SessionID)
				.post();

			result = "Sucess";
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage() + " e");
		}
		
		return result;
		
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}
	
}
