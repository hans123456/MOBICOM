package com.mobicom.moihana.AsyncTasks;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.os.AsyncTask;
import android.util.Log;

import com.mobicom.moihana.Interfaces.AsyncFetchListTaskCompleteListener;
import com.mobicom.moihana.Model.Event;
import com.mobicom.moihana.Model.HTML;

public class FetchInvitedEvents extends AsyncTask<Void, Void, ArrayList<Event>> {

	private final String TAG = "Invited Events";
	private AsyncFetchListTaskCompleteListener<ArrayList<Event>> listener;
	
	public FetchInvitedEvents(AsyncFetchListTaskCompleteListener<ArrayList<Event>> listener) {
		this.listener = listener;
	}
	
	@Override
	protected ArrayList<Event> doInBackground(Void... arg0) {

		Document doc;
		ArrayList<Event> events = new ArrayList<Event>();
		
		try {
			
			doc = Jsoup.connect(HTML.website + HTML.invited_events)
						.userAgent(HTML.user_agent)
						.cookie(HTML.session_id, HTML.SessionID)
						.get();
			
			Element json_element = doc.getElementById(HTML.element_id);
			
			if(json_element.text().equals("empty") == false){
			
				JSONArray json_array = new JSONArray(json_element.text());
				int length = json_array.length();
				
				for(int j=0; j<length; j++){
					
					JSONObject o = json_array.getJSONObject(j);
					Event event = new Event();
					Iterator<String> i = o.keys();
					
					while(i.hasNext()){
						String key = i.next();
						String value = o.getString(key);
						event.put_information(key, value);					
					}
					
					events.add(event);
					
				}
			
			}
				
		} catch (Exception e) {
			Log.e(TAG, e.getMessage() + " e");
		}
		
		return events;
					
	}

	@Override
	protected void onPostExecute(ArrayList<Event> result) {
		super.onPostExecute(result);
		listener.update_list(result);
	}
	
}
