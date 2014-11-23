package com.example.plan8_ui.AsyncTasks;

import java.util.ArrayList;
import android.os.AsyncTask;
import com.example.plan8_ui.Interfaces.AsyncFetchListTaskCompleteListener;
import com.example.plan8_ui.Model.Friend;

public class FetchFriends extends AsyncTask<Void, Void, ArrayList<Friend>> {

	private AsyncFetchListTaskCompleteListener<ArrayList<Friend>> listener;
	
	public FetchFriends(AsyncFetchListTaskCompleteListener<ArrayList<Friend>> listener) {
		this.listener = listener;
	}
	
	@Override
	protected ArrayList<Friend> doInBackground(Void... arg0) {
		
		ArrayList<Friend> Friends = new ArrayList<Friend>();
		
		return Friends;
				
	}

	@Override
	protected void onPostExecute(ArrayList<Friend> result) {
		super.onPostExecute(result);
		listener.update_list(result);
	}
	
}
