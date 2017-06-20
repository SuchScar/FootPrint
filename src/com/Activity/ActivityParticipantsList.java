package com.Activity;

import com.Adapter.ActivityTripParticipantListAdapter;
import com.Bll.MainBindService;
import com.Bll.MinaSocket;

import Entity.SetTripParticipantListEntity;
import Entity.TripParticipantEntity;
import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ActivityParticipantsList extends Activity{
	
	private ImageView back;
	private ListView listview;
	
	
	private String tripId;
	
	public static Handler mhander;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_participants_list);
		Intent intent=getIntent();
		//tripId=intent.getStringExtra("navigationtripid");
		
//		try {
//			MinaSocket.SendMessage(new TripParticipantEntity().ToJSON(21, tripId));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		initView();
		SetTripParticipantListEntity list=(SetTripParticipantListEntity) intent.getSerializableExtra("navigationtripid");
		System.out.println("----------------"+intent.getSerializableExtra("navigationtripid"));
		ActivityTripParticipantListAdapter adapter=new ActivityTripParticipantListAdapter(ActivityParticipantsList.this, list);
		
		listview.setAdapter(adapter);
		setListen();
		//UpdateUI();
		
		
	}

	private void UpdateUI() {
		// TODO Auto-generated method stub
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==0){
					if(MainBindService.sParticipantsList.isFlag()){
						SetTripParticipantListEntity list=(SetTripParticipantListEntity) MainBindService.sParticipantsList.getData();
						ActivityTripParticipantListAdapter adapter=new ActivityTripParticipantListAdapter(ActivityParticipantsList.this, list);
						
						listview.setAdapter(adapter);
						
					}
				}

			}
		};
	}

	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView) findViewById(R.id.back_image);
		listview=(ListView) findViewById(R.id.listview);
		
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
	}

	
}
