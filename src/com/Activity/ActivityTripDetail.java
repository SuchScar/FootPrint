package com.Activity;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import Entity.SetCommentListEntity;
import Entity.SetTripPlanListEntity;
import Entity.TripEntitry;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Adapter.ActivityTripPlanListAdapter;
import com.Adapter.CommentListAdapter;
import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Bll.PictureSocket;
import com.Lru.DiskLruCache;
import com.Lru.LruCacheUtils;
import com.Tool.MyListView;
import com.Tool.SPUtils;
import com.Tool.ToastUtil;

public class ActivityTripDetail extends Activity {

	private ImageView topimage, back;
	private LinearLayout listview_focus, listview_comment, liner_usercomment,
			liner_writecomment;
	private TextView triptitle, createusername, tripabstract, createtime;
	private Intent intent;
	private int tripid,tripplanid,userid;
	
	private TripEntitry tripentity;

	// 数据更新获取
	public static Handler mhander;
	private LruCacheUtils lruCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tripdetail);
		lruCache = LruCacheUtils.getInstance();
		initView();
		setlisten();

		intent = getIntent();
		tripid=intent.getIntExtra("tripid", -1);
		ToastUtil.show(ActivityTripDetail.this,
				intent.getIntExtra("tripid", -1) + "");
		userid=(Integer) SPUtils.get(ActivityTripDetail.this,"userId",0);
		TripEntitry tripentity = new TripEntitry();

		try {
			MinaSocket.SendMessage(tripentity.ToJSON(6,tripid,userid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateUI();
	}

	private void updateUI() {
		mhander = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 0) {
					if (MainBindService.TripDetails.isFlag()) {
						tripentity = new TripEntitry();
						tripentity = (TripEntitry) MainBindService.TripDetails
								.getData();
						SetTripPlanListEntity sTripPlanList = new SetTripPlanListEntity();
						sTripPlanList = (SetTripPlanListEntity) MainBindService.TripDetails
								.getData1();
						SetCommentListEntity sCommentList = new SetCommentListEntity();
						sCommentList = (SetCommentListEntity) MainBindService.TripDetails
								.getData2();
						showlistview(sTripPlanList, sCommentList);
						lruCache.loadBitmap(topimage,tripentity.getTripImage(),8);
						
						triptitle.setText(tripentity.getTripTitle());
						createusername.setText(tripentity.getTripCreateUser());
						tripabstract.setText(tripentity.getTripSummary());
						createtime.setText(tripentity.getTripCreateTime());
					} else {
						ToastUtil.show(ActivityTripDetail.this, "数据获取异常");
					}
				}else if(msg.what == 1){
					
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

	private void showlistview(final SetTripPlanListEntity sTripPlanList,
			SetCommentListEntity sCommentList) {

		LayoutInflater inflate = LayoutInflater.from(ActivityTripDetail.this);
		LinearLayout layout = (LinearLayout) inflate.inflate(
				R.layout.activity_tripdetail_focus_class, null).findViewById(
				R.id.listview_focus);
		MyListView lv = (MyListView) layout.getChildAt(0);

		LinearLayout layout1 = (LinearLayout) inflate.inflate(
				R.layout.activity_comment_class, null).findViewById(
				R.id.listview_comment);
		MyListView lv1 = (MyListView) layout1.getChildAt(0);

		lv1.setAdapter(new CommentListAdapter(ActivityTripDetail.this,
				sCommentList));
		lv.setAdapter(new ActivityTripPlanListAdapter(ActivityTripDetail.this,
				sTripPlanList));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(ActivityTripDetail.this,
						ActivityShowTripPlanMap.class);
				int tripplanid=sTripPlanList.getItem(position).getTripPlanId();
				intent.putExtra("tripid", tripid);
				intent.putExtra("tripplanid", tripplanid);
				startActivity(intent);
			}
		});
		lv.setFocusable(false);
		lv1.setFocusable(false);
		listview_focus.addView(layout);
		listview_comment.addView(layout1);

	}

	private void initView() {
		topimage = (ImageView) findViewById(R.id.topimage);
		listview_focus = (LinearLayout) findViewById(R.id.listview_focus);
		listview_comment = (LinearLayout) findViewById(R.id.listview_comment);
		back = (ImageView) findViewById(R.id.back_image);
		liner_usercomment = (LinearLayout) findViewById(R.id.liner_usercomment);
		liner_writecomment = (LinearLayout) findViewById(R.id.liner_writecomment);
		triptitle = (TextView) findViewById(R.id.triptitle);
		createusername = (TextView) findViewById(R.id.creatusername);
		tripabstract = (TextView) findViewById(R.id.text_tripabstract);
		createtime = (TextView) findViewById(R.id.createtime);

		int screenWidth = getwidth();
		ViewGroup.LayoutParams lp = topimage.getLayoutParams();
		lp.width = screenWidth;
		lp.height = (int) (screenWidth / 4 * 3);
		topimage.setLayoutParams(lp);
	}

	private void setlisten() {
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		liner_usercomment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent inten = new Intent(ActivityTripDetail.this,
						ActivityComment.class);
				inten.putExtra("tripid", intent.getIntExtra("tripid", -1));
				startActivity(inten);
			}
		});
		liner_writecomment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent inten = new Intent(ActivityTripDetail.this,
						ActivityWriteComment.class);
				inten.putExtra("tripid", intent.getIntExtra("tripid", -1));
				startActivity(inten);
			}
		});
	}
	public int getwidth() {

		DisplayMetrics dm = new DisplayMetrics();
		ActivityTripDetail.this.getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		// 2.获取分辨率宽度
		int screenW = dm.widthPixels;
		return screenW;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//ActivityTripPlanListAdapter.piclist.clear();
	}

}
