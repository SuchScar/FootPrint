package com.Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.Lru.LruCacheUtils;
import com.Tool.Bimp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


public class ActivityPreview extends Activity{

	private ImageView back,delete;
	private TextView all,num;
	private ViewPager viewpager;
	
	private ViewPagerAdapter adapter;
	private ArrayList<View> lists = new ArrayList<View>();
	private int number;
	private int CurrentView;
	private ArrayList<String> path; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_preview);
		Intent intent=getIntent();
		number=intent.getIntExtra("position", -1);
		path=intent.getStringArrayListExtra("path");
		initData();
		initView();
		
		
		adapter=new ViewPagerAdapter(lists);
		viewpager.setAdapter(adapter);
		viewpager.setCurrentItem(number);
		initListen();
	}

	private void initData()  {
		for(int i=0;i<Bimp.tempSelectBitmap.size();i++){
			View view=getLayoutInflater().inflate(R.layout.imageview, null);
			ImageView image=(ImageView) view.findViewById(R.id.image);
			 image.setImageBitmap(Bimp.tempSelectBitmap.get(i).getBitmap());
			lists.add(view);
		}
	}

	@SuppressWarnings("deprecation")
	private void initListen() {
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.putStringArrayListExtra("path",path);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				num.setText(arg0+1+"");
				CurrentView=arg0;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bimp.tempSelectBitmap.remove(CurrentView);
				lists.remove(CurrentView);
				//adapter.destroyItem(viewpager, CurrentView, null);
				adapter.setListViews(lists);
				adapter.notifyDataSetChanged();
				path.remove(CurrentView);
				all.setText(path.size()+"");
			}
		});
		
	}

	private void initView() {
		back=(ImageView) findViewById(R.id.back_image);
		delete=(ImageView) findViewById(R.id.delete);
		all=(TextView) findViewById(R.id.all);
		num=(TextView) findViewById(R.id.num);
		viewpager=(ViewPager) findViewById(R.id.viewpager);
		
		all.setText(Bimp.tempSelectBitmap.size()+"");
		num.setText(number+1+"");
		CurrentView=number;
	}
	
	public class ViewPagerAdapter extends PagerAdapter {  

		private ArrayList<View> viewLists;  
	    private int size;        //窗体界面数
	      
	    public ViewPagerAdapter(ArrayList<View> lists)  
	    {  
	        viewLists = lists;  
	        size = lists == null? 0: lists.size();
	    }  
	    @Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			//return super.getItemPosition(object);
	    	return POSITION_NONE;
		}
	    
	    public void setListViews(ArrayList<View> listviews){
	    	this.viewLists = listviews;
	    	size = listviews == null?0 : listviews.size();
	    }
	    //获取当前窗体界面数
	    @Override  
	    public int getCount() {   
	        return size;  
	    }  
	  
	    @Override  
	    public boolean isViewFromObject(View arg0, Object arg1) {                           
	        return arg0 == arg1;  
	    }  
	      
	    //是从ViewGroup中移出当前View  
	    @Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
	    	// TODO Auto-generated method stub
	    	container.removeView((View) object); 
	    }
	    //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
	    @Override
	    public Object instantiateItem(ViewGroup container, int position) {
	    	// TODO Auto-generated method stub
	    	 container.addView(viewLists.get(position));  
	         return viewLists.get(position);
	    }
	} 
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
