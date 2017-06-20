package com.Activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.Adapter.ActivityChatRoomAdapter;
import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Tool.JSONtoDataSet;
import com.Tool.SPUtils;
import com.Tool.ToastUtil;

import Entity.SetTripWalkTrackListEntity;
import Entity.TripWalkTrackEntity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class ActivityChatroom extends Activity{

	private ImageView back;
	private EditText content;
	private Button send;
	
	private ListView listview;
	
	private ActivityChatRoomAdapter adapter;
	private SetTripWalkTrackListEntity list=new SetTripWalkTrackListEntity();
	
	private TripWalkTrackEntity entity;
	
	private int tripplanid,tripid,userid;
	
	
	//数据更新获取
	public static Handler mhander;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_chatroom);
		Intent intent =getIntent();
		tripplanid=intent.getIntExtra("tripplanid", -1);
		tripid=intent.getIntExtra("tripid",-1);
		userid=(Integer) SPUtils.get(ActivityChatroom.this, "userId", -1);
		
		JSONObject json=new JSONObject();
		try {
			json=new TripWalkTrackEntity().ToJSON(23, tripplanid, userid);
			MinaSocket.SendMessage(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		initView();
		setListen();
		updateUI();
		
		
		
	}
	
	
	
	
	private void data() {
		// TODO Auto-generated method stub
		TripWalkTrackEntity eentity=new TripWalkTrackEntity();
		eentity.setTripId(tripid);
		eentity.setTripPlanId(tripplanid);
		eentity.setUserid(3);
		eentity.setTripWalkTrackContent("你好");
		eentity.setUsername("某某");
		
		List<TripWalkTrackEntity>l=new ArrayList<TripWalkTrackEntity>();
		l.add(eentity);
		list=new SetTripWalkTrackListEntity(l);
	}




	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView) findViewById(R.id.back_image);
		content=(EditText) findViewById(R.id.edit_input);
		send=(Button) findViewById(R.id.send);
		listview=(ListView) findViewById(R.id.listview);
	}
	
	private void updateUI() {
		// TODO Auto-generated method stub
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==1){
					if((Boolean) msg.obj){
					List<TripWalkTrackEntity>l=new ArrayList<TripWalkTrackEntity>();
					if(list.getSize()!=0){
					l=list.getTripWalkTrackList();
					l.add(entity);
					list=new SetTripWalkTrackListEntity(l);
					adapter.notifyDataSetChanged();
					}else{
						l.add(entity);
						list=new SetTripWalkTrackListEntity(l);
						adapter=new ActivityChatRoomAdapter(ActivityChatroom.this, list);
						listview.setAdapter(adapter);
						
					}
					listview.setSelection(list.getSize());					
					content.setText("");
					}else{
						ToastUtil.show(ActivityChatroom.this,"发送失败");
					}

				}else if(msg.what==2){
					if(((JSONtoDataSet)msg.obj).isFlag()){
						TripWalkTrackEntity chatinfo=(TripWalkTrackEntity) ((JSONtoDataSet)msg.obj).getData();
						List<TripWalkTrackEntity>l=new ArrayList<TripWalkTrackEntity>();
						if(list.getSize()!=0){
							l=list.getTripWalkTrackList();
							l.add(chatinfo);
							list=new SetTripWalkTrackListEntity(l);
							adapter.notifyDataSetChanged();
							}else{
								l.add(chatinfo);
								list=new SetTripWalkTrackListEntity(l);
								adapter=new ActivityChatRoomAdapter(ActivityChatroom.this, list);
								listview.setAdapter(adapter);
							}
						
						listview.setSelection(list.getSize());	
					}
				}else if(msg.what==0){
					if(MainBindService.ChatInfoList.isFlag()){
						list=(SetTripWalkTrackListEntity) MainBindService.ChatInfoList.getData();
						
						adapter=new ActivityChatRoomAdapter(ActivityChatroom.this, list);
						listview.setAdapter(adapter);
						listview.setSelection(list.getSize());
					}else{
						ToastUtil.show(ActivityChatroom.this,"数据获取异常");
					}
				}
				
			}
		};
	}
	
	private void setListen() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!content.getText().toString().equals("")){
					entity=new TripWalkTrackEntity();
					entity.setTripId(tripid);
					entity.setTripPlanId(tripplanid);
					entity.setUserid(userid);
					entity.setTripWalkTrackContent(content.getText().toString());
					entity.setUsername((String)SPUtils.get(ActivityChatroom.this, "userNickname",""));
					JSONObject json=new JSONObject();
					try {
						json=new TripWalkTrackEntity().ToJSON(25, 1, tripplanid, entity);
						MinaSocket.SendMessage(json);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					List<TripWalkTrackEntity>l=new ArrayList<TripWalkTrackEntity>();
//					l=list.getTripWalkTrackList();
//					l.add(entity);
//					list=new SetTripWalkTrackListEntity(l);
//					adapter.notifyDataSetChanged();
//					listview.setSelection(list.getSize());
//					
//					content.setText("");
					
				}
			}
		});
		
		
	}
	
	
	
	
	
}
