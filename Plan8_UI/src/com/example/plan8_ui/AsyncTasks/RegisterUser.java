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
import com.example.plan8_ui.Model.RegisterResult;

public class RegisterUser extends AsyncTask<String, Void, RegisterResult>{
	
	private final String TAG = "Register User";
	private AsyncResultTaskCompleteListener<RegisterResult> listener;
	
	public RegisterUser(AsyncResultTaskCompleteListener<RegisterResult> listener) {
		this.listener = listener;
	}
	
	@Override
	protected RegisterResult doInBackground(String... arg) {
		
		Document doc;
		RegisterResult result = null;
		
		try {
			
			doc = Jsoup.connect(HTML.website + HTML.register)
						  .data(HTML.post_username, arg[0])
						  .data(HTML.post_email_address, arg[1])
						  .data(HTML.post_first_name, arg[2])
						  .data(HTML.post_last_name, arg[3])
						  .data(HTML.post_password, arg[4])
						  .data(HTML.post_email_address, arg[5])
						  .userAgent(HTML.user_agent)
						  .post();
			
			Element json_element = doc.getElementById(HTML.element_id);
			
			JSONArray json_array = new JSONArray(json_element.text());
			JSONObject json_object = json_array.getJSONObject(0);
			Iterator<String> i = json_object.keys();
			result = new RegisterResult();
			
			while(i.hasNext()){
				String key = i.next();
				Boolean value = json_object.getString(key).equals("valid") ? true : false;
				result.put_result(key, value);
			}
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		
		return result;
		
	}
	
	@Override
	protected void onPostExecute(RegisterResult result) {
		super.onPostExecute(result);
		listener.display_result(result);
	}
	
}