package Entity;

import org.json.JSONException;
import org.json.JSONObject;

import com.Bll.MinaSocket;

public class TripPlanEntity {

	private	int	TripPlanId	;//	行程内容Id
	private	String	TripPlanBrief	;//	行程内容概要（标题）
	private	String	TripMainPlan	;//	行程内容主要规划
	private	int	TripId	;//	行程id
	private	int	TripParticipantId	;//	行程参与者id
	private String TripParticipantName;//参与者昵称
	private int FocusId;             //关注点id
	private String FocusTitle;              //关注点标题
	private String FocusPicture;         //关注点封面图片
	private String Focusorder;         //关注点顺序
	private String TripFocusTime;         //关注点顺序时间
	
	
	public TripPlanEntity(){}
	
	public TripPlanEntity(int tpi,String tpb,String tmp,String ft,String tpn,String fp) {
		this.TripPlanId=tpi;
		this.TripPlanBrief=tpb;
		this.TripMainPlan=tmp;
		this.FocusTitle=ft;
		this.TripParticipantName=tpn;
		this.FocusPicture=fp;
	}

	public TripPlanEntity(JSONObject json){
		try {
			this.TripPlanId=json.getInt("TripPlanId");
			this.FocusId=json.getInt("foucsId");
			this.TripId=json.getInt("TripId");
			this.TripParticipantId=json.getInt("tripParticipantId");
			if(json.has("UserNickname")){
				this.TripParticipantName=json.getString("UserNickname");
			}
			this.FocusTitle=json.getString("TripFoucsName");
			this.TripFocusTime=json.getString("TripFoucsTime");
			this.FocusPicture=json.getString("TripFoucsImage");
			if(json.has("TripPlanBrief")){
				this.TripPlanBrief=json.getString("TripPlanBrief");
			}
			if(json.has("TripMainPlan")){
				this.TripMainPlan=json.getString("TripMainPlan");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public JSONObject ToJSON(int tag,TripPlanEntity tripplanentity){
		JSONObject json=new JSONObject();
		try {
			JSONObject jsontripplan=new JSONObject();
			jsontripplan.put("TripPlanBrief", tripplanentity.getTripPlanBrief());
			jsontripplan.put("TripMainPlan", tripplanentity.getTripMainPlan());
			json.put("tag", tag);
			json.put("tripPlanId", tripplanentity.getTripPlanId());
			json.put("jsonObject", jsontripplan);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public JSONObject ToJSON(int tag,int tripplanid){
		JSONObject json=new JSONObject();
		try {
			json.put("tag", tag);
			json.put("tripPlanId", tripplanid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return json;
		
	}
	
	public String getTripParticipantName() {
		return TripParticipantName;
	}
	public void setTripParticipantName(String tripParticipantName) {
		TripParticipantName = tripParticipantName;
	}
	public int getTripPlanId() {
		return TripPlanId;
	}

	public void setTripPlanId(int tripPlanId) {
		TripPlanId = tripPlanId;
	}

	public String getTripPlanBrief() {
		return TripPlanBrief;
	}

	public void setTripPlanBrief(String tripPlanBrief) {
		TripPlanBrief = tripPlanBrief;
	}

	public String getTripMainPlan() {
		return TripMainPlan;
	}

	public void setTripMainPlan(String tripMainPlan) {
		TripMainPlan = tripMainPlan;
	}

	public int getTripId() {
		return TripId;
	}

	public void setTripId(int tripId) {
		TripId = tripId;
	}

	public int getTripParticipantId() {
		return TripParticipantId;
	}

	public void setTripParticipantId(int tripParticipantId) {
		TripParticipantId = tripParticipantId;
	}

	public int getFocusId() {
		return FocusId;
	}

	public void setFocusId(int focusId) {
		FocusId = focusId;
	}

	public String getFocusTitle() {
		return FocusTitle;
	}

	public void setFocusTitle(String focusTitle) {
		FocusTitle = focusTitle;
	}

	public String getFocusPicture() {
		return FocusPicture;
	}

	public void setFocusPicture(String focusPicture) {
		FocusPicture = focusPicture;
	}

	public String getFocusorder() {
		return Focusorder;
	}

	public void setFocusorder(String focusorder) {
		Focusorder = focusorder;
	}

	public String getTripFocusTime() {
		return TripFocusTime;
	}

	public void setTripFocusTime(String tripFocusTime) {
		TripFocusTime = tripFocusTime;
	}
	
	
	
	
	
}
