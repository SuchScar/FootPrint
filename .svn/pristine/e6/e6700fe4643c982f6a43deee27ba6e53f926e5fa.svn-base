package com.Activity;

import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Tool.ToastUtil;
import Entity.TripPlanEntity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityNavigationTripPlanDetail extends Activity {

	private ImageView topimage, back;
	private LinearLayout edit;
	private TextView focustitle, username, tripmainplan;

	private int tripplanid;
	private int tripid, Participantsid;

	// 数据更新获取
	public static Handler mhander;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_navigation_tripplan_detail);
		Intent intent = getIntent();
		tripplanid = intent.getIntExtra("tripplanid", -1);
		tripid = intent.getIntExtra("tripid", -1);
		Participantsid = intent.getIntExtra("Participantsid", -1);
		try {
			MinaSocket.SendMessage(new TripPlanEntity().ToJSON(15, tripplanid));
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView();
		setlisten();

		UpdateUI();

	}

	private void UpdateUI() {
		mhander = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 0) {
					if (MainBindService.NavigationTripPlanDetails.isFlag()) {
						TripPlanEntity tripplan = (TripPlanEntity) MainBindService.NavigationTripPlanDetails
								.getData();
						focustitle.setText(tripplan.getFocusTitle());
						username.setText(tripplan.getTripParticipantName());
						tripmainplan.setText(tripplan.getTripMainPlan());

					} else {
						ToastUtil.show(ActivityNavigationTripPlanDetail.this,
								"数据获取异常");
					}
				}

			}
		};
	}

	private void initView() {
		topimage = (ImageView) findViewById(R.id.topimage);
		back = (ImageView) findViewById(R.id.back_image);
		edit = (LinearLayout) findViewById(R.id.liner_edit);
		focustitle = (TextView) findViewById(R.id.focus_title);
		username = (TextView) findViewById(R.id.username);
		tripmainplan = (TextView) findViewById(R.id.text_tripmainplan);

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
		edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						ActivityNavigationTripPlanDetail.this,
						ActivityEditNewTripPlan.class);
				intent.putExtra("tripplanid", tripplanid);
				startActivity(intent);
			}
		});
	}

	public int getwidth() {

		DisplayMetrics dm = new DisplayMetrics();
		ActivityNavigationTripPlanDetail.this.getWindowManager()
				.getDefaultDisplay().getMetrics(dm);
		// 2.获取分辨率宽度
		int screenW = dm.widthPixels;
		return screenW;
	}
}
