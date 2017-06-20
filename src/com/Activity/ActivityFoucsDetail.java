package com.Activity;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import Entity.FocusEntity;
import Entity.SetCheckInfoListEntity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.Adapter.ActivityFootPrintListAdapter;
import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Lru.DiskLruCache;
import com.Lru.LruCacheUtils;
import com.Tool.MyListView;
import com.Tool.SPUtils;
import com.Tool.ToastUtil;

public class ActivityFoucsDetail extends Activity{

	private ImageView back,topimage;
	private TextView foucs_title,foucs_abstract,createusername,createtime,address;
	private MyListView listview;
	private ActivityFootPrintListAdapter adapter;
	private int foucsid,userid;
	private String imageurl;
	
	
	public static Handler mhander;
	private LruCacheUtils lruCache;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_foucsdetail);
		lruCache = LruCacheUtils.getInstance();
		Intent intent=getIntent();
		foucsid=intent.getIntExtra("foucsid", -1);
		ToastUtil.show(ActivityFoucsDetail.this,foucsid+"");
		userid=(Integer) SPUtils.get(ActivityFoucsDetail.this,"userId",0);
		
		JSONObject json=new JSONObject();
		try {
			json=new FocusEntity().ToJSON(35, foucsid,userid);
			MinaSocket.SendMessage(json);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initView();
		setListen();
		
		updateUI();
	}

	private void updateUI() {
		mhander = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if(msg.what==0){
					if(MainBindService.Focusdetails.isFlag()){
						FocusEntity foucs=(FocusEntity) MainBindService.Focusdetails.getData();
						SetCheckInfoListEntity scheckinfo=(SetCheckInfoListEntity) MainBindService.Focusdetails.getData1();
						imageurl=foucs.getFocusTopPicture();
						lruCache.loadBitmap(topimage,imageurl,11);
						foucs_title.setText(foucs.getFocusTitle());
						foucs_abstract.setText(foucs.getFoucsContent());
						createusername.setText(foucs.getFocusCreateUserName());
						createtime.setText(foucs.getFocusCreateTime());
						address.setText(foucs.getFocusAddress());
						
						adapter=new ActivityFootPrintListAdapter(ActivityFoucsDetail.this,scheckinfo);
						listview.setAdapter(adapter);
					}else{
						ToastUtil.show(ActivityFoucsDetail.this, "数据获取异常");
					}
				}else if(msg.what==1){
					Bitmap bitmap=(Bitmap) msg.obj;
					
					String url=imageurl;
					String key = lruCache.hashKeyForDisk(url);
	                DiskLruCache.Editor editor = null;
	                topimage.setImageBitmap(bitmap);
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

	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView) findViewById(R.id.back_image);
		foucs_title=(TextView) findViewById(R.id.foucstitle);
		foucs_abstract=(TextView) findViewById(R.id.text_foucsabstract);
		createusername=(TextView) findViewById(R.id.creatusername);
		createtime=(TextView) findViewById(R.id.createtime);
		listview=(MyListView) findViewById(R.id.listview);
		topimage=(ImageView)findViewById(R.id.topimage);
		address=(TextView) findViewById(R.id.address);
		listview.setFocusable(false);
		
		
		int screenWidth = getwidth();
		ViewGroup.LayoutParams lp = topimage.getLayoutParams();
		lp.width = screenWidth;
		lp.height = (int) (screenWidth/4*3);
		topimage.setLayoutParams(lp);
		
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
		
		
		
		
	}

	public int getwidth() {

		DisplayMetrics dm = new DisplayMetrics();
		ActivityFoucsDetail.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		// 2.获取分辨率宽度
		int screenW = dm.widthPixels;
		return screenW;
	}
}
