package com.Activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.Activity.R.string;
import com.Adapter.ActivityChooseFocusListAdapter;
import com.Adapter.FoucsBreifListAdapter;
import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Tool.SPUtils;
import com.Tool.ToastUtil;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import Entity.FocusBriefEntity;
import Entity.SetFocusBriefListEntity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class ActivityChooseFocus extends Activity{
	
	private ImageView back;
	private ListView listview;
	private View handview;
	
	//自定义变量
	private ActivityChooseFocusListAdapter adapter;
	private SetFocusBriefListEntity list;
		
	// 定位相关
	private LocationClient mLocationClient;
	private MyLocationListener mLocationListener;
	private double mLatitude;
	private double mLongtitude;
	private String mAddress;
	private String mCity;
		
	public static Handler mhander;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_choosefocus);
		
		initLocation();
		updateUI();
		
		//getData();
		initView();
		setlisten();
		handview=View.inflate(this,R.layout.activity_choosefocus_topitem,null);
//		adapter = new ActivityChooseFocusListAdapter(ActivityChooseFocus.this, list);
//		listview.addHeaderView(handview);
//		listview.setAdapter(adapter);
		
	}
	
	private void updateUI() {
		// TODO Auto-generated method stub
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==0){
					if(MainBindService.sNearbyFoucsList.isFlag()){
						list= (SetFocusBriefListEntity) MainBindService.sNearbyFoucsList.getData();
						
						adapter = new ActivityChooseFocusListAdapter(ActivityChooseFocus.this, list);
						listview.addHeaderView(handview);
						listview.setAdapter(adapter);
						
						
						
					}else{
						ToastUtil.show(ActivityChooseFocus.this,"数据获取异常");
					}
				}
			}
			
		};
	}

//	private void getData(){
//		for(int i=0;i<10;i++){
//			list.add(new FocusBriefEntity(i,"宁波工程学院西校区逸夫楼", "", "","宁波工程学院西校区101号"));
//			
//		}
//	}

	private void setlisten() {
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position==0){
					Intent intent=new Intent(ActivityChooseFocus.this,ActivityCreateFocus.class);
					intent.putExtra("Latitude",mLatitude);
					intent.putExtra("Longtitude", mLongtitude);
					intent.putExtra("City",mCity);
					intent.putExtra("Address",mAddress);
					startActivity(intent);
				}else{
					FocusBriefEntity focus=(FocusBriefEntity)parent.getAdapter().getItem(position);
					//ToastUtil.show(ChooseFocus.this,focus.focus_id);
					Intent intent1= new Intent(ActivityChooseFocus.this,ActivityCheckPrint.class);
					intent1.putExtra("foucsid", focus.getFocus_id());
					intent1.putExtra("foucsname", focus.getFocus_title());
					startActivity(intent1);
				}
			}
		});
		
		
	}
	private void initView() {
		back=(ImageView)findViewById(R.id.back_image);
		listview=(ListView)findViewById(R.id.listview);
		
	}
	
	private class MyLocationListener implements BDLocationListener{

		@Override
		public void onReceiveLocation(BDLocation location) {
			mLatitude=location.getLatitude();
			mLongtitude=location.getLongitude();
			mAddress=location.getAddrStr();
			mCity=location.getCity();
			
			JSONObject json=new JSONObject();
			
			try {
				json.put("tag",37);
				json.put("page", 1);
				json.put("selfLat", mLatitude);
				json.put("selfLong", mLongtitude);
				MinaSocket.SendMessage(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}
	
	private void initLocation() {

		mLocationClient=new LocationClient(this);
		mLocationListener=new MyLocationListener();
		mLocationClient.registerLocationListener(mLocationListener);
		LocationClientOption option=new LocationClientOption();
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setIsNeedAddress(true);
		option.setOpenGps(true);// 打开GPS
		//option.setScanSpan(1000);// 设置发起定位请求的间隔时间为1000ms
		option.setPriority(LocationClientOption.GpsFirst);// gps定位优先
		option.disableCache(false);// 禁止启用缓存定位
		mLocationClient.setLocOption(option);
		mLocationClient.start();// 开启定位SDK
		mLocationClient.requestLocation(); 
		
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(mLocationClient.isStarted()){
		mLocationClient.stop();}
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
}
