package com.Activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.Adapter.ActivityNavigationTripFocusListAdapter;
import com.Adapter.ActivityTripParticipantListAdapter;
import com.Adapter.FoucsBreifListAdapter;
import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Bll.PictureSocket;
import com.Lru.DiskLruCache;
import com.Lru.LruCacheUtils;
import com.Tool.CallBack;
import com.Tool.CallList;
import com.Tool.MyListView;
import com.Tool.SPUtils;
import com.Tool.ToastUtil;

import Entity.FocusBriefEntity;
import Entity.SetTripParticipantListEntity;
import Entity.SetTripPlanListEntity;
import Entity.TripEntitry;
import Entity.TripParticipantEntity;
import Entity.TripPlanEntity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ActivityNavigationTripDetails extends Activity{
	
	private ImageView back,participant,topimage;
	private MyListView listview;
	private TextView triptitle,createusername,tripabstract,createtime,end;
	private RelativeLayout start;
	
	private int tripId;
	private int Participantsid;
	private TripEntitry tripentity;
	//private boolean startflag=false;
	
	private SetTripPlanListEntity sTripPlanList=new SetTripPlanListEntity();
	private ActivityNavigationTripFocusListAdapter adapter;
	private SetTripParticipantListEntity pentity;
	
	
	//数据更新获取
	public static Handler mhander;
	private LruCacheUtils lruCache;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_navigation_details);
		lruCache = LruCacheUtils.getInstance();
		Intent intent=getIntent();
		tripId=intent.getIntExtra("navigationtripid", -1);
		ToastUtil.show(ActivityNavigationTripDetails.this,tripId+"");
		
		initView();
		setlisten();
		
		try {
			MinaSocket.SendMessage(new TripParticipantEntity().ToJSON(21, tripId));
			MinaSocket.SendMessage(new TripEntitry().ToJSON(14, tripId,0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				if(msg.what==0){
					if(MainBindService.NavigationTripDetails.isFlag()){
						tripentity=new TripEntitry();
						tripentity=(TripEntitry) MainBindService.NavigationTripDetails.getData();
						
						sTripPlanList=(SetTripPlanListEntity) MainBindService.NavigationTripDetails.getData1();
						adapter=new ActivityNavigationTripFocusListAdapter(ActivityNavigationTripDetails.this,sTripPlanList);
						listview.setAdapter(adapter);
						
						lruCache.loadBitmap(topimage,tripentity.getTripImage(),7);
						triptitle.setText(tripentity.getTripTitle());
						createusername.setText(tripentity.getTripCreateUser());
						tripabstract.setText(tripentity.getTripSummary());
						createtime.setText(tripentity.getTripCreateTime());
						if(tripentity.getTripState()==6){
							start.setVisibility(View.GONE);
						}else if(tripentity.getTripState()==5){
							end.setVisibility(View.GONE);
						}
						
						listview.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								// TODO Auto-generated method stub
								//if(tripentity.getTripState()==6){
									Intent intent=new Intent(ActivityNavigationTripDetails.this,ActivityShowNavigationTripPlanMap.class);
									intent.putExtra("tripplanid", sTripPlanList.getItem(position).getTripPlanId());
									intent.putExtra("tripid",tripId);
									intent.putExtra("Participantsid", Participantsid);
									startActivity(intent);
//								}else if(tripentity.getTripState()==5){
//									ToastUtil.show(ActivityNavigationTripDetails.this, "请先启程");
//								}
								
					
							}
						});
						
						
					}else{
						ToastUtil.show(ActivityNavigationTripDetails.this, "数据获取异常");
					}
					
					
				}else if(msg.what==1){
					if(MainBindService.sParticipantsList.isFlag()){
						SetTripParticipantListEntity list=(SetTripParticipantListEntity) MainBindService.sParticipantsList.getData();
						//ActivityTripParticipantListAdapter adapter=new ActivityTripParticipantListAdapter(ActivityParticipantsList.this, list);
						int myid=(Integer) SPUtils.get(ActivityNavigationTripDetails.this,"userId", -1);
						List<TripParticipantEntity>li=list.getTripParticipant();
						pentity=new SetTripParticipantListEntity(li);
						for(int i=0;i<li.size();i++){
							if(li.get(i).getUserId()==myid){
								Participantsid=li.get(i).getTripParticipantId();
								break;
							}
						}
						
					}
				}else if(msg.what==2){
					Bitmap bitmap=(Bitmap) msg.obj;
					
					String url=tripentity.getTripImage();
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
		participant=(ImageView) findViewById(R.id.participant_image);
		listview=(MyListView) findViewById(R.id.listview_focus);
		triptitle=(TextView) findViewById(R.id.triptitle);
		createusername=(TextView) findViewById(R.id.creatusername);
		tripabstract=(TextView) findViewById(R.id.text_tripabstract);
		createtime=(TextView) findViewById(R.id.createtime);
		topimage=(ImageView) findViewById(R.id.topimage);
		start=(RelativeLayout) findViewById(R.id.start);
		end=(TextView) findViewById(R.id.end);
		
		
		listview.setFocusable(false);
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
		participant.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ActivityNavigationTripDetails.this,ActivityParticipantsList.class);
				intent.putExtra("navigationtripid", pentity);
				startActivity(intent);
			}
		});
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JSONObject json=new JSONObject();
				try {
					//json.put("tag", );
					json.put("", tripId);
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
		end.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
//		listview.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent(ActivityNavigationTripDetails.this,ActivityNavigationTripPlanDetail.class);
//				startActivity(intent);
//
//				
//			}
//		});
	}

}
