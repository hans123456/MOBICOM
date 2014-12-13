package com.mobicom.moihana.Main_Page;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobicom.moihana.R;
import com.mobicom.moihana.Model.DrawerItem;
import com.mobicom.moihana.Model.DrawerItemViewHolder;

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
		    
		    DrawerItemViewHolder drawer_item_view_holder = new DrawerItemViewHolder();

			drawer_item_view_holder.set_icon_image_view((ImageView) convertView.findViewById(R.id.drawer_item_icon_image_view));
			drawer_item_view_holder.set_label_text_view((TextView) convertView.findViewById(R.id.drawer_item_label_text_view));
			
		    convertView.setTag(drawer_item_view_holder);
		    
		}
		
		DrawerItem i = getItem(position);
		DrawerItemViewHolder drawer_item_view_holder = (DrawerItemViewHolder) convertView.getTag();
		
		drawer_item_view_holder.get_icon_image_view().setImageResource(i.getPic());
		drawer_item_view_holder.get_label_text_view().setText(i.getText());
		
		return convertView;

	}
	
}
