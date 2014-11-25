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
import com.example.plan8_ui.Model.User;

public class LoginUser extends AsyncTask<String, Void, User>{
	
	private final String TAG = "Login User";
	private AsyncResultTaskCompleteListener<User> listener;
	
	public LoginUser(AsyncResultTaskCompleteListener<User> listener) {
		this.listener = listener;
	}
	
	@Override
	protected User doInBackground(String... arg) {
		
		Document doc;
		User user = null;
		
		try {
			
			doc = Jsoup.connect(HTML.website + HTML.login)
						  .data(HTML.post_username, arg[0])
						  .data(HTML.post_password, arg[1])
						  .userAgent(HTML.user_agent)
						  .post();
			
			Element json_element = doc.getElementById(HTML.element_id);
			
			JSONArray json_array = new JSONArray(json_element.text());
			JSONObject json_object = json_array.getJSONObject(0);
			
			user = new User();

			Iterator<String> i = json_object.keys();

			while(i.hasNext()){
				String key = i.next();
				String value = json_object.getString(key);
				user.put_information(key, value);
			}

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		
		return user;
		
	}
	
	@Override
	protected void onPostExecute(User result) {
		super.onPostExecute(result);
		listener.display_result(result);
	}

}
