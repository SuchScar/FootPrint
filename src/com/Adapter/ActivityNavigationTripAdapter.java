package com.Adapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.Activity.R;
import com.Bll.PictureSocket;
import com.Lru.DiskLruCache;
import com.Lru.LruCacheUtils;
import com.Tool.CallBack;
import com.Tool.CallList;

import Entity.SetTripBriefListEntity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityNavigationTripAdapter extends BaseAdapter{

	private SetTripBriefListEntity list;
	private Context context;
	
	private LruCacheUtils lruCache;
	
	//数据更新获取
	public static Handler mhander;
	public List<CallList> piclist=new ArrayList<CallList>();
	
	 public ActivityNavigationTripAdapter(Context c, SetTripBriefListEntity l) {
	    	super();
	    	context = c;
	    	list = l;
	    	
	    	lruCache = LruCacheUtils.getInstance();
	    	mhander=new Handler(){
				@Override
				
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					
					if(msg.what==1){
						for(int i=0;i<piclist.size();i++){
							if(piclist.get(i).getPos()==msg.arg1){
								Bitmap bitmap=(Bitmap) msg.obj;
								
								String url=list.getItem(msg.arg1).getTrip_image();
								String key = lruCache.hashKeyForDisk(url);
				                DiskLruCache.Editor editor = null;
								
								piclist.get(i).getEntity().response(bitmap);
								
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
								piclist.remove(i);
								break;
							}
						}
	 				}
				}
			};
	    	
	    }
	 
	//定义内部类作为占位符
	    class ViewHolder{
	    	ImageView trip_image;
	    	TextView trip_title;
	    	TextView trip_abstract;
	    	TextView trip_author;
	    	TextView trip_state;
	    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.getSize();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.getItem(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder viewholder = null;
		if(arg1 == null){
			arg1 = LayoutInflater.from(context).inflate(R.layout.fragment_trip_list_item, null);
			viewholder = new ViewHolder();
			//获得视图中的对象控件
			viewholder.trip_title = (TextView) arg1.findViewById(R.id.trip_title);
			viewholder.trip_abstract = (TextView) arg1.findViewById(R.id.trip_abstract);
			viewholder.trip_author = (TextView) arg1.findViewById(R.id.trip_username);
			viewholder.trip_state=(TextView)arg1.findViewById(R.id.trip_time);
			viewholder.trip_image=(ImageView) arg1.findViewById(R.id.trip_image);
			arg1.setTag(viewholder);
		}
		else{
			viewholder = (ViewHolder) arg1.getTag();
		}
		loadBitmap(viewholder.trip_image,arg0);
		
		viewholder.trip_title.setText(list.getItem(arg0).getTrip_title());
		viewholder.trip_abstract.setText(list.getItem(arg0).getTrip_abstract());
		viewholder.trip_author.setText(list.getItem(arg0).getTrip_author());
		if(list.getItem(arg0).getTrip_state().equals("5")){
		viewholder.trip_state.setText("未开始");
		}else if(list.getItem(arg0).getTrip_state().equals("6")){
			viewholder.trip_state.setText("进行中");
		}
		return arg1;
	}
	
	private void loadBitmap(final ImageView focus_image, final int position) {
		String url=list.getItem(position).getTrip_image();
		// 从内存缓存中取图片
		Bitmap bitmap = lruCache.getBitmapFromCahce(url);
		if (bitmap == null) { 
			// 再从磁盘缓存中取图片
			InputStream in = lruCache.getDiskCache(url);
			if (in == null) {
				JSONObject json=new JSONObject();
				try { 
					json.put("tag", 101);
					json.put("number", position);
					json.put("tagpage",2);
					json.put("fileName", url);
					PictureSocket.SendMessage(json);
					
					
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				CallBack<Bitmap> back=new  CallBack<Bitmap>(){

					@Override
					public void response(Bitmap entity) {
						focus_image.setImageBitmap(entity);
					}
				};
				piclist.add(new CallList(position,back)); 
			} else {
				System.out.println("disk cache");
				bitmap = BitmapFactory.decodeStream(in);
				lruCache.addBitmapToCache(url, bitmap);
				if(bitmap!=null){
					focus_image.setImageBitmap(bitmap);
				}
				else{
					focus_image.setImageResource(R.drawable.aa);
				}
			}
		} else {
			System.out.println("memory cache");
			if(bitmap!=null){
				focus_image.setImageBitmap(bitmap);
			}
			else{
				focus_image.setImageResource(R.drawable.aa);
			}
		}
	}

}
