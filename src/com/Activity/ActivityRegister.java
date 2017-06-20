package com.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.Bll.MinaSocket;
import com.Tool.JSONtoDataSet;
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

public class ActivityRegister extends Activity{

	private ImageView back;
	private EditText account,password;
	private LinearLayout register;
	private TextView login;
	
	//数据更新获取
	public static Handler mhander;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_register);
		initView();
		setListen();
		updateUI();
	}

	private void updateUI() {
		// TODO Auto-generated method stub
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==0){
					JSONtoDataSet result=(JSONtoDataSet) msg.obj;
					if(result.isFlag()){
						ToastUtil.show(ActivityRegister.this,"注册成功");
						finish();
					}else{
						String string=(String) result.getData();
						if(string.isEmpty()){
							ToastUtil.show(ActivityRegister.this,"注册失败");
						}else{
							ToastUtil.show(ActivityRegister.this,string);
						}
					}
				}
			}
		};
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
				Intent intent=new Intent(ActivityRegister.this,ActivityLogin.class);
				startActivity(intent);
			}
		});
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(account.length()!=0){
					if(password.length()!=0){
						JSONObject json=new JSONObject();
						JSONObject user=new JSONObject();
						String acc=account.getText().toString();
						String pass=password.getText().toString();
						try {
							json.put("tag",17);
							user.put("userAccount", acc);
							user.put("userPassword", pass);
							user.put("userNickname", acc);
							json.put("user", user);
							
							MinaSocket.SendMessage(json);
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{ToastUtil.show(ActivityRegister.this, "密码不能为空");}

				}else{
					ToastUtil.show(ActivityRegister.this, "帐号不能为空！");
				}
			}
			
		});

	}

	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView) findViewById(R.id.back_image);
		account=(EditText) findViewById(R.id.useraccount);
		password=(EditText) findViewById(R.id.userpassword);
		register=(LinearLayout) findViewById(R.id.register);
		login=(TextView) findViewById(R.id.login);
	}
	
	
	
	
}

