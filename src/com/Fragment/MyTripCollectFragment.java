package com.Fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.chainsaw.Main;
import org.json.JSONException;
import org.json.JSONObject;

import com.Activity.ActivityFoucsDetail;
import com.Activity.ActivityTripDetail;
import com.Activity.R;
import com.Adapter.FragmentTripPageListAdapter;
import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Tool.SPUtils;

import Entity.FocusBriefEntity;
import Entity.SetTripBriefListEntity;
import Entity.TripBriefEntity;
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


public class MyTripCollectFragment extends Fragment{

	private ListView listview3;
	private FragmentTripPageListAdapter adapter;
	private SetTripBriefListEntity list;
	
	private int userid;
	public static Handler mhander;
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		View contactsView = inflater.inflate(R.layout.listview, container,false);
	    listview3=(ListView)contactsView.findViewById(R.id.listView1);
	
		return contactsView;
		
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		userid=(Integer) SPUtils.get(getActivity(),"userId", -1);
		//initdate();
		JSONObject json=new JSONObject();
		try {
			json.put("tag", 38);
			json.put("page",1 );
			json.put("userId", userid);
			json.put("collectStyle", 2);
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
					list=(SetTripBriefListEntity) MainBindService.sMyCollect.getData2();
					adapter = new FragmentTripPageListAdapter(getActivity(), list);
					listview3.setAdapter(adapter);
					listview3.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							Intent intent=new Intent(getActivity(),ActivityTripDetail.class);
							intent.putExtra("tripid", list.getItem(arg2).getTrip_id());
							startActivity(intent);
						}
					});
				}
			}
		};
	}
	private void initdate() {
		{
			List<TripBriefEntity>l=new ArrayList<TripBriefEntity>();
			TripBriefEntity ss  = new TripBriefEntity(R.drawable.aa,"宁波工程学院","宁波以“书藏古今，港通天下”为口号。宁波历史悠久，是具有7000多年文明史的“河姆渡文化”发祥地。唐代，宁波成为“海上丝绸之路”的起点之一，与扬州、广州并称为中国三大对外贸易港口。","2016/05/27","创建人");
			l.add(ss);
			TripBriefEntity aa  = new TripBriefEntity(R.drawable.aa,"宁波工程学院","宁波以“书藏古今，港通天下”为口号。宁波历史悠久，是具有7000多年文明史的“河姆渡文化”发祥地。唐代，宁波成为“海上丝绸之路”的起点之一，与扬州、广州并称为中国三大对外贸易港口。","2016/05/27","创建人");
			l.add(aa);
			TripBriefEntity bb  = new TripBriefEntity(R.drawable.aa,"宁波工程学院","宁波以“书藏古今，港通天下”为口号。宁波历史悠久，是具有7000多年文明史的“河姆渡文化”发祥地。唐代，宁波成为“海上丝绸之路”的起点之一，与扬州、广州并称为中国三大对外贸易港口。","2016/05/27","创建人");
			l.add(bb);
			TripBriefEntity cc  = new TripBriefEntity(R.drawable.aa,"宁波工程学院","宁波以“书藏古今，港通天下”为口号。宁波历史悠久，是具有7000多年文明史的“河姆渡文化”发祥地。唐代，宁波成为“海上丝绸之路”的起点之一，与扬州、广州并称为中国三大对外贸易港口。","2016/05/27","创建人");
			l.add(cc);
			TripBriefEntity ee  = new TripBriefEntity(R.drawable.aa,"宁波工程学院","宁波以“书藏古今，港通天下”为口号。宁波历史悠久，是具有7000多年文明史的“河姆渡文化”发祥地。唐代，宁波成为“海上丝绸之路”的起点之一，与扬州、广州并称为中国三大对外贸易港口。","2016/05/27","创建人");
			l.add(ee);
			TripBriefEntity nn  = new TripBriefEntity(R.drawable.aa,"宁波工程学院","宁波以“书藏古今，港通天下”为口号。宁波历史悠久，是具有7000多年文明史的“河姆渡文化”发祥地。唐代，宁波成为“海上丝绸之路”的起点之一，与扬州、广州并称为中国三大对外贸易港口。","2016/05/27","创建人");
			l.add(nn);
			TripBriefEntity mm  = new TripBriefEntity(R.drawable.aa,"宁波工程学院","宁波以“书藏古今，港通天下”为口号。宁波历史悠久，是具有7000多年文明史的“河姆渡文化”发祥地。唐代，宁波成为“海上丝绸之路”的起点之一，与扬州、广州并称为中国三大对外贸易港口。","2016/05/27","创建人");
			l.add(mm);
			TripBriefEntity ww  = new TripBriefEntity(R.drawable.aa,"宁波工程学院","宁波以“书藏古今，港通天下”为口号。宁波历史悠久，是具有7000多年文明史的“河姆渡文化”发祥地。唐代，宁波成为“海上丝绸之路”的起点之一，与扬州、广州并称为中国三大对外贸易港口。","2016/05/27","创建人");
			l.add(ww);
			list=new SetTripBriefListEntity(l);
			}
		
		
		}
}
