package com.mobicom.moihana.AsyncTasks;

import java.io.IOException;

import org.jsoup.Jsoup;

import com.mobicom.moihana.Interfaces.AsyncGetResultTaskCompleteListener2;
import com.mobicom.moihana.Model.HTML;

import android.os.AsyncTask;
import android.util.Log;

public class SetInvitationStatus extends AsyncTask<String, Void, Boolean>{

	private final String TAG = "Invitation Status";
	AsyncGetResultTaskCompleteListener2<Boolean> listener;
	
	public SetInvitationStatus(AsyncGetResultTaskCompleteListener2<Boolean> listener) {
		this.listener = listener;
	}
	
	@Override
	protected Boolean doInBackground(String... arg) {

		try {
			
			Jsoup.connect(HTML.website + HTML.invitation_status)
				  .data(HTML.post_event_id, arg[0])
				  .data(HTML.post_invitation_status, arg[1])
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
		listener.display_result2(result);
	}
	
}
