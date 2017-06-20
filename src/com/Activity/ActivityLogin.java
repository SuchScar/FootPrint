package com.Activity;

import org.json.JSONObject;

import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Tool.SPUtils;
import com.Tool.ToastUtil;

import Entity.UserEntity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityLogin extends Activity{
	
	private ImageView back;
	private EditText account,password;
	private LinearLayout login;
	private TextView register;
	
    private String acc,pass;
	
	//数据更新获取
	public static Handler mhander;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_login);
		
		initView();
		setListen();
		
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==0){
					if(MainBindService.UserDetail.isFlag()){
						UserEntity userentity=(UserEntity) MainBindService.UserDetail.getData();
						userentity.setUserAccount(acc);
						userentity.setUserPassword(pass);
						new UserEntity().saveUserInfo(ActivityLogin.this,userentity);
						ToastUtil.show(ActivityLogin.this,"登陆成功");
						finish();
					}else {
						String string=(String) MainBindService.UserDetail.getData();
						ToastUtil.show(ActivityLogin.this,string);
						
						SPUtils.clear(ActivityLogin.this);
					}
				}
			}
		};
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView) findViewById(R.id.back_image);
		account=(EditText) findViewById(R.id.useraccount);
		password=(EditText) findViewById(R.id.userpassword);
		login=(LinearLayout) findViewById(R.id.login);
		register=(TextView) findViewById(R.id.register);
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
	login.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(account.length()!=0){
				if(password.length()!=0){
					JSONObject json=new JSONObject();
					acc=account.getText().toString();
					pass=password.getText().toString();
					json=new UserEntity().ToJSON(11,acc, pass);
					try {
						MinaSocket.SendMessage(json);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{ToastUtil.show(ActivityLogin.this, "密码不能为空");}

			}else{
				ToastUtil.show(ActivityLogin.this, "帐号不能为空！");
			}
		}
	});
	
	register.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(ActivityLogin.this,ActivityRegister.class);
			startActivity(intent);
		}
	});
	
	}
	
}
