package com.mobicom.moihana.AsyncTasks;

import java.io.IOException;

import org.jsoup.Jsoup;

import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener;
import com.mobicom.moihana.Model.HTML;

import android.os.AsyncTask;
import android.util.Log;

public class SendLocation extends AsyncTask<String, Void, Boolean>{

	private final String TAG = "Send Location";
	private AsyncGetResultTaskCompleteListener<Boolean> listener;
	
	public SendLocation(AsyncGetResultTaskCompleteListener<Boolean> listener) {
		this.listener = listener;
	}
	
	@Override
	protected Boolean doInBackground(String... arg) {
		
		try {
			
			Jsoup.connect(HTML.website + HTML.send_location)
				.data(HTML.post_event_id, arg[0])
				.data(HTML.post_latitude, arg[1])
				.data(HTML.post_longitude, arg[2])
				.userAgent(HTML.user_agent)
				.cookie(HTML.session_id, HTML.SessionID)
				.post();
							
		} catch (IOException e) {
			Log.e(TAG, e.getMessage() + " e");
		}
		
		return true;
		
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		listener.display_result(true);
	}
	
}
