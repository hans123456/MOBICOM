package com.mobicom.moihana.AsyncTasks;

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

import com.mobicom.moihana.Interfaces.AsyncFetchListTaskCompleteListener;
import com.mobicom.moihana.Model.Friend;
import com.mobicom.moihana.Model.HTML;

public class FetchFriends extends AsyncTask<Void, Void, ArrayList<Friend>> {

	private final String TAG = "Friends";
	private AsyncFetchListTaskCompleteListener<ArrayList<Friend>> listener;

	public FetchFriends(AsyncFetchListTaskCompleteListener<ArrayList<Friend>> listener) {
		this.listener = listener;
	}

	@Override
	protected ArrayList<Friend> doInBackground(Void... arg0) {

		ArrayList<Friend> friends = new ArrayList<Friend>();

		try {

			Document doc = Jsoup.connect(HTML.website + HTML.friends)
								.userAgent(HTML.user_agent)
								.cookie(HTML.session_id, HTML.SessionID)
								.get();
			
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
			Log.e(TAG, e.getMessage() + " e");
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage() + " e");
		}

		return friends;

	}

	@Override
	protected void onPostExecute(ArrayList<Friend> result) {
		super.onPostExecute(result);
		listener.update_list(result);
	}

}
