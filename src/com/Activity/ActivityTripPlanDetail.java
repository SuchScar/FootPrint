package com.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActivityTripPlanDetail extends Activity{

	private ImageView topimage,back;
	//private LinearLayout showmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_tripplan_detail);
		
		initView();
		setlisten();
		
	}

	private void setlisten() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		topimage=(ImageView) findViewById(R.id.topimage);
		//showmap=(LinearLayout)findViewById(R.id.liner_map);
		back=(ImageView) findViewById(R.id.back_image);
		
		
		int screenWidth = getwidth();
		ViewGroup.LayoutParams lp = topimage.getLayoutParams();
		lp.width = screenWidth;
		lp.height = (int) (screenWidth/4*3);
		topimage.setLayoutParams(lp);
	}
	
	
	
	public int getwidth() {

		DisplayMetrics dm = new DisplayMetrics();
		ActivityTripPlanDetail.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		// 2.获取分辨率宽度
		int screenW = dm.widthPixels;
		return screenW;
	}
}
