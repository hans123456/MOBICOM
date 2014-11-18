package com.example.plan8_ui.Main_Page;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Model.DrawerItem;

public class DrawerAdapter extends ArrayAdapter<DrawerItem>{

	public DrawerAdapter(Context context, int resource, List<DrawerItem> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (null == convertView) {
			
		    LayoutInflater vi = LayoutInflater.from(getContext());
		    convertView = vi.inflate(R.layout.drawer_item, parent, false);
		
		}
		
		DrawerItem i = getItem(position);
		
		ImageView iv = (ImageView) convertView.findViewById(R.id.drawer_item_icon);
		TextView tv = (TextView) convertView.findViewById(R.id.drawer_item_text);
		
		iv.setImageResource(i.getPic());
		tv.setText(i.getText());
		
		return convertView;

	}
	
}
