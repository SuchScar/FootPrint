package com.Adapter;

import com.Activity.R;
import com.Adapter.ActivityTripPlanListAdapter.ViewHolder;

import Entity.SetTripParticipantListEntity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityTripParticipantListAdapter extends BaseAdapter{

	private SetTripParticipantListEntity list = new SetTripParticipantListEntity();
	private Context context;
	
	 public ActivityTripParticipantListAdapter(Context c, SetTripParticipantListEntity l) {
	    	super();
	    	context = c;
	    	list = l;
	    }
	 
	//定义内部类作为占位符
	    class ViewHolder{
	    	TextView participant_name;
//	    	TextView plan_mainplan;
//	    	TextView focus_title;
//	    	ImageView focus_picture;
	    }
	    
	    
	    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.getSize();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewholder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.activity_tripparticipant_list_item, null);
			viewholder = new ViewHolder();
			//获得视图中的对象控件
			viewholder.participant_name = (TextView) convertView.findViewById(R.id.username);

			convertView.setTag(viewholder);
		}
		else{
			viewholder = (ViewHolder) convertView.getTag();
		}
		viewholder.participant_name.setText(list.getItem(position).getUserNickname());

		
		return convertView;
	}

}
