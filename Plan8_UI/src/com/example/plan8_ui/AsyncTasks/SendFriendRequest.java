package com.example.plan8_ui.AsyncTasks;

import java.io.IOException;

import org.jsoup.Jsoup;

import com.example.plan8_ui.Interfaces.AsyncGetResultTaskCompleteListener;
import com.example.plan8_ui.Model.HTML;
import com.google.android.gms.internal.li;

import android.os.AsyncTask;
import android.util.Log;

public class SendFriendRequest extends AsyncTask<String, Void, Boolean>{

	private final String TAG = "Send Friend Request";
	AsyncGetResultTaskCompleteListener<Boolean> listener;
	
	public SendFriendRequest(AsyncGetResultTaskCompleteListener<Boolean> listener) {
		this.listener = listener;
	}
	
	@Override
	protected Boolean doInBackground(String... arg) {

		try {
			
			Jsoup.connect(HTML.website + HTML.send_friend_request)
				  .data(HTML.post_friend_id, arg[0])
				  .userAgent(HTML.user_agent)
				  .cookie(HTML.session_id, HTML.SessionID)
				  .post();
			
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		}
		
		return true;
		
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		listener.display_result(result);
	}
	
}
