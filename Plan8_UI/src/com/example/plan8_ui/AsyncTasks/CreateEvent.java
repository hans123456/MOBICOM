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
import com.example.plan8_ui.Model.HTML;

public class CreateEvent extends AsyncTask<String, Void, String>{

	private final String TAG = "Create Event";
	private AsyncResultTaskCompleteListener<String> listener;
	
	@Override
	protected String doInBackground(String... arg) {

		Document doc;
		String result = null;
		
		try {
			
			doc = Jsoup.connect(HTML.website + HTML.create_event)
						  .data(HTML.post_title, arg[0])
						  .data(HTML.post_description, arg[1])
						  .data(HTML.post_location, arg[2])
						  .data(HTML.post_date_start, arg[3])
						  .data(HTML.post_time_start, arg[4])
						  .data(HTML.post_date_end, arg[5])
						  .data(HTML.post_time_end, arg[6])
						  .data(HTML.post_latitude, arg[7])
						  .data(HTML.post_longitude, arg[8])
						  .userAgent(HTML.user_agent)
						  .post();
			
			Element json_element = doc.getElementById(HTML.element_id);
			
			JSONArray json_array = new JSONArray(json_element.text());
			JSONObject json_object = json_array.getJSONObject(0);
			Iterator<String> i = json_object.keys();
			
			while(i.hasNext()){
				String key = i.next();
				Boolean value = json_object.getString(key).equals("valid") ? true : false;
				json_object.put(key, value);
			}
			
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
