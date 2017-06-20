package Entity;

import org.json.JSONException;
import org.json.JSONObject;

public class TripEntitry {

	private int TripId;   //行程id
	private String TripTitle;  //行程标题
	private String TripImage; //行程封面图片
	private String TripSummary; //行程概述 
	private String TripCreateUser; //行程创建人
	private String TripCreateTime; //行程创建时间
	private String TripReleaseTime;  //行程发布时间
	private int TripState; //行程发布状态
	
	public TripEntitry() {}

	public TripEntitry(JSONObject json) {
		
		try {
			this.TripId=json.getInt("tripId");
			if(json.has("tripReleaseTime")){
			this.TripReleaseTime=json.getString("tripReleaseTime");
			}
			this.TripCreateUser=json.getString("tripCreateMan");
			this.TripTitle=json.getString("tripName");
			this.TripSummary=json.getString("tripSummary");
			this.TripCreateTime=json.getString("tripCreateTime");
			if(json.has("tripImage")){
			this.TripImage=json.getString("tripImage");
			}
			this.TripState=json.getInt("tripState");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
    public JSONObject ToJSON(int tag,int TripId,int userid) {
		JSONObject json=new JSONObject();
		try {
			json.put("tag", tag);
			json.put("tripId", TripId);
			json.put("userId", userid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return json;
	}
	
	
	
	public int getTripId() {
		return TripId;
	}

	public void setTripId(int tripId) {
		TripId = tripId;
	}

	public String getTripTitle() {
		return TripTitle;
	}

	public void setTripTitle(String tripTitle) {
		TripTitle = tripTitle;
	}

	public String getTripImage() {
		return TripImage;
	}

	public void setTripImage(String tripImage) {
		TripImage = tripImage;
	}

	public String getTripSummary() {
		return TripSummary;
	}

	public void setTripSummary(String tripSummary) {
		TripSummary = tripSummary;
	}

	public String getTripCreateUser() {
		return TripCreateUser;
	}

	public void setTripCreateUser(String tripCreateUser) {
		TripCreateUser = tripCreateUser;
	}

	public String getTripCreateTime() {
		return TripCreateTime;
	}

	public void setTripCreateTime(String tripCreateTime) {
		TripCreateTime = tripCreateTime;
	}

	public String getTripReleaseTime() {
		return TripReleaseTime;
	}

	public void setTripReleaseTime(String tripReleaseTime) {
		TripReleaseTime = tripReleaseTime;
	}

	public int getTripState() {
		return TripState;
	}

	public void setTripState(int tripState) {
		TripState = tripState;
	}
	
	
	
	
}
