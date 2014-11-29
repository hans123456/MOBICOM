package com.example.plan8_ui.AsyncTasks;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.os.AsyncTask;
import android.util.Log;

import com.example.plan8_ui.Interfaces.AsyncResultTaskCompleteListener;
import com.example.plan8_ui.Model.Event;
import com.example.plan8_ui.Model.HTML;

public class FetchEventInfo extends AsyncTask<String, Void, Event>{

	private final String TAG = "Event Info";
	private AsyncResultTaskCompleteListener<Event> listener;

	public FetchEventInfo(AsyncResultTaskCompleteListener<Event> listener) {
		this.listener = listener;
	}
	
	@Override
	protected Event doInBackground(String... arg) {
		
		Event event = new Event();
		
		try {
			
			Document doc = Jsoup.connect(HTML.website + HTML.event_info)
								.data(HTML.post_event_id, arg[0])
								.userAgent(HTML.user_agent)
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
						event.put_information(key, value);					
					}
					
				}
			
			}
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		
		return event;
				
	}

	@Override
	protected void onPostExecute(Event result) {
		super.onPostExecute(result);
		listener.display_result(result);
	}
	
}
