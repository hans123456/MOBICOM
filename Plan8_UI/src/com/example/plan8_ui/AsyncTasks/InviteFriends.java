package com.example.plan8_ui.AsyncTasks;

import org.jsoup.Jsoup;

import android.os.AsyncTask;
import android.util.Log;

import com.example.plan8_ui.Interfaces.AsyncResultTaskCompleteListener;
import com.example.plan8_ui.Model.HTML;

public class InviteFriends extends AsyncTask<String, Void, String>{
	
	private final String TAG = "Invite Friends";
	private AsyncResultTaskCompleteListener<String> listener;
	
	@Override
	protected String doInBackground(String... arg) {
		
		StringBuilder imploded_text = new StringBuilder();
		boolean first = true;
		
		for(String i : arg){
			if(false == first)
				imploded_text.append(',');
			imploded_text.append(i);
			first = false;
		}
		
//		Document doc;
		String result = null;
		
		try {
			
//			doc = 
			Jsoup.connect(HTML.website + HTML.invite_friends)
				  .data(HTML.post_invited_friends, imploded_text.toString())
				  .userAgent(HTML.user_agent)
				  .post();
//			
//			Element json_element = doc.getElementById(HTML.element_id);
//			
//			JSONArray json_array = new JSONArray(json_element.text());
//			JSONObject json_object = json_array.getJSONObject(0);
//			
			result = "Sucess";
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		
		return result;
		
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		listener.display_result(result);
	}
	
}