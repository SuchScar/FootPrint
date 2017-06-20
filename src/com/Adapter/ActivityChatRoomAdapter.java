package com.Adapter;

import com.Activity.R;
import com.Adapter.CommentListAdapter.ViewHolder;
import com.Tool.SPUtils;

import Entity.SetCommentListEntity;
import Entity.SetTripWalkTrackListEntity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ActivityChatRoomAdapter extends BaseAdapter {

	private SetTripWalkTrackListEntity list;
	private Context context;
	
	
	
	 public ActivityChatRoomAdapter(Context c, SetTripWalkTrackListEntity l) {
	    	super();
	    	context = c;
	    	list = l;
	    }
	 
	//定义内部类作为占位符
	    class ViewHolder{
	    	ImageView myhead,otherhead;
	    	TextView myname;
	    	TextView mychat;
	    	TextView othername;
	    	TextView otherchat;
	    	
	    	LinearLayout liner_right;
	    	LinearLayout liner_left;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.activity_chat_item, null);
			viewholder = new ViewHolder();
			//获得视图中的对象控件
			viewholder.myname = (TextView) convertView.findViewById(R.id.myname);
			viewholder.mychat = (TextView) convertView.findViewById(R.id.right_msg);
			viewholder.myhead = (ImageView) convertView.findViewById(R.id.right_img);
			viewholder.otherhead = (ImageView) convertView.findViewById(R.id.left_img);
			viewholder.othername = (TextView) convertView.findViewById(R.id.othername);
			viewholder.otherchat = (TextView) convertView.findViewById(R.id.left_msg);
			
			viewholder.liner_right=(LinearLayout)convertView.findViewById(R.id.right_layout);
			viewholder.liner_left=(LinearLayout)convertView.findViewById(R.id.left_layout);
			convertView.setTag(viewholder);
		}
		else{
			viewholder = (ViewHolder) convertView.getTag();
		}
		if(list.getItem(position).getUserid()==(Integer)SPUtils.get(context, "userId",-1)){ 
			viewholder.liner_right.setVisibility(View.VISIBLE);
			viewholder.liner_left.setVisibility(View.GONE);
			viewholder.myname.setText(list.getItem(position).getUsername());
			viewholder.mychat.setText(list.getItem(position).getTripWalkTrackContent());
			
			
		}else{
			viewholder.liner_right.setVisibility(View.GONE);
			viewholder.liner_left.setVisibility(View.VISIBLE);
			viewholder.othername.setText(list.getItem(position).getUsername());
			viewholder.otherchat.setText(list.getItem(position).getTripWalkTrackContent());
		}
		
		
		
//		viewholder.comment_Info.setText(list.getItem(position).getCommentInfo());
//		viewholder.comment_time.setText(list.getItem(position).getCommentTime());
//		viewholder.user_name.setText(list.getItem(position).getUsername());
		
		return convertView;

	}
	
}
