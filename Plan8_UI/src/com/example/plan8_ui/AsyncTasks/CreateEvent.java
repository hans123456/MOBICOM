package com.example.plan8_ui.AsyncTasks;

import java.io.IOException;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.os.AsyncTask;
import android.util.Log;

import com.example.plan8_ui.Interfaces.AsyncGetResultTaskCompleteListener;
import com.example.plan8_ui.Model.CreateEventResult;
import com.example.plan8_ui.Model.HTML;

public class CreateEvent extends AsyncTask<String, Void, CreateEventResult>{

	private final String TAG = "Create Event";
	private AsyncGetResultTaskCompleteListener<CreateEventResult> listener;
	
	public CreateEvent(AsyncGetResultTaskCompleteListener<CreateEventResult> listener) {
		this.listener = listener;
	}
	
	@Override
	protected CreateEventResult doInBackground(String... arg) {

		Document doc;
		CreateEventResult result = new CreateEventResult();
		
		try {
			Log.wtf("Tag2", arg[9]);
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
						  .data(HTML.post_invited_friends, arg[9])
						  .userAgent(HTML.user_agent)
						  .cookie(HTML.session_id, HTML.SessionID)
						  .post();
			
			Element json_element = doc.getElementById(HTML.element_id);
			
			if(json_element != null){
				if(json_element.text().equals("success") == false){
					
					JSONObject json_object = new JSONObject(json_element.text());
					Iterator<String> i = json_object.keys();
					
					while(i.hasNext()){
						String key = i.next();
						Boolean value = json_object.getString(key).equals("valid") ? true : false;
						result.put_result(key, value);
					}
				
				}
			}
			
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}
		
		return result;
		
	}
	
	@Override
	protected void onPostExecute(CreateEventResult result) {
		super.onPostExecute(result);
		listener.display_result(result);
	}
	
}
