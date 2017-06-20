package com.Fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Entity.SetTripBriefListEntity;
import Entity.TripBriefEntity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.Activity.ActivityLogin;
import com.Activity.ActivityNavigationTripDetails;
import com.Activity.ActivityNavigationTripList;
import com.Activity.ActivityTripDetail;
import com.Activity.R;
import com.Adapter.ActivityNavigationTripAdapter;
import com.Adapter.FragmentTripPageListAdapter;
import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Tool.SPUtils;
import com.Tool.ToastUtil;

public class NaviTripPageFragment extends Fragment implements OnScrollListener {

	private ListView listview;
	private LinearLayout login;
	
	//自定义变量
	private ActivityNavigationTripAdapter adapter;
	private SetTripBriefListEntity list;
	private int userid;
	
	//刷新数据变量
	private View footview;
	private TextView tv_tip;
	private LinearLayout vis;
	private int i=1;
	private int visibleLastIndex;   //用来可显示的最后一条数据的索引值
	private ProgressBar progressBar;
	private boolean moredata = true;
	private boolean firstdata = true;
	private boolean firstrequest=true;

	//数据更新获取
	public static Handler mhander;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_trip, null);
		footview=inflater.inflate(R.layout.loading, null);
		findview(view);
		return view;

	}

	private void findview(View view) {
 
		listview=(ListView)view.findViewById(R.id.listview);
		tv_tip=(TextView) footview.findViewById(R.id.textView1);
		progressBar=(ProgressBar) footview.findViewById(R.id.progressBar1);
		vis=(LinearLayout) footview.findViewById(R.id.vis);
		login=(LinearLayout) view.findViewById(R.id.login);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		
		listview.setOnScrollListener(this);
		userid=(Integer) SPUtils.get(getActivity(), "userId",-1);
		if(userid!=-1){
			if(firstdata){
			login.setVisibility(View.GONE);
			JSONObject json=new JSONObject();
			json=new TripBriefEntity().ToJSON(13,1,userid);
			try {
				MinaSocket.SendMessage(json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			firstrequest=false;
			}
		}else{
			ToastUtil.show(getActivity(), "请先登陆您的账户");
			
		}
		setListen();

		updateUI();
	}
	
	private void setListen() {
		// TODO Auto-generated method stub
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),ActivityLogin.class);
				startActivityForResult(intent, 1);
			}
		});
	}

	private void updateUI() {
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==0){
					login.setVisibility(View.GONE);
					if(MainBindService.sNavigationTripBriefList.isFlag()){
					if(firstdata){	
					list=(SetTripBriefListEntity)MainBindService.sNavigationTripBriefList.getData();
					if(list.getSize()<15){
						vis.setVisibility(View.GONE);
					}
					adapter = new ActivityNavigationTripAdapter(getActivity(), list);
					listview.addFooterView(footview);
					listview.setAdapter(adapter);
					listview.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							Intent intent=new Intent(getActivity(),ActivityNavigationTripDetails.class);
							intent.putExtra("navigationtripid", list.getItem(position).getTrip_id());
							startActivity(intent);
						}
					});
					firstdata=false;
					}else{
						List<TripBriefEntity>l=new ArrayList<TripBriefEntity>();
						List<TripBriefEntity>nl=new ArrayList<TripBriefEntity>();
						
						nl=((SetTripBriefListEntity) MainBindService.sNavigationTripBriefList.getData()).getTripBriefList();
						if(!nl.isEmpty()){
						l=list.getTripBriefList();
						l.addAll(nl);
						list=new SetTripBriefListEntity(l);
						adapter.notifyDataSetChanged();
						}else{
							progressBar.setVisibility(View.GONE);
							tv_tip.setText("没有更多");
							moredata=false;
						}	
					}
					}else{
						ToastUtil.show(getActivity(), "数据获取异常");
					}

				}

			}
		};
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if(scrollState == SCROLL_STATE_IDLE && moredata &&
				adapter.getCount() == visibleLastIndex){
			i=i+1;	
			requestServer(i);
			
		}
	}
	private void requestServer(int i) {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		json=new TripBriefEntity().ToJSON(13,i,userid);
		try {
			MinaSocket.SendMessage(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		visibleLastIndex = firstVisibleItem + visibleItemCount-1;
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1){
			userid=(Integer) SPUtils.get(getActivity(), "userId",-1);
			if(userid!=-1){
				login.setVisibility(View.GONE);
				JSONObject json=new JSONObject();
				json=new TripBriefEntity().ToJSON(13,1,userid);
				try {
					MinaSocket.SendMessage(json);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				ToastUtil.show(getActivity(), "请先登陆您的账户");
				
			}
		}
		
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		userid=(Integer) SPUtils.get(getActivity(), "userId",-1);
		if(userid!=-1){
			if(firstrequest){
			login.setVisibility(View.GONE);
			JSONObject json=new JSONObject();
			json=new TripBriefEntity().ToJSON(13,1,userid);
			try {
				MinaSocket.SendMessage(json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			firstrequest=false;
			}
		}else{
			listview.setAdapter(null);
			listview.removeFooterView(footview);
			i=1;
			firstdata=true;
			moredata=true;
			firstrequest=true;
			progressBar.setVisibility(View.VISIBLE);
			tv_tip.setText("加载更多");
			ToastUtil.show(getActivity(), "请先登陆您的账户");
			
		}
	}

}
