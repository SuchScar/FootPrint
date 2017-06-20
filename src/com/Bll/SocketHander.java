package com.Bll;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.json.JSONObject;

import android.os.Message;

import com.Activity.ActivityChatroom;
import com.Activity.ActivityCheckPrint;
import com.Activity.ActivityChooseFocus;
import com.Activity.ActivityComment;
import com.Activity.ActivityCreateFocus;
import com.Activity.ActivityEditNewTripPlan;
import com.Activity.ActivityFoucsDetail;
import com.Activity.ActivityHomeSearch;
import com.Activity.ActivityLogin;
import com.Activity.ActivityNavigationTripDetails;
import com.Activity.ActivityNavigationTripPlanDetail;
import com.Activity.ActivityRegister;
import com.Activity.ActivitySetting;
import com.Activity.ActivityShowNavigationTripPlanMap;
import com.Activity.ActivityTripDetail;
import com.Activity.ActivityWriteComment;
import com.Activity.ActivityWriteWalkTrack;
import com.Fragment.HomePageFragment;
import com.Fragment.MyFoucsCollectFragment;
import com.Fragment.MyTripCollectFragment;
import com.Fragment.NaviTripPageFragment;
import com.Tool.JSONtoDataSet;

 
public class SocketHander extends IoHandlerAdapter {	

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		JSONObject jsonObject = new JSONObject(message.toString());
		System.out.println("********"+jsonObject);
		JSONtoDataSet dataset=new JSONtoDataSet(jsonObject);
		CBLL Cbll=new CBLL();

//		if(dataset.getTag()==1){//1表示关注点列表
//			MainBindService.sFocusBriefList=Cbll.json2focusbrieflist(dataset);
//			HomePageFragment.mhander.sendEmptyMessage(1);
//		}
		if(dataset.getTag()==2){//表示关注点创建结果
			Message msg=new Message();
			msg.what=1;
			msg.obj=dataset.isFlag();
			ActivityCreateFocus.mhander.sendMessage(msg);
			
		}else if(dataset.getTag()==3){//获取关注点类型
			MainBindService.FoucsType=Cbll.json2foucstype(dataset);
			ActivityCreateFocus.mhander.sendEmptyMessage(0);
		}
		
		else if(dataset.getTag()==5){//5表示行程列表
			MainBindService.sTripBriefList=Cbll.json2tripbrieflist(dataset);
			HomePageFragment.mhander.sendEmptyMessage(0);
		}else if(dataset.getTag()==6){//6表示行程详情
			MainBindService.TripDetails=Cbll.json2tripdetails(dataset);
			ActivityTripDetail.mhander.sendEmptyMessage(0);
		}else if(dataset.getTag()==7){//表示评论列表
			MainBindService.CommentList=Cbll.json2commentlist(dataset);
			ActivityComment.mhander.sendEmptyMessage(0);
		}else if(dataset.getTag()==8){//表示评论上传结果
			Message msg=new Message();
			msg.what=1;
			msg.obj=dataset.isFlag();
			ActivityWriteComment.mhander.sendMessage(msg);
		}
		else if(dataset.getTag()==9){//表示修改行程安排结果
			Message msg=new Message();
			msg.what=1;
			msg.obj=dataset.isFlag();
			ActivityEditNewTripPlan.mhander.sendMessage(msg);
		}else if(dataset.getTag()==10){
			
			
		}else if(dataset.getTag()==11){//表示登录
			MainBindService.UserDetail=Cbll.json2userdetail(dataset);
			ActivityLogin.mhander.sendEmptyMessage(0);
			//MainActivity.mhandler.sendEmptyMessage(0);
			
		}else if(dataset.getTag()==12){//表示登陆退出
			Message msg=new Message();
			msg.what=1;
			msg.obj=dataset.isFlag();
			ActivitySetting.mhander.sendMessage(msg);
		}else if(dataset.getTag()==13){//表示导航行程列表
			MainBindService.sNavigationTripBriefList=Cbll.json2navigationtripbrieflist(dataset);
			NaviTripPageFragment.mhander.sendEmptyMessage(0);
		}else if(dataset.getTag()==14){//表示导航行程详情
			MainBindService.NavigationTripDetails=Cbll.json2navigationtripdetails(dataset);
			ActivityNavigationTripDetails.mhander.sendEmptyMessage(0);
		}else if(dataset.getTag()==15){//表示导航行程安排详情
			MainBindService.NavigationTripPlanDetails=Cbll.json2navigationtripplandetails(dataset);
			ActivityNavigationTripPlanDetail.mhander.sendEmptyMessage(0);
		}else if(dataset.getTag()==16){//表示导航轨迹(顺带接收了行走轨迹，已发布行程也使用这个)
			MainBindService.sNavigationWalkTrack=Cbll.json2navigationtrack(dataset);
			ActivityShowNavigationTripPlanMap.mhander.sendEmptyMessage(0);
		}else if(dataset.getTag()==17){//表示注册
			Message msg=new Message();
			msg.what=0;
			msg.obj=Cbll.json2register(dataset);
			ActivityRegister.mhander.sendMessage(msg);
		}else if(dataset.getTag()==21){//表示参与者列表
			MainBindService.sParticipantsList=Cbll.json2participantslist(dataset);
			ActivityNavigationTripDetails.mhander.sendEmptyMessage(1);
		}
		else if(dataset.getTag()==22){//表示上传行走轨迹结果
			Message msg=new Message();
			msg.what=1;
			msg.obj=dataset;
			ActivityWriteWalkTrack.mhander.sendMessage(msg);
		}else if(dataset.getTag()==23){//一开始获取聊天信息列表
			MainBindService.ChatInfoList=Cbll.json2chatinfolist(dataset);
			ActivityChatroom.mhander.sendEmptyMessage(0);
		}else if(dataset.getTag()==24){//接收单个行走轨迹
			dataset=Cbll.json2walktrackentity(dataset);
			Message msg=new Message();
			msg.what=1;
			msg.obj=dataset;
			ActivityShowNavigationTripPlanMap.mhander.sendMessage(msg);
		}else if(dataset.getTag()==25){//聊天结果接收
			Message msg=new Message();
			msg.what=1;
			msg.obj=dataset.isFlag();
			ActivityChatroom.mhander.sendMessage(msg);
		}else if(dataset.getTag()==26){//接收单个聊天信息
			dataset=Cbll.json2chatinfo(dataset);
			Message msg=new Message();
			msg.what=2;
			msg.obj=dataset;
			ActivityChatroom.mhander.sendMessage(msg);
		}else if(dataset.getTag()==30){//表示踩点
			Message msg=new Message();
			msg.what=1;
			msg.obj=dataset.isFlag();
			ActivityCheckPrint.mhander.sendMessage(msg);
		}else if(dataset.getTag()==35){//表示关注点详情
			MainBindService.Focusdetails=Cbll.json2foucsdetail(dataset);
			ActivityFoucsDetail.mhander.sendEmptyMessage(0);
		}else if(dataset.getTag()==37){//表示附近关注点
			MainBindService.sNearbyFoucsList=Cbll.json2nearbyfoucs(dataset);
			ActivityChooseFocus.mhander.sendEmptyMessage(0);
		}else if(dataset.getTag()==38){//我的收藏
			MainBindService.sMyCollect=Cbll.json2collectlist(dataset);
			if((Boolean) MainBindService.sMyCollect.getData()){
				MyFoucsCollectFragment.mhander.sendEmptyMessage(0);
				System.out.println("11111111111111111111");
			}else{
				MyTripCollectFragment.mhander.sendEmptyMessage(0);
				System.out.println("22222222222222222222222");
			}
		}else if(dataset.getTag()==39){//搜索关注点行程
			MainBindService.sSearchList=Cbll.json2searchresult(dataset);
			ActivityHomeSearch.mhander.sendEmptyMessage(0);
		}
		else if(dataset.getTag()==44){//根据城市名查询关注点列表
			MainBindService.sCityToFocusList=Cbll.json2cityfocuslist(dataset);
			ActivityCreateFocus.mhander.sendEmptyMessage(2);
		}
		
		
	}
	
	//发送消息
    public void messageSent(IoSession session, Object message) throws Exception {
    	//logger.info("发送给服务器消息 -> ：" + message);
    	System.out.println("发送给服务器消息 -> ：" + message);
    }

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		//logger.error("客户端发生异常...", cause);
		System.out.println("客户端发生异常..."+cause);
		
	}
}