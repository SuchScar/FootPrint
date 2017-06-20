package com.Bll;

import java.util.List;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;

import com.Activity.ActivityFoucsDetail;
import com.Activity.ActivityNavigationTripDetails;
import com.Activity.ActivityShowNavigationTripPlanMap;
import com.Activity.ActivityTripDetail;
import com.Adapter.ActivityFootPrintListAdapter;
import com.Adapter.ActivityNavigationTripAdapter;
import com.Adapter.ActivityNavigationTripFocusListAdapter;
import com.Adapter.ActivityTripPlanListAdapter;
import com.Adapter.CommentListAdapter;
import com.Adapter.FoucsBreifListAdapter;
import com.Adapter.FragmentTripPageListAdapter;
import com.Common.FileUploadRequest;
import com.Fragment.MyinfoPageFragment;

public class PictureHander extends IoHandlerAdapter {	

	@SuppressWarnings("unchecked")
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
//		JSONObject jsonObject = new JSONObject(message.toString());
//		System.out.println("********"+jsonObject);
//		JSONtoDataSet dataset=new JSONtoDataSet(jsonObject);
//		CBLL Cbll=new CBLL();
		if(message instanceof List){
			List<FileUploadRequest>fileUploadRequest=(List<FileUploadRequest>) message;
			int number=fileUploadRequest.get(0).getNumber();
			int tagpage=fileUploadRequest.get(0).getTagPage();
			Bitmap bitmap = null;
			for (int i = 0; i < fileUploadRequest.size(); i++) {
				
	            String  fileName = fileUploadRequest.get(i).getFileName();  
	            byte [] bytes = fileUploadRequest.get(i).getBytes();  
	            //String file = new Date().getTime()+fileName.substring(fileName.lastIndexOf("."),fileName.length()); 
	            if(bytes.length!=0){
            		 bitmap=BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            		}
	            System.out.println("success");
            }
			
			if(tagpage==1){//主页行程列表图片
				 Message msg=new Message();
					msg.what=1;
					msg.arg1=number;
					msg.obj=bitmap;
					FragmentTripPageListAdapter.mhander.sendMessage(msg);
			}else if(tagpage==2){//导航行程列表图片
				 Message msg=new Message();
					msg.what=1;
					msg.arg1=number;
					msg.obj=bitmap;
					ActivityNavigationTripAdapter.mhander.sendMessage(msg);
			}else if(tagpage==3){//搜索关注点列表图片
				 Message msg=new Message();
					msg.what=1;
					msg.arg1=number;
					msg.obj=bitmap;
					FoucsBreifListAdapter.mhander.sendMessage(msg);
			}else if(tagpage==4){//已发布行程安排列表图片
				 Message msg=new Message();
					msg.what=1;
					msg.arg1=number;
					msg.obj=bitmap;
					ActivityTripPlanListAdapter.mhander.sendMessage(msg);
			}else if(tagpage==5){//已发布行程安排列表图片
				 Message msg=new Message();
					msg.what=1;
					msg.arg1=number;
					msg.obj=bitmap;
					ActivityNavigationTripFocusListAdapter.mhander.sendMessage(msg);
			}else if(tagpage==6){//评论者头像
				 Message msg=new Message();
					msg.what=1;
					msg.arg1=number;
					msg.obj=bitmap;
					CommentListAdapter.mhander.sendMessage(msg);
			}else if(tagpage==7){//导航行程封面图
				 Message msg=new Message();
					msg.what=2;
					msg.arg1=number;
					msg.obj=bitmap;
					ActivityNavigationTripDetails.mhander.sendMessage(msg);
			}else if(tagpage==8){//已发布行程封面图
				 Message msg=new Message();
					msg.what=1;
					msg.arg1=number;
					msg.obj=bitmap;
					ActivityTripDetail.mhander.sendMessage(msg);
			}else if(tagpage==9){//我的信息fragment中的头像
				 Message msg=new Message();
					msg.what=1;
					msg.arg1=number;
					msg.obj=bitmap;
					MyinfoPageFragment.mhander.sendMessage(msg);
			}else if(tagpage==10){//修改信息活动中的头像
				 Message msg=new Message();
					msg.what=1;
					msg.arg1=number;
					msg.obj=bitmap;
					MyinfoPageFragment.mhander.sendMessage(msg);
			}else if(tagpage==11){//关注点详情封面
				 Message msg=new Message();
					msg.what=1;
					msg.arg1=number;
					msg.obj=bitmap;
					ActivityFoucsDetail.mhander.sendMessage(msg);
			}else if(tagpage==12){//踩点信息列表用户头像
				 Message msg=new Message();
					msg.what=1;
					msg.arg1=number;
					msg.obj=bitmap;
					ActivityFootPrintListAdapter.mhander.sendMessage(msg);
			}else if(tagpage==13){//导航轨迹brief图片
				 Message msg=new Message();
					msg.what=2;
					msg.arg1=number;
					msg.obj=bitmap;
					ActivityShowNavigationTripPlanMap.mhander.sendMessage(msg);
			}
			
			
			
			

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
