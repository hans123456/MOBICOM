package com.example.plan8_ui.AsyncTasks;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.os.AsyncTask;
import android.util.Log;

import com.example.plan8_ui.Interfaces.AsyncFetchListTaskCompleteListener;
import com.example.plan8_ui.Model.Event;
import com.example.plan8_ui.Model.HTML;

public class FetchInvitedEvents extends AsyncTask<Void, Void, ArrayList<Event>> {

	private final String TAG = "Invited Events";
	private AsyncFetchListTaskCompleteListener<ArrayList<Event>> listener;
	
	public FetchInvitedEvents(AsyncFetchListTaskCompleteListener<ArrayList<Event>> listener) {
		this.listener = listener;
	}
	
	@Override
	protected ArrayList<Event> doInBackground(Void... arg0) {

		ArrayList<Event> events = new ArrayList<Event>();
		
		try {
			
			Document doc = Jsoup.connect(HTML.website + HTML.invited_events).userAgent("Mozilla").get();
			Element json_element = doc.getElementById("result");
			
			JSONArray json_array = new JSONArray(json_element.text());
			int length = json_array.length();
			
			for(int i=0; i<length; i++){
				
				JSONObject o = json_array.getJSONObject(i);
				
				events.add(new Event(
					o.getString(Event.id_id),
					o.getString(Event.id_title),
					o.getString(Event.id_location),
					o.getString(Event.id_date_start),
					o.getString(Event.id_time_start),
					o.getString(Event.id_date_end),
					o.getString(Event.id_time_end)
				));
				
			}
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		
		return events;
					
	}

	@Override
	protected void onPostExecute(ArrayList<Event> result) {
		super.onPostExecute(result);
		listener.update_list(result);
	}
	
}
