package com.example.plan8_ui.AsyncTasks;

import java.io.IOException;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.os.AsyncTask;
import android.util.Log;

import com.example.plan8_ui.Interfaces.AsyncGetResultTaskCompleteListener;
import com.example.plan8_ui.Model.HTML;
import com.example.plan8_ui.Model.User;

public class LoginUser extends AsyncTask<String, Void, User>{
	
	private final String TAG = "Login User";
	private AsyncGetResultTaskCompleteListener<User> listener;
	
	public LoginUser(AsyncGetResultTaskCompleteListener<User> listener) {
		this.listener = listener;
	}
	
	@Override
	protected User doInBackground(String... arg) {
		
		Document doc;
		Connection.Response res;
		User user = new User();
		
		try {
			
			res = Jsoup.connect(HTML.website + HTML.login)
									  .data(HTML.post_username, arg[0])
									  .data(HTML.post_password, arg[1])
									  .userAgent(HTML.user_agent)
									  .method(Method.POST)
									  .execute();
			
			doc = res.parse();
			HTML.SessionID = res.cookie(HTML.session_id);
			
			Element json_element = doc.getElementById(HTML.element_id);
			
			if(json_element.text().equals("empty") == false){
			
				JSONObject json_object = new JSONObject(json_element.text());
				Iterator<String> i = json_object.keys();
	
				while(i.hasNext()){
					String key = i.next();
					String value = json_object.getString(key);
					user.put_information(key, value);
				}
			
			}
			
		} catch (IOException e) {
			Log.e(TAG, e.getMessage() == null ? "" : e.getMessage());
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage() == null ? "" : e.getMessage());
		}
		
		return user;
		
	}
	
	@Override
	protected void onPostExecute(User result) {
		super.onPostExecute(result);
		listener.display_result(result);
	}

}
