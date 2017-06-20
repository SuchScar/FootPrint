package com.Activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.Bll.MinaSocket;
import com.Tool.IconManger;
import com.Tool.MyOrientationListener;
import com.Tool.ToastUtil;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

import Entity.TripNavigationTrackEntity;
import Entity.TripWalkTrackEntity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ActivityShowTripPlanMap extends Activity{

	private RelativeLayout focus_info,tripwalk_info;
	private LinearLayout myplace,alltrack;
	private ImageView back;
	private TextView mainplan;

	private MapView mMapView;
	private BaiduMap mBaiduMap;
	private List<TripNavigationTrackEntity> navigationtracklist=new ArrayList<TripNavigationTrackEntity>();
	private List<TripWalkTrackEntity> walktracklist=new ArrayList<TripWalkTrackEntity>();
	private int tripid,tripplanid;
	
	// 定位相关
	private LocationClient mLocationClient;
	private MyLocationListener mLocationListener;
	private double mLatitude;
	private double mLongtitude;
			
    //自定义图标
	//private BitmapDescriptor mlogo;
	private com.Tool.MyOrientationListener myOrientationListener;
	private float mcurrentX;
	private LocationMode mlocationmode;
			
	//覆盖物相关
	private BitmapDescriptor mmarker;
	private List<Walklist> walklist=new ArrayList<Walklist>();
	private List<IconManger>iconlist=new ArrayList<IconManger>();
	private List<Marker> marklist = new ArrayList<Marker>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_showtripplan_map);
		iconlist.add(new IconManger(true,R.drawable.icon_redmarker));
		iconlist.add(new IconManger(true,R.drawable.icon_bluemarker));
		iconlist.add(new IconManger(true,R.drawable.icon_greenmarker));
		iconlist.add(new IconManger(true,R.drawable.icon_purplemarker));
		iconlist.add(new IconManger(true,R.drawable.icon_yellowmarker));
		iconlist.add(new IconManger(true,R.drawable.icon_brownmarker));
		iconlist.add(new IconManger(true,R.drawable.icon_cyanmarker));
		//iconlist.add(new IconManger(true,R.drawable.icon_graymarker));
		iconlist.add(new IconManger(true,R.drawable.icon_orangemarker));
		iconlist.add(new IconManger(true,R.drawable.icon_pinkmarker));
		
		Intent intent=getIntent();
		tripid=intent.getIntExtra("tripid", -1);
		tripplanid=intent.getIntExtra("tripplanid", -1);
		try {
			MinaSocket.SendMessage(new TripNavigationTrackEntity().ToJSON(16,
					tripplanid));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getDate();
		getDate1();
		
		initView();
		setlisten();
		initLocation();
		
		initNavigationMarker();
		itWalkMarker();
		showall();
	}
	
	
	private void showall() {
		// TODO Auto-generated method stub
		LatLngBounds.Builder builder = new LatLngBounds.Builder();
		for (Marker overlay : marklist) {
			builder.include((overlay).getPosition());
		}
		mBaiduMap.setMapStatus(MapStatusUpdateFactory
				.newLatLngBounds(builder.build()));
	}


	private void itWalkMarker() {
		// TODO Auto-generated method stub
		for(TripWalkTrackEntity info:walktracklist){
			
			if(walklist.size()==0){
				
				for(IconManger icon:iconlist){
					if(icon.isFlag()){
						Walkmarker marker=new Walkmarker(icon.getImageid());
						icon.setFlag(false);
						List<TripWalkTrackEntity>list=new ArrayList<TripWalkTrackEntity>();
						list.add(info);
						marker.initWalkMarker(info, ActivityShowTripPlanMap.this);
						walklist.add(new Walklist(info.getTripParticipantId(), marker, list));
						break;
					}
				}
				
			}else{
				int z=0;
				
			for(Walklist item:walklist){
				z++;
				if(item.getTripParticipantId()==info.getTripParticipantId()){
					item.list.add(info);
					
					if(item.marker.getImageid()!=-1){
					item.getMarker().initWalkMarker(info, ActivityShowTripPlanMap.this);
					}
					break;
				}else if(walklist.size()==z){
					int x=0;
					for(IconManger icon:iconlist){
						x++;
					if(icon.isFlag()){
					Walkmarker marker=new Walkmarker(icon.getImageid()); 
					icon.setFlag(false);
					List<TripWalkTrackEntity>list=new ArrayList<TripWalkTrackEntity>();
					list.add(info);
					marker.initWalkMarker(info, ActivityShowTripPlanMap.this);
					walklist.add(new Walklist(info.getTripParticipantId(), marker, list));
					break;
					}else if(iconlist.size()==x){
						ToastUtil.show(ActivityShowTripPlanMap.this,"显示数目已最大");
						List<TripWalkTrackEntity>list=new ArrayList<TripWalkTrackEntity>();
						list.add(info);
						walklist.add(new Walklist(info.getTripParticipantId(), new Walkmarker(), list));
					}
					}
					
				}	
			}
			}	
		}
	}


	private void setlisten() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				//showall();
			}
		});
		mainplan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ActivityShowTripPlanMap.this,ActivityTripPlanDetail.class);
				intent.putExtra("tripplanid", tripplanid);
				startActivity(intent);
			}
		});
		alltrack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showall();
			}
		});
		
		myplace.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LatLng latlng=new LatLng(mLatitude,mLongtitude);
				MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latlng);
				MapStatusUpdate msu1=MapStatusUpdateFactory.zoomTo(15.0f);
				mBaiduMap.setMapStatus(msu1);
				mBaiduMap.animateMapStatus(msu);				
			}
		});
//		navigationtrack.setOnClickListener(new OnClickListener() {			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				initNavigationMarker();			
//			}
//		});
//		walktrack.setOnClickListener(new OnClickListener() {		
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub	
//				
//				itWalkMarker();
//			}
//		});
//		hidetrack.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				mBaiduMap.clear();
//			}
//		});
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker maiker) {
				Bundle extrainfo=maiker.getExtraInfo();
				if(extrainfo.getBoolean("flag")){					
					TripNavigationTrackEntity info=(TripNavigationTrackEntity) extrainfo.getSerializable("info");
					tripwalk_info.setVisibility(View.GONE);
					focus_info.setVisibility(View.VISIBLE);
					ToastUtil.show(ActivityShowTripPlanMap.this,info.getFocusId()+"");
				}
				else{
					TripWalkTrackEntity info=(TripWalkTrackEntity) extrainfo.getSerializable("info");
					TextView title=(TextView) tripwalk_info.findViewById(R.id.tripwalk_info_title);
					TextView abstracts=(TextView)tripwalk_info.findViewById(R.id.tripwalk_info_abstract);
					TextView time=(TextView) tripwalk_info.findViewById(R.id.tripwalk_info_time);
					title.setText(info.getTripWalkTrackName());
					abstracts.setText(info.getTripWalkTrackContent());
					time.setText(info.getTripWalkTrackTime());
					focus_info.setVisibility(View.GONE);
					tripwalk_info.setVisibility(View.VISIBLE);
				}
				return true;
			}
		});
		
		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onMapClick(LatLng arg0) {
				// TODO Auto-generated method stub
				focus_info.setVisibility(View.GONE);
				tripwalk_info.setVisibility(View.GONE);
			}
		});
	}


	private void initView() {
		// TODO Auto-generated method stub
		focus_info=(RelativeLayout)findViewById(R.id.focus_info);
		tripwalk_info=(RelativeLayout)findViewById(R.id.tripwalk_info);
		myplace=(LinearLayout)findViewById(R.id.myplace);
//		walktrack=(LinearLayout)findViewById(R.id.walktrack);
//		navigationtrack=(LinearLayout)findViewById(R.id.navigationtrack);
//		hidetrack=(LinearLayout)findViewById(R.id.hidetrack);
		back=(ImageView) findViewById(R.id.back_image);
		mainplan=(TextView) findViewById(R.id.mainplan);
		alltrack=(LinearLayout)findViewById(R.id.alltrack);
		

		mMapView=(MapView)findViewById(R.id.mapView);
		mBaiduMap=mMapView.getMap();
		MapStatusUpdate msu=MapStatusUpdateFactory.zoomTo(15.0f);
		mBaiduMap.setMapStatus(msu);
	}
	
	private void initLocation() {

		mlocationmode=LocationMode.NORMAL;
		mLocationClient=new LocationClient(this);
		mLocationListener=new MyLocationListener();
		mLocationClient.registerLocationListener(mLocationListener);
		LocationClientOption option=new LocationClientOption();
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setIsNeedAddress(true);
		option.setOpenGps(true);// 打开GPS
		option.setScanSpan(1000);// 设置发起定位请求的间隔时间为1000ms
		option.setPriority(LocationClientOption.GpsFirst);// gps定位优先
		option.disableCache(false);// 禁止启用缓存定位
		mLocationClient.setLocOption(option);
//		//初始化图标
//		LayoutInflater inflate=getLayoutInflater();
//		View a=inflate.inflate(R.layout.location_logo, null);	
//		mlogo=BitmapDescriptorFactory.fromView(a);
		
		myOrientationListener=new MyOrientationListener(this);
		myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
			
			@Override
			public void onOrientationChanged(float x) {
				// TODO Auto-generated method stub
				mcurrentX=x;
			}
		});
		
	}
	
	private void getDate(){
		navigationtracklist.add(new TripNavigationTrackEntity(1,34.242652, 108.971171,1));
		navigationtracklist.add(new TripNavigationTrackEntity(2,34.242952, 108.972171,2));
		navigationtracklist.add(new TripNavigationTrackEntity(3,34.242852, 108.973171,3));
		navigationtracklist.add(new TripNavigationTrackEntity(4,34.242152, 108.971971,4));

	}
	private void getDate1(){
		walktracklist.add(new TripWalkTrackEntity(1,"路上",34.243652, 108.971171,"","现在还在路上你要我说什么","2016/02/15"));
		walktracklist.add(new TripWalkTrackEntity(2,"山下",34.243952, 108.972171,"","到山下了，开始爬山","2016/02/15"));
		walktracklist.add(new TripWalkTrackEntity(3,"山腰上",34.243852, 108.973171,"","好累啊，在半山腰要就累了","2016/02/15"));
		walktracklist.add(new TripWalkTrackEntity(4,"山顶",34.243152, 108.971971,"","终于到达山顶，风景好美","2016/02/15"));

	}
	
	
	
	private void initNavigationMarker() {
		LayoutInflater flater = LayoutInflater.from(ActivityShowTripPlanMap.this);
		View view = flater.inflate(R.layout.marker_nloge, null);
		TextView num = (TextView) view.findViewById(R.id.number);
		int i=0;
		//mmarker=BitmapDescriptorFactory.fromResource(R.drawable.rednn);
		//mBaiduMap.clear();
		LatLng latlng=null;
		Marker marker=null;
		OverlayOptions opinions;
		for(TripNavigationTrackEntity info:navigationtracklist){	
			i++;
			num.setText(i+ "");
			mmarker = BitmapDescriptorFactory.fromView(view);
			//经纬度
			latlng=new LatLng(info.getTripNavigationTracklat(),info.getTripNavigationTracklon());
			//图标
			opinions=new MarkerOptions().position(latlng).icon(mmarker).zIndex(5);
			marker=(Marker)mBaiduMap.addOverlay(opinions); 
			
			Bundle arg0=new Bundle();
			arg0.putBoolean("flag", true);
			arg0.putSerializable("info", info);
			marker.setExtraInfo(arg0);
			marklist.add(marker);
		}
		//MapStatusUpdate mus=MapStatusUpdateFactory.newLatLng(latlng);
//		MapStatusUpdate msu1=MapStatusUpdateFactory.zoomTo(15.0f);
//		mBaiduMap.setMapStatus(msu1);
		//mBaiduMap.setMapStatus(mus);
	}
	
	class Walkmarker{
		private int i=0;
		private int imageid=-1;
		
		public Walkmarker(){}
		public Walkmarker(int imageid){
			this.imageid=imageid;
		}
		
		public int getI() {
			return i;
		}
		public void setI(int i) {
			this.i = i;
		}
		public int getImageid() {
			return imageid;
		}
		public void setImageid(int imageid) {
			this.imageid = imageid;
		}
		private void initWalkMarker(TripWalkTrackEntity info,Context context) {
			//mmarker=BitmapDescriptorFactory.fromResource(R.drawable.icon_redmarker);
			LayoutInflater flater=LayoutInflater.from(context);
			View view=flater.inflate(R.layout.marker_logo,null);
			TextView num=(TextView) view.findViewById(R.id.number);
			ImageView image=(ImageView) view.findViewById(R.id.image);
			//mBaiduMap.clear();
			LatLng latlng=null;
			Marker marker=null;
			OverlayOptions opinions;
			
			//for(TripWalkTrackEntity info:walktracklist.getTripWalkTrackList()){
				i++;
				num.setText(i+"");
				image.setImageResource(imageid);
				mmarker=BitmapDescriptorFactory.fromView(view);
				//经纬度
				latlng=new LatLng(info.getTripWalkTrackLat(),info.getTripWalkTrackLon());
				//图标
				opinions=new MarkerOptions().position(latlng).icon(mmarker).zIndex(5);
				marker=(Marker)mBaiduMap.addOverlay(opinions); 
				
				Bundle arg0=new Bundle();
				arg0.putBoolean("flag", false);
				arg0.putSerializable("info", info);
				marker.setExtraInfo(arg0);	
				marklist.add(marker);
			//}
			//MapStatusUpdate mus=MapStatusUpdateFactory.newLatLng(latlng);
			//mBaiduMap.setMapStatus(mus);
		}
		
	}
	
	public class Walklist {

		private int tripParticipantId;
		private Walkmarker marker;
		private List<TripWalkTrackEntity> list=new ArrayList<TripWalkTrackEntity>();
		
		
		public Walklist(int tripParticipantId,Walkmarker marker,List<TripWalkTrackEntity> list){
			this.tripParticipantId=tripParticipantId;
			this.marker=marker;
			this.list=list;
		}
		public int getTripParticipantId() {
			return tripParticipantId;
		}
		public void setTripParticipantId(int userid) {
			this.tripParticipantId = userid;
		}
		public Walkmarker getMarker() {
			return marker;
		}
		public void setMarker(Walkmarker marker) {
			this.marker = marker;
		}
		public List<TripWalkTrackEntity> getList() {
			return list;
		}
		public void setList(List<TripWalkTrackEntity> list) {
			this.list = list;
		}
		
	}
	
	private class MyLocationListener implements BDLocationListener{

		@Override
		public void onReceiveLocation(BDLocation location) {

			MyLocationData data=new MyLocationData.Builder()
			.direction(mcurrentX)
			.accuracy(location.getRadius())
			.latitude(location.getLatitude())
			.longitude(location.getLongitude())
			.build();
			
			mBaiduMap.setMyLocationData(data);
			//设置自定义图标
			MyLocationConfiguration config=new MyLocationConfiguration(mlocationmode, true, null);
			mBaiduMap.setMyLocationConfigeration(config);
			
			
			mLatitude=location.getLatitude();
			mLongtitude=location.getLongitude();
			
		}
	
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mMapView.onDestroy();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		mMapView.onResume();
		super.onResume();
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mMapView.onPause();
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//开启定位
		mBaiduMap.setMyLocationEnabled(true);
		if(!mLocationClient.isStarted()){
		mLocationClient.start();
		}
		//开启方向
		myOrientationListener.start();
		
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//关闭定位
		mBaiduMap.setMyLocationEnabled(false);
		mLocationClient.stop();
		//停止方向传感器
		myOrientationListener.stop();
		
		
	}
}
