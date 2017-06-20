package com.Activity;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Activity.R.color;
import com.Adapter.FragmentAdapter;
import com.Fragment.MyFoucsCollectFragment;
import com.Fragment.MyTripCollectFragment;


public class ActivityMyCollection extends FragmentActivity{
	private ViewPager mPageVp; //一个装界面的容器

	private List<Fragment> mFragmentList = new ArrayList<Fragment>();
	private FragmentAdapter mFragmentAdapter; //一个FragmentAdapter适配器

	
	private TextView mTabChatTv, mTabContactsTv;

	private ImageView mTabLineIv,fanhui;
	
	private MyFoucsCollectFragment mFoucsF;
	private MyTripCollectFragment mTripF;
	
	private int currentIndex;
	/**
	 *屏幕的宽度
	 */
	private int screenWidth;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_mycollection);
		
		findById();
		init();
		initTabLineWidth();
	}
	
	private void findById() {
		    fanhui=(ImageView)findViewById(R.id.fanhui);
			mTabContactsTv = (TextView) findViewById(R.id.id_contacts_tv);
			mTabChatTv = (TextView) findViewById(R.id.id_chat_tv);

			mTabLineIv = (ImageView) findViewById(R.id.id_tab_line_iv);

			mPageVp = (ViewPager) findViewById(R.id.id_page_vp);
		}

		
		@SuppressWarnings("deprecation")
		private void init() {
			
			mTabChatTv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 
						   mPageVp.setCurrentItem(0);  
						
					    }  
					 
				}
			);
			
			mTabContactsTv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 
						   mPageVp.setCurrentItem(1);  
						
					    }  
					 
				}
			);
			mTripF = new MyTripCollectFragment();
			mFoucsF = new MyFoucsCollectFragment();
			mFragmentList.add(mFoucsF);
			mFragmentList.add(mTripF);

			mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
			mPageVp.setAdapter(mFragmentAdapter);
			mPageVp.setCurrentItem(0);

			mPageVp.setOnPageChangeListener(new OnPageChangeListener() {

			
				
				@Override
				public void onPageScrollStateChanged(int state) {

				}

				
				@Override
				public void onPageScrolled(int position, float offset,
						int offsetPixels) {
					LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
							.getLayoutParams();

					if (currentIndex == 0 && position == 0)// 0->1
					{
						lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
								* (screenWidth / 2));

					} else if (currentIndex == 1 && position == 0) // 1->0
					{
						lp.leftMargin = (int) (-(1 - offset)
								* (screenWidth * 1.0 /2) + currentIndex
								* (screenWidth / 2));

					} else if (currentIndex == 1 && position == 1) // 1->2
					{
						lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
								* (screenWidth / 2));
					}
					mTabLineIv.setLayoutParams(lp);
					
//					switch (position) {
//					case 0:
//						mTabChatTv.setTextColor(R.color.orange);
//						mTabContactsTv.setTextColor(Color.BLACK);
//						break;
//				
//					case 1:
//						mTabContactsTv.setTextColor(R.color.orange);
//						mTabChatTv.setTextColor(Color.BLACK);
//						break;
//					}
				}

				 @Override
				public void onPageSelected(int position) {
					switch (position) {
					case 0:
						mTabChatTv.setTextColor(getResources().getColor(R.color.orange));
						mTabContactsTv.setTextColor(Color.BLACK);
						break;
				
					case 1:
						mTabContactsTv.setTextColor(getResources().getColor(R.color.orange));
						mTabChatTv.setTextColor(Color.BLACK);
						break;
					}
					currentIndex = position;
				}
			});
	fanhui.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
		}

		
		private void initTabLineWidth() {
			DisplayMetrics dpMetrics = new DisplayMetrics();
			getWindow().getWindowManager().getDefaultDisplay()
					.getMetrics(dpMetrics);  //获取到屏幕的宽度 并将宽度存放在 自己设置的参数中
			screenWidth = dpMetrics.widthPixels;
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
					.getLayoutParams();
			lp.width = screenWidth / 2;
			mTabLineIv.setLayoutParams(lp);
		}

		
}
