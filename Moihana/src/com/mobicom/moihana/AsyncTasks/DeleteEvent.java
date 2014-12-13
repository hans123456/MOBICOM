package com.mobicom.moihana.AsyncTasks;

import java.io.IOException;

import org.jsoup.Jsoup;

import android.os.AsyncTask;
import android.util.Log;

import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener3;
import com.mobicom.moihana.Model.HTML;

public class DeleteEvent extends AsyncTask<String, Void, Boolean>{

	private final String TAG = "Delete Event";
	private AsyncGetResultTaskCompleteListener3<Boolean> listener;
	
	public DeleteEvent(AsyncGetResultTaskCompleteListener3<Boolean> listener) {
		this.listener = listener;
	}
	
	@Override
	protected Boolean doInBackground(String... arg) {
		
		try {
			
			Jsoup.connect(HTML.website + HTML.delete_event)
				  .data(HTML.post_event_id, arg[0])
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
		listener.display_result3(result);
	}
	
}
