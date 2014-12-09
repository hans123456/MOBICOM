package com.example.plan8_ui.AsyncTasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.os.AsyncTask;
import android.util.Log;

import com.example.plan8_ui.Interfaces.AsyncFetchListTaskCompleteListener;
import com.example.plan8_ui.Model.Friend;
import com.example.plan8_ui.Model.HTML;

public class FetchFriendsNotYetInvited extends AsyncTask<String, Void, ArrayList<Friend>>{

	private final String TAG = "Friends Not Yet Invited";
	AsyncFetchListTaskCompleteListener<ArrayList<Friend>> listener;
	
	public FetchFriendsNotYetInvited(AsyncFetchListTaskCompleteListener<ArrayList<Friend>> listener) {
		this.listener = listener;
	}
	
	@Override
	protected ArrayList<Friend> doInBackground(String... arg) {
		
		ArrayList<Friend> friends = new ArrayList<Friend>();
		
		try {
			Document doc;
			
			doc = Jsoup.connect(HTML.website + HTML.friends_not_yet_invited)
							.data(HTML.post_event_id, arg[0])
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
					Friend friend = new Friend();
	
					while(i.hasNext()){
						String key = i.next();
						String value = o.getString(key);
						friend.put_information(key, value);
					}
	
					friends.add(friend);
	
				}
			
			}
				
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}
		
		return friends;
		
	}
	
	@Override
	protected void onPostExecute(ArrayList<Friend> result) {
		super.onPostExecute(result);
		listener.update_list(result);
	}

}
