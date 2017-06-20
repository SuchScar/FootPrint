package com.Activity;

import org.json.JSONObject;

import com.Activity.R.id;
import com.Bll.MinaSocket;
import com.Tool.ToastUtil;

import Entity.TripBriefEntity;
import Entity.TripPlanEntity;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ActivityEditNewTripPlan extends Activity{

	private ImageView back;
	private EditText newtripplan,newtripplanbrief;
	private TextView submit;
	private RelativeLayout rel;
	
	
	private int triopplanid;
	
	//数据更新获取
	public static Handler mhander;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_edit_tripplan);
		Intent intent =getIntent();
		triopplanid=intent.getIntExtra("tripplanid", -1);
		
		
		
		initView();
		setlisten();
		
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==1){
					if((Boolean) msg.obj){
						ToastUtil.show(ActivityEditNewTripPlan.this, "修改成功");
						finish();
					}else{
						ToastUtil.show(ActivityEditNewTripPlan.this,"修改失败，发生异常");
					}
				}
				
			}
		};

	}

	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView) findViewById(R.id.back_image);
		newtripplan=(EditText) findViewById(R.id.newtriipplan);
		submit=(TextView) findViewById(R.id.submit);
		rel=(RelativeLayout) findViewById(R.id.rell);
		newtripplanbrief=(EditText) findViewById(R.id.newtriipplanbrief);
		
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
		rel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newtripplan.setFocusable(true);
				newtripplan.requestFocus();
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			}
		});
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(newtripplanbrief.length()!=0){
					if(newtripplan.length()!=0){
					TripPlanEntity tripplanentity=new TripPlanEntity();
					tripplanentity.setTripPlanBrief(newtripplanbrief.getText().toString());
					tripplanentity.setTripMainPlan(newtripplan.getText().toString());
					tripplanentity.setTripPlanId(triopplanid);
					JSONObject json=new TripPlanEntity().ToJSON(9, tripplanentity);
					try {
						MinaSocket.SendMessage(json);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}else {
						ToastUtil.show(ActivityEditNewTripPlan.this,"详细安排不能为空");
					}
				}else{
					ToastUtil.show(ActivityEditNewTripPlan.this,"概要不能为空");
				}
			}
		});
	}
}
