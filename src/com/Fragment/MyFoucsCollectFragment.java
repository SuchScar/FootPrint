package com.Fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import Entity.FocusBriefEntity;
import Entity.SetFocusBriefListEntity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.Activity.ActivityFoucsDetail;
import com.Activity.R;
import com.Adapter.FoucsBreifListAdapter;
import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Tool.SPUtils;




public class MyFoucsCollectFragment extends Fragment{
	private  ListView listview1;
	private FoucsBreifListAdapter adapter;
	private SetFocusBriefListEntity list;
	
	private int userid;
	
	public static Handler mhander;
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		View chatView = inflater.inflate(R.layout.listview, container,false);
		listview1=(ListView) chatView.findViewById(R.id.listView1);
		
		return chatView;
	
		
			}
		
	

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		userid=(Integer) SPUtils.get(getActivity(),"userId", -1);
		JSONObject json=new JSONObject();
		try {
			json.put("tag", 38);
			json.put("page",1 );
			json.put("userId", userid);
			json.put("collectStyle", 1);
			MinaSocket.SendMessage(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					list=(SetFocusBriefListEntity) MainBindService.sMyCollect.getData1();
					adapter = new FoucsBreifListAdapter(getActivity(), list);
					listview1.setAdapter(adapter);
					listview1.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							Intent intent=new Intent(getActivity(),ActivityFoucsDetail.class);
							intent.putExtra("foucsid", list.getItem(arg2).getFocus_id());
							startActivity(intent);
							
						}
					});
				}
			}
		};
	}




	
}
