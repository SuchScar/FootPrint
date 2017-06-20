package com.Adapter;

import java.util.ArrayList;
import java.util.List;

import com.Activity.R;

import Entity.FocusBriefEntity;
import Entity.SetFocusBriefListEntity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ActivityChooseFocusListAdapter extends BaseAdapter{

	private SetFocusBriefListEntity list;
	private Context context;
	
	 public ActivityChooseFocusListAdapter(Context c, SetFocusBriefListEntity l) {
	    	super();
	    	context = c;
	    	list = l;
	    }
	 
	//定义内部类作为占位符
	    class ViewHolder{
	    	TextView focus_title;
	    	TextView focus_address;
	    }
	@Override
	public int getCount() {
		return list.getSize();
	}

	@Override
	public Object getItem(int position) {
		return list.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.activity_choosefocus_list_item, null);
			viewholder = new ViewHolder();
			//获得视图中的对象控件
			viewholder.focus_title = (TextView) convertView.findViewById(R.id.focus_title);
			viewholder.focus_address = (TextView) convertView.findViewById(R.id.focus_address);
			convertView.setTag(viewholder);
		}
		else{
			viewholder = (ViewHolder) convertView.getTag();
		}
		viewholder.focus_title.setText(list.getItem(position).getFocus_title());
		viewholder.focus_address.setText(list.getItem(position).getFocus_address());
		
		return convertView;
	}

}
