package com.Activity;


import Entity.TripWalkTrackEntity;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityWalkTrackDetail extends Activity{

	private ImageView back;
	private TextView info;
	
	
	private TripWalkTrackEntity walktrack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_walktrackdetail);
		Intent intent=getIntent();
		walktrack=(TripWalkTrackEntity) intent.getSerializableExtra("detail");
		initView();
		setListen();
		info.setText(walktrack.getTripWalkTrackContent());
		
		
		
		
		
	}
	private void initView() {
		back=(ImageView) findViewById(R.id.back_image);
		info=(TextView) findViewById(R.id.tripwalk_info);
	}
	
	private void setListen() {
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	
}
