package com.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Tool.SPUtils;
import com.Tool.ToastUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActivitySetting extends Activity{

	private ImageView back;
	private LinearLayout exitLogin;
	
	//数据更新获取
	public static Handler mhander;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_setting);
		
		initView();
		setListen();
		if((Integer)SPUtils.get(ActivitySetting.this,"userId",-1)!=-1){
			exitLogin.setVisibility(View.VISIBLE);
		}else{
			exitLogin.setVisibility(View.GONE);
		}
		updateUI();
		
	}
	private void updateUI() {
		// TODO Auto-generated method stub
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==1){
					if((Boolean)msg.obj){
						SPUtils.clear(ActivitySetting.this);
						finish();
					}else{
						ToastUtil.show(ActivitySetting.this,"退出操作，发生异常");
					}
					
				}
			}
		};
	}
	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView) findViewById(R.id.back_image);
		exitLogin=(LinearLayout) findViewById(R.id.exitlogin);
		
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
		exitLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JSONObject json=new JSONObject();
				try {
					json.put("tag", 12);
					MinaSocket.SendMessage(json);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
