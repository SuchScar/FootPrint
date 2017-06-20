package com.Bll;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import Entity.CheckInfoEntity;
import Entity.CommentEntity;
import Entity.FocusBriefEntity;
import Entity.FocusEntity;
import Entity.SetCheckInfoListEntity;
import Entity.SetCommentListEntity;
import Entity.SetFocusBriefListEntity;
import Entity.SetTripBriefListEntity;
import Entity.SetTripNavigationTrackListEntity;
import Entity.SetTripParticipantListEntity;
import Entity.SetTripPlanListEntity;
import Entity.SetTripWalkTrackListEntity;
import Entity.TripBriefEntity;
import Entity.TripEntitry;
import Entity.TripNavigationTrackEntity;
import Entity.TripParticipantEntity;
import Entity.TripPlanEntity;
import Entity.TripWalkTrackEntity;
import Entity.UserEntity;
import android.R.bool;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;

import com.Common.FileUploadRequest;
import com.Fragment.HomePageFragment;
import com.Fragment.NaviTripPageFragment;
import com.Lru.LruCacheUtils;
import com.Tool.FoucsType;
import com.Tool.JSONtoDataSet;

public class CBLL {
	/**
	 * ----------------------关注点列表-------------------------------------------
	 */
	public JSONtoDataSet json2focusbrieflist(JSONtoDataSet dataset) {

		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		try {
		//Message msg=new Message();
		if(dataset.isFlag()){
			//msg.what=1;
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=new JSONObject();
			json=(JSONObject) dataset.getData();
			JSONArray focusbriefarray=(JSONArray) json.get("SetFoucsBriefInfo");
			List<FocusBriefEntity>FocusBrieflist=new ArrayList<FocusBriefEntity>();
			for(int i=0;i<focusbriefarray.length();i++){
				
				FocusBriefEntity focusbrief=new FocusBriefEntity((JSONObject) focusbriefarray.get(i));
				FocusBrieflist.add(focusbrief);
			}			
			SetFocusBriefListEntity sFocusBriefList=new SetFocusBriefListEntity(FocusBrieflist);
			jsontodataset.setData(sFocusBriefList);
			//msg.obj=sFocusBriefList;
			
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsontodataset;
		
		
	}
	
	/**
	 * ----------------------行程列表-------------------------------------------
	 */
	public JSONtoDataSet json2tripbrieflist(JSONtoDataSet dataset) {
		
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		try {
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=new JSONObject();
			json=(JSONObject) dataset.getData();
			JSONArray tripbriefarray=(JSONArray) json.get("SetTripBriefInfo");
			List<TripBriefEntity>TripBrieflist=new ArrayList<TripBriefEntity>();
			for(int i=0;i<tripbriefarray.length();i++){
				
				TripBriefEntity tripbrief=new TripBriefEntity((JSONObject) tripbriefarray.get(i));
				TripBrieflist.add(tripbrief);
			}			
			SetTripBriefListEntity sTripBriefList=new SetTripBriefListEntity(TripBrieflist);
			jsontodataset.setData(sTripBriefList);
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return jsontodataset;
	}

	/**
	 * ----------------------已发布行程详情-------------------------------------------
	 */
	public JSONtoDataSet json2tripdetails(JSONtoDataSet dataset) {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=new JSONObject();
			json=(JSONObject) dataset.getData();
			
			try {
				JSONObject tripdetail=new JSONObject();
				tripdetail=json.getJSONObject("TripDetailInfo");
				JSONArray planarray=new JSONArray();
				JSONArray commentarray=new JSONArray();
				planarray=json.getJSONArray("SetTripPlan");
				commentarray=json.getJSONArray("SetTripComment");
				
				TripEntitry tripentity=new TripEntitry(tripdetail);
				jsontodataset.setData(tripentity);
				
				List<TripPlanEntity>TripPlanList=new ArrayList<TripPlanEntity>();
				for(int i=0;i<planarray.length();i++){
					
					TripPlanEntity tripplanentity=new TripPlanEntity(planarray.getJSONObject(i));
					
					TripPlanList.add(tripplanentity);
				}	
				SetTripPlanListEntity sTripPlanList=new SetTripPlanListEntity(TripPlanList);
				jsontodataset.setData1(sTripPlanList);
				
				List<CommentEntity>CommentList=new ArrayList<CommentEntity>();
				for(int i=0;i<commentarray.length();i++){
					
					CommentEntity commententity=new CommentEntity(commentarray.getJSONObject(i));
					
					CommentList.add(commententity);
				}	
				SetCommentListEntity sCommentList=new SetCommentListEntity(CommentList);
				jsontodataset.setData2(sCommentList);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{
				jsontodataset.setFlag(dataset.isFlag());
			}
		return jsontodataset;
	
	}
	
	
	
	
	/**
	 * ----------------------评论列表-------------------------------------------
	 */
	public JSONtoDataSet json2commentlist(JSONtoDataSet dataset) {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=new JSONObject();
			json=(JSONObject) dataset.getData();
			try {
				JSONArray commentarray=json.getJSONArray("SetTripComment");
				List<CommentEntity>commentlist=new ArrayList<CommentEntity>();
				for(int i=0;i<commentarray.length();i++){
					CommentEntity commententity=new CommentEntity(commentarray.getJSONObject(i));
					commentlist.add(commententity);
				}
				SetCommentListEntity sCommentList=new SetCommentListEntity(commentlist);
				jsontodataset.setData(sCommentList);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		return jsontodataset;
		
	}

	/**
	 * ----------------------接收图片-------------------------------------------
	 */
	public Bitmap json2bitmap(JSONtoDataSet dataset) { //这一段代码是没有用到的
		// TODO Auto-generated method stub
		Bitmap bitmap = null;
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=new JSONObject();
			json=(JSONObject) dataset.getData();
			
			try {
				JSONArray jsonarray=new JSONArray();
				jsonarray=json.getJSONArray("pic");
				for(int i=0;i<jsonarray.length();i++){
					//String  fileName = ((JSONObject)jsonarray.get(i)).getString("fileName");    //获得图片名字
	            	//JSONObject jj=new JSONObject();
	            	FileUploadRequest jj=(FileUploadRequest)jsonarray.get(i);
					byte [] bytes =  jj.getBytes();   //获得图片字节数组
	            	if(bytes.length!=0){
	            		//bitmap=BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	            		bitmap=LruCacheUtils.decodeSampledBitmapFromStream(bytes,100,100);
	            		
	            		
	            		}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}	
		return bitmap;
	}
	
	/**
	 * ----------------------登录-------------------------------------------
	 */

	public JSONtoDataSet json2userdetail(JSONtoDataSet dataset) {
		// TODO Auto-generated method stub
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject jsonobject=(JSONObject) dataset.getData();
			JSONObject json = null;
			try {
				json = jsonobject.getJSONObject("UserDetailInfo");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			UserEntity userentity=new UserEntity(json);
			jsontodataset.setData(userentity);
		}else{
			jsontodataset.setFlag(dataset.isFlag());
			try {
				String result;
				if(((JSONObject)dataset.getData()).has("String")){
					result = ((JSONObject)dataset.getData()).getString("String");
					jsontodataset.setData(result);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jsontodataset;
	}

	/**
	 * ----------------------我的导航行程列表-------------------------------------------
	 */
	public JSONtoDataSet json2navigationtripbrieflist(JSONtoDataSet dataset) {
		// TODO Auto-generated method stub
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		try {
			if(dataset.isFlag()){
				jsontodataset.setFlag(dataset.isFlag());
				JSONObject json=new JSONObject();
				json=(JSONObject) dataset.getData();
				JSONArray navigationtripbriefarray=(JSONArray) json.get("SetTripBriefInfo");
				List<TripBriefEntity>navigation=new ArrayList<TripBriefEntity>();
				for(int i=0;i<navigationtripbriefarray.length();i++){
					
					TripBriefEntity navigationtripbrief=new TripBriefEntity((JSONObject) navigationtripbriefarray.get(i));
					navigation.add(navigationtripbrief);
				}			
				SetTripBriefListEntity sNavigationTripBriefList=new SetTripBriefListEntity(navigation);
				jsontodataset.setData(sNavigationTripBriefList);
			}else{
				jsontodataset.setFlag(dataset.isFlag());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsontodataset;
	}
	
	/**
	 * ----------------------行程参与者列表-------------------------------------------
	 */
	public JSONtoDataSet json2participantslist(JSONtoDataSet dataset) {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=new JSONObject();
			json=(JSONObject) dataset.getData();
			try {
				JSONArray participantsarray=(JSONArray) json.get("SetTripParticipant");
				List<TripParticipantEntity>navigation=new ArrayList<TripParticipantEntity>();
				for(int i=0;i<participantsarray.length();i++){
					
					TripParticipantEntity navigationtripbrief=new TripParticipantEntity((JSONObject) participantsarray.get(i));
					navigation.add(navigationtripbrief);
				}			
				SetTripParticipantListEntity sTripParticipantList=new SetTripParticipantListEntity(navigation);
				jsontodataset.setData(sTripParticipantList);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		
		return jsontodataset;
		
	}
	
	/**
	 * ----------------------(导航)行程详情-------------------------------------------
	 */
	public JSONtoDataSet json2navigationtripdetails(JSONtoDataSet dataset) {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=new JSONObject();
			json=(JSONObject) dataset.getData();
			
			try {
				JSONObject tripdetail=new JSONObject();
				tripdetail=json.getJSONObject("TripDetailInfo");
				JSONArray planarray=new JSONArray();
				planarray=json.getJSONArray("SetTripPlan");
				
				TripEntitry tripentity=new TripEntitry(tripdetail);
				jsontodataset.setData(tripentity);
				
				List<TripPlanEntity>TripPlanList=new ArrayList<TripPlanEntity>();
				for(int i=0;i<planarray.length();i++){
					
					TripPlanEntity tripplanentity=new TripPlanEntity(planarray.getJSONObject(i));
					
					TripPlanList.add(tripplanentity);
				}	
				SetTripPlanListEntity sTripPlanList=new SetTripPlanListEntity(TripPlanList);
				jsontodataset.setData1(sTripPlanList);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{
				jsontodataset.setFlag(dataset.isFlag());
			}
		return jsontodataset;
		
	}
	
	/**
	 * ----------------------(导航)行程安排详情-------------------------------------------
	 */
	public JSONtoDataSet json2navigationtripplandetails(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=((JSONObject) dataset.getData()).getJSONObject("TripPlan");
			TripPlanEntity tripplan=new TripPlanEntity(json);
			jsontodataset.setData(tripplan);
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		return jsontodataset;
	}
	
	/**
	 * ----------------------导航轨迹与行走轨迹-------------------------------------------
	 */
	public JSONtoDataSet json2navigationtrack(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=(JSONObject) dataset.getData();
			JSONArray navigationarray=json.getJSONArray("SetTripNavigationTrack");
			JSONArray walkarray=json.getJSONArray("SetTripWalkTrack");
			List<TripNavigationTrackEntity>NavigationTrackList=new ArrayList<TripNavigationTrackEntity>();
			for(int i=0;i<navigationarray.length();i++){
				TripNavigationTrackEntity navigationtrackentity=new TripNavigationTrackEntity(navigationarray.getJSONObject(i));
				NavigationTrackList.add(navigationtrackentity);
			}
			SetTripNavigationTrackListEntity ListEntity=new SetTripNavigationTrackListEntity(NavigationTrackList);
			jsontodataset.setData(ListEntity);

			List<TripWalkTrackEntity>WalkTrackList=new ArrayList<TripWalkTrackEntity>();
			for(int i=0;i<walkarray.length();i++){
				TripWalkTrackEntity walktrackentity=new TripWalkTrackEntity(3,walkarray.getJSONObject(i));
				WalkTrackList.add(walktrackentity);
			}
			SetTripWalkTrackListEntity wListEntity=new SetTripWalkTrackListEntity(WalkTrackList);
			jsontodataset.setData1(wListEntity);
			
			
			
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		
		return jsontodataset;
	}
	
	/**
	 * ----------------------接收单个聊天信息-------------------------------------------
	 */
	public JSONtoDataSet json2chatinfo(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=(JSONObject) dataset.getData();
			TripWalkTrackEntity walkentity=new TripWalkTrackEntity(1,json);
			jsontodataset.setData(walkentity);
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		return jsontodataset;
	}
	

	/**
	 * ----------------------获取聊天信息列表-------------------------------------------
	 */
	public JSONtoDataSet json2chatinfolist(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=(JSONObject) dataset.getData();
			if(!json.has("String")){
			JSONArray array=json.getJSONArray("TripWalkList");
			List<TripWalkTrackEntity>chatlist=new ArrayList<TripWalkTrackEntity>();
			for(int i=0;i<array.length();i++){
				if(array.getJSONObject(i).getInt("TripWalkTrackType")==1){
				TripWalkTrackEntity chatinfo=new TripWalkTrackEntity(3,array.getJSONObject(i));
				chatlist.add(chatinfo);
				}
			}
			SetTripWalkTrackListEntity chatlistentity=new SetTripWalkTrackListEntity(chatlist);
			jsontodataset.setData(chatlistentity);
			}else{
				SetTripWalkTrackListEntity listentity=new SetTripWalkTrackListEntity();
				jsontodataset.setData(listentity);
			}
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		return jsontodataset;
	}
	
	/**
	 * ----------------------获取单个行走轨迹-------------------------------------------
	 */
	public JSONtoDataSet json2walktrackentity(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=(JSONObject) dataset.getData();
			TripWalkTrackEntity walktrack=new TripWalkTrackEntity(2,json);
			jsontodataset.setData(walktrack);
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		return jsontodataset;
	}
	
	/**
	 * ----------------------关注点详情-------------------------------------------
	 */
	public JSONtoDataSet json2foucsdetail(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=(JSONObject) dataset.getData();
			JSONArray array=json.getJSONArray("SetCheckInfo");
			FocusEntity foucsentity=new FocusEntity(json.getJSONObject("FoucsDetailInfo"));
			List<CheckInfoEntity>cheaklist=new ArrayList<CheckInfoEntity>();
			for(int i=0;i<array.length();i++){
				CheckInfoEntity cheakinfo=new CheckInfoEntity(array.getJSONObject(i));
				cheaklist.add(cheakinfo);
			}
			SetCheckInfoListEntity checkinfoentity=new SetCheckInfoListEntity(cheaklist);

			jsontodataset.setData(foucsentity);
			jsontodataset.setData1(checkinfoentity);
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		return jsontodataset;
	}

	/**
	 * ----------------------关注点类型-------------------------------------------
	 */
	public JSONtoDataSet json2foucstype(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONArray array=((JSONObject) dataset.getData()).getJSONArray("SetFoucsBriefInfo");
			List<FoucsType>list=new ArrayList<FoucsType>();
			for(int i=0;i<array.length();i++){
				FoucsType type=new FoucsType((JSONObject)array.get(i));
				list.add(type);
			}
			jsontodataset.setData(list);
			
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		return jsontodataset;
	}
	
	/**
	 * ----------------------获得附近关注点-------------------------------------------
	 **/
	public JSONtoDataSet json2nearbyfoucs(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=new JSONObject();
			json=(JSONObject) dataset.getData();
			JSONArray focusbriefarray=(JSONArray) json.get("SetFoucsBriefInfo");
			List<FocusBriefEntity>FocusBrieflist=new ArrayList<FocusBriefEntity>();
			for(int i=0;i<focusbriefarray.length();i++){
				
				FocusBriefEntity focusbrief=new FocusBriefEntity((JSONObject) focusbriefarray.get(i));
				FocusBrieflist.add(focusbrief);
			}			
			SetFocusBriefListEntity sFocusBriefList=new SetFocusBriefListEntity(FocusBrieflist);
			jsontodataset.setData(sFocusBriefList);

		}
		else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		return jsontodataset;
	}
	
	/**
	 * ----------------------获得注册结果-------------------------------------------
	 * @param dataset
	 * @return
	 * @throws JSONException
	 */
	public JSONtoDataSet json2register(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
		}else if(((JSONObject)dataset.getData()).has("String")){
			jsontodataset.setFlag(dataset.isFlag());
			jsontodataset.setData(((JSONObject)dataset.getData()).getString("String"));
			
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		return jsontodataset;
	}
	
	/**
	 * ----------------------获得搜索结果-------------------------------------------
	 * @param dataset
	 * @return
	 * @throws JSONException
	 */
	public JSONtoDataSet json2searchresult(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=new JSONObject();
			json=(JSONObject) dataset.getData();
			if(json.has("SetFoucsBriefInfo")){
				JSONArray focusbriefarray=(JSONArray) json.get("SetFoucsBriefInfo");
				List<FocusBriefEntity>FocusBrieflist=new ArrayList<FocusBriefEntity>();
				if(focusbriefarray.length()>0){
					for(int i=0;i<focusbriefarray.length();i++){
						
						FocusBriefEntity focusbrief=new FocusBriefEntity((JSONObject) focusbriefarray.get(i));
						FocusBrieflist.add(focusbrief);
					}			
					SetFocusBriefListEntity sFocusBriefList=new SetFocusBriefListEntity(FocusBrieflist);
					jsontodataset.setData(0);
					jsontodataset.setData1(sFocusBriefList);
				}else{
					jsontodataset.setData(-1);
				}
				
			}else if(json.has("SetTripBriefInfo")){
				JSONArray tripbriefarray=(JSONArray) json.get("SetTripBriefInfo");
				List<TripBriefEntity>TripBrieflist=new ArrayList<TripBriefEntity>();
				if(tripbriefarray.length()>0){
					for(int i=0;i<tripbriefarray.length();i++){
						
						TripBriefEntity tripbrief=new TripBriefEntity((JSONObject) tripbriefarray.get(i));
						TripBrieflist.add(tripbrief);
					}			
					SetTripBriefListEntity sTripBriefList=new SetTripBriefListEntity(TripBrieflist);
					jsontodataset.setData(1);
					jsontodataset.setData1(sTripBriefList);
				}else{
					jsontodataset.setData(-1);
				}
			}
			
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		return jsontodataset;
	}
	/**
	 * ---------------------根据城市名获取关注点list-------------------------------------------
	 * @param dataset
	 * @return
	 * @throws JSONException
	 */
	public JSONtoDataSet json2cityfocuslist(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=new JSONObject();
			json=(JSONObject) dataset.getData();
			JSONArray focusbriefarray=(JSONArray) json.get("SetFoucsBriefInfo");
			List<FocusBriefEntity>FocusBrieflist=new ArrayList<FocusBriefEntity>();
			for(int i=0;i<focusbriefarray.length();i++){
				
				FocusBriefEntity focusbrief=new FocusBriefEntity((JSONObject) focusbriefarray.get(i));
				FocusBrieflist.add(focusbrief);
			}			
			SetFocusBriefListEntity sFocusBriefList=new SetFocusBriefListEntity(FocusBrieflist);
			jsontodataset.setData(sFocusBriefList);

		}
		else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		return jsontodataset;
	}
	/**
	 * ---------------------我的收藏-------------------------------------------
	 * @param dataset
	 * @return
	 * @throws JSONException
	 */
	public JSONtoDataSet json2collectlist(JSONtoDataSet dataset) throws JSONException {
		JSONtoDataSet jsontodataset=new JSONtoDataSet();
		if(dataset.isFlag()){
			jsontodataset.setFlag(dataset.isFlag());
			JSONObject json=new JSONObject();
			json=(JSONObject) dataset.getData();
			JSONArray collectarray=(JSONArray) json.get("SetCollectInfo");
			if((Integer)((JSONObject)collectarray.get(0)).get("collectStyle")==1){
				List<FocusBriefEntity>FocusBrieflist=new ArrayList<FocusBriefEntity>();
				for(int i=0;i<collectarray.length();i++){
					
					FocusBriefEntity focusbrief=new FocusBriefEntity(1,(JSONObject) collectarray.get(i));
					FocusBrieflist.add(focusbrief);
				}			
				SetFocusBriefListEntity sFocusBriefList=new SetFocusBriefListEntity(FocusBrieflist);
				boolean t=true;
				jsontodataset.setData(t);
				jsontodataset.setData1(sFocusBriefList);
			}else{
				List<TripBriefEntity>TripBrieflist=new ArrayList<TripBriefEntity>();
				for(int i=0;i<collectarray.length();i++){
					
					TripBriefEntity tripbrief=new TripBriefEntity(1,(JSONObject) collectarray.get(i));
					TripBrieflist.add(tripbrief);
				}			
				SetTripBriefListEntity sTripBriefList=new SetTripBriefListEntity(TripBrieflist);
				boolean t=false;
				jsontodataset.setData(t);
				jsontodataset.setData2(sTripBriefList);
			}
			
		}else{
			jsontodataset.setFlag(dataset.isFlag());
		}
		
		return jsontodataset;
	}
	
}
