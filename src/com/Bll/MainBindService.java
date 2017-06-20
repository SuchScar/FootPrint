package com.Bll;

import org.json.JSONObject;

import com.Tool.JSONtoDataSet;
import com.Tool.SPUtils;

import Entity.UserEntity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public  class MainBindService extends Service{

	public static JSONtoDataSet sFocusBriefList,sTripBriefList,TripDetails,CommentList,UserDetail,
	sNavigationTripBriefList,sParticipantsList,NavigationTripDetails,NavigationTripPlanDetails,sNavigationWalkTrack,sWalkTrack,
	ChatInfoList,Focusdetails,FoucsType,sNearbyFoucsList,sSearchList,sCityToFocusList,sMyCollect;
	
	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("info", "BindService--onCreate()");
		super.onCreate();
		Thread trd = new mythread();
		Thread pic = new picthread();
		trd.start();
		pic.start();
		
		

	}
	public class mythread extends Thread{
		public void run(){
			MinaSocket.connect(getBaseContext()); 
			
			
//			if(!SPUtils.get(getBaseContext(),"userAccount", "").equals("")){
//				if(!SPUtils.get(getBaseContext(),"userPassword", "").equals("")){
//					JSONObject json=new JSONObject();
//					json=new UserEntity().ToJSON(11,SPUtils.get(getBaseContext(),"userAccount", "").toString(), SPUtils.get(getBaseContext(),"userPassword", "").toString());
//					System.out.println("77777777777777777777");
//					try {
//						MinaSocket.SendMessage(json);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
			   }
	}
	public class picthread extends Thread{
		public void run(){
			PictureSocket.connect();
		}
	}
		
	
	public class MainBinder extends Binder{
		public MainBindService getService(){
			return MainBindService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("info", "BindService--onBind()");
		return new MainBinder();
	}
	
	@Override
	public void unbindService(ServiceConnection conn) {
		super.unbindService(conn);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("info", "BindService--unbindService()");
		super.onDestroy();
	}

}
