package com.Fragment;

import java.io.IOException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Activity.ActivityChangeUserInfo;
import com.Activity.ActivityLogin;
import com.Activity.ActivityMyCollection;
import com.Activity.ActivityMyFootprint;
import com.Activity.ActivityMyFoucs;
import com.Activity.ActivityMyTrip;
import com.Activity.ActivityNavigationTripList;
import com.Activity.ActivitySetting;
import com.Activity.R;
import com.Lru.DiskLruCache;
import com.Lru.LruCacheUtils;
import com.Tool.SPUtils;
import com.Tool.ToastUtil;

public class MyinfoPageFragment extends Fragment{

	private RelativeLayout Rel_Navigation,Rel_Personalpage,Rel_MyCollcetion,Rel_MyTrip,Rel_MyFoucs,Rel_MyFoootPrint;
	private LinearLayout Lin_changeinfo;
	private ImageView headimage,setimage;
	private TextView username;
	private int userid;
	
	private String imageurl;

	//数据更新获取
	public static Handler mhander;
	private LruCacheUtils lruCache;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_myinfo, null);
		findview(view);
		return view;
	}

	private void findview(View view) {
		// TODO Auto-generated method stub

		Rel_Navigation=(RelativeLayout)view.findViewById(R.id.Rel_navigationtrip);
		headimage=(ImageView) view.findViewById(R.id.headimage);
		Rel_Personalpage=(RelativeLayout) view.findViewById(R.id.personalpage);
		username=(TextView) view.findViewById(R.id.username);
		setimage=(ImageView) view.findViewById(R.id.set_image);
		Rel_MyCollcetion=(RelativeLayout) view.findViewById(R.id.Rel_collection);
		Rel_MyFoucs=(RelativeLayout) view.findViewById(R.id.Rel_focus);
		Rel_MyTrip=(RelativeLayout) view.findViewById(R.id.Rel_trip);
		Rel_MyFoootPrint=(RelativeLayout) view.findViewById(R.id.Rel_footprint);
		Lin_changeinfo=(LinearLayout) view.findViewById(R.id.changeinfo);
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		lruCache = LruCacheUtils.getInstance();
		setlisten();
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==1){
					Bitmap bitmap=(Bitmap) msg.obj;
					
					String url=imageurl;
					String key = lruCache.hashKeyForDisk(url);
	                DiskLruCache.Editor editor = null;
	                headimage.setImageBitmap(bitmap);
					lruCache.addBitmapToCache(url,bitmap);
					try {
						editor = lruCache.diskLruCache.edit(key);
						//位图压缩后输出（参数：压缩格式，质量(100表示不压缩，30表示压缩70%)，输出流）
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, editor.newOutputStream(0));
                        editor.commit();//提交
					} catch (IOException e) {
						 try {
		                        editor.abort();//放弃写入
		                    } catch (IOException e1) {
		                        e1.printStackTrace();
		                    }
					}	
				}
				
			}
		};
	}

	private void setlisten() {
		// TODO Auto-generated method stub
		Rel_Navigation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),ActivityNavigationTripList.class);
				startActivity(intent);
			}
		});
		Rel_Personalpage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if((Integer)SPUtils.get(getActivity(), "userId",-1)==-1){
				Intent intent=new Intent(getActivity(),ActivityLogin.class);
				startActivity(intent);
				}else{
					ToastUtil.show(getActivity(), "个人主页待开发");
				}
				
			}
		});
		Lin_changeinfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if((Integer)SPUtils.get(getActivity(), "userId",-1)==-1){
					Intent intent=new Intent(getActivity(),ActivityLogin.class);
					startActivity(intent);
					}else{
						//ToastUtil.show(getActivity(), "change");
						Intent intent=new Intent(getActivity(),ActivityChangeUserInfo.class);
						startActivity(intent);
					}
			}
		});
		setimage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),ActivitySetting.class);
				startActivity(intent);
			}
		});
		Rel_MyCollcetion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(userid!=-1){
					Intent intent=new Intent(getActivity(),ActivityMyCollection.class);
					startActivity(intent);
				}else{
					Intent intent=new Intent(getActivity(),ActivityLogin.class);
					startActivity(intent);
				}
				
			}
		});
		Rel_MyFoucs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(userid!=-1){
					Intent intent=new Intent(getActivity(),ActivityMyFoucs.class);
					startActivity(intent);
				}else{
					Intent intent=new Intent(getActivity(),ActivityLogin.class);
					startActivity(intent);
				}
				
			}
		});
		Rel_MyTrip.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                 if(userid!=-1){
                	 Intent intent=new Intent(getActivity(),ActivityMyTrip.class);
     				startActivity(intent);
				}else{
					Intent intent=new Intent(getActivity(),ActivityLogin.class);
					startActivity(intent);
				}
				
			}
		});
		Rel_MyFoootPrint.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                if(userid!=-1){
                	Intent intent=new Intent(getActivity(),ActivityMyFootprint.class);
    				startActivity(intent);
				}else{
					Intent intent=new Intent(getActivity(),ActivityLogin.class);
					startActivity(intent);
				}
				
			}
		});
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if((Integer)SPUtils.get(getActivity(),"userId",-1)!=-1){
			username.setText((String)SPUtils.get(getActivity(), "userNickname", ""));	
		}else{
			
			username.setText("未登录");
		}
		userid=(Integer) SPUtils.get(getActivity(), "userId",-1);
		imageurl=(String) SPUtils.get(getActivity(), "userPicture", "");
		if(!imageurl.equals("")){
			lruCache.loadBitmap(headimage,imageurl,9);
		}else{
			headimage.setImageResource(R.drawable.head);
		}
		
	}
}
