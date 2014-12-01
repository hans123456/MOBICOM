package com.example.plan8_ui.AsyncTasks;

import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.os.AsyncTask;
import android.util.Log;

import com.example.plan8_ui.Interfaces.AsyncGetInfoTaskCompleteListener;
import com.example.plan8_ui.Model.Friend;
import com.example.plan8_ui.Model.HTML;

public class FetchFriendInfo extends AsyncTask<String, Void, Friend>{

	private final String TAG = "Friend Info";
	private AsyncGetInfoTaskCompleteListener<Friend> listener;
	
	public FetchFriendInfo(AsyncGetInfoTaskCompleteListener<Friend> listener) {
		this.listener = listener;
	}
	
	@Override
	protected Friend doInBackground(String... arg) {
		
		Friend friend = new Friend();

		try {
			
			Document doc = Jsoup.connect(HTML.website + HTML.search_profile_by_unique_id)
								.data(HTML.post_unique_id, arg[0])
								.userAgent(HTML.user_agent)
								.cookie(HTML.session_id, HTML.SessionID)
								.post();
			
			Element json_element = doc.getElementById(HTML.element_id);
			
			if(json_element.text().equals("empty") == false){
				
				JSONArray json_array = new JSONArray(json_element.text());
				int length = json_array.length();
				
				for(int j=0; j<length; j++){
					
					JSONObject o = json_array.getJSONObject(j);
					Iterator<String> i = o.keys();
					
					while(i.hasNext()){
						String key = i.next();
						String value = o.getString(key);
						friend.put_information(key, value);					
					}
					
				}
			
			}
			
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}
		
		return friend;
		
	}
	
	@Override
	protected void onPostExecute(Friend result) {
		super.onPostExecute(result);
		listener.display_info(result);
	}
	
}
