package com.Activity;

import org.json.JSONObject;

import com.Bll.MinaSocket;
import com.Tool.SPUtils;
import com.Tool.ToastUtil;

import Entity.CommentEntity;
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
import android.widget.RatingBar;
import android.widget.TextView;

public class ActivityWriteComment extends Activity{

	private TextView text_report;
	private ImageView back;
	private EditText commentcontent;
	private RatingBar tripcommentrank;
	
	private int tripid;
	
	//数据更新获取
	public static Handler mhander;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_write_comment);
		Intent intent=getIntent();
		tripid=intent.getIntExtra("tripid", -1);
		
		initView();
		setlisten();
		
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==1){
					boolean result=(Boolean) msg.obj;
					if(result){
						ToastUtil.show(ActivityWriteComment.this,"评论成功");
						finish();
					}else{
						ToastUtil.show(ActivityWriteComment.this,"评论失败，发生异常");
					}
				}
	
			}
		};
		
	}
	private void initView() {
		// TODO Auto-generated method stub
		text_report=(TextView) findViewById(R.id.report_text);
		back=(ImageView) findViewById(R.id.back_image);
		commentcontent=(EditText) findViewById(R.id.commentcontent);
		tripcommentrank=(RatingBar) findViewById(R.id.tripcommentrank);
		

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
		text_report.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(commentcontent.length()!=0){
					if(commentcontent.length()>14){
						CommentEntity comment=new CommentEntity();
						comment.setCommentInfo(commentcontent.getText().toString());
						comment.setCommentRank((int)tripcommentrank.getRating());
						comment.setTripId(tripid);
						JSONObject json=new JSONObject();
						json=new CommentEntity().ToJSON(8,(Integer)SPUtils.get(ActivityWriteComment.this,"userId", -1), comment);
						try {
							MinaSocket.SendMessage(json);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						
					}else{
						ToastUtil.show(ActivityWriteComment.this,"字数不得少于15字");
					}
				}else{
					ToastUtil.show(ActivityWriteComment.this, "内容不能为空");
					//ToastUtil.show(ActivityWriteComment.this, (int)tripcommentrank.getRating()+"");
					
				}
			}
		});
	}
	
	
	
	
	
	
}

