package Entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class TripWalkTrackEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -958955084370004265L;
	private	int	TripWalkTrackId	;//	行程行走轨迹Id
	private	String	TripWalkTrackName	;//	行程行走轨迹名字(给别人的一条提示)
	private	double	TripWalkTrackLon	;//	行程行走轨迹经度
	private	double	TripWalkTrackLat	;//	行程行走轨迹纬度
	private	String	TripWalkTrackImage	;//	行程行走轨迹图片
	private	String	TripWalkTrackContent;//	行程行走轨迹内容
	private	String	TripWalkTrackTime	;//	行程行走轨迹发表时间
	private	int	TripWalkTrackType	;//	行程行走轨迹类型
	private	int	TripWalkTrackState	;//	行程行走轨迹消息状态  集合点 紧急状况点
	private	String	TripInfoComeFrom	;//	行程消息来源渠道
	private	int	TripId	;//	行程id
	private	int	TripPlanId	;//	行程内容Id
	private	int	tripParticipantId	;//	行程参与者id
	
	private int userid;
	private String username;
	
	public TripWalkTrackEntity() {}
	
	public TripWalkTrackEntity(int wti,String wtn,double wtlat,double wtlon,String wtimage,String wtc,String wtt) {
		
		this.TripWalkTrackId=wti;
		this.TripWalkTrackName=wtn;
		this.TripWalkTrackLon=wtlon;
		this.TripWalkTrackLat=wtlat;
		this.TripWalkTrackImage=wtimage;
		this.TripWalkTrackContent=wtc;
		this.TripWalkTrackTime=wtt;
	}
//	public TripWalkTrackEntity(JSONObject json) throws JSONException{
//		this.TripWalkTrackId=json.getInt("TripWalkTrackId");
//		if(!json.getString("TripWalkTrackLon").equals("null")){
//		this.TripWalkTrackLon=json.getDouble("TripWalkTrackLon");
//		
//		}
//		if(!json.getString("TripWalkTrackLat").equals("null")){
//		this.TripWalkTrackLat=json.getDouble("TripWalkTrackLat");
//		}
//		this.TripWalkTrackContent=json.getString("TripWalkTrackContent");
//		this.TripWalkTrackTime=json.getString("TripWalkTrackTime");
//		this.username=json.getString("userNickName");
//		this.tripParticipantId=json.getInt("tripParticipantId");
//	}
	public TripWalkTrackEntity(int i,JSONObject json) throws JSONException{
		if(i==3){//行走轨迹列表
			this.TripWalkTrackId=json.getInt("TripWalkTrackId");
			if(!json.getString("TripWalkTrackLon").equals("null")){
			this.TripWalkTrackLon=json.getDouble("TripWalkTrackLon");
			
			}
			if(!json.getString("TripWalkTrackLat").equals("null")){
			this.TripWalkTrackLat=json.getDouble("TripWalkTrackLat");
			}
			this.TripWalkTrackContent=json.getString("TripWalkTrackContent");
			this.TripWalkTrackTime=json.getString("TripWalkTrackTime");
			this.username=json.getString("userNickName");
			this.tripParticipantId=json.getInt("tripParticipantId");
		}
		else if(i==1){//聊天
				this.userid=json.getInt("tripParticipantId");
				this.TripId=json.getInt("tripId");
				this.TripPlanId=json.getInt("tripPlanId");
				this.username=json.getString("name");
				this.TripWalkTrackContent=json.getString("word");
			
		}else if(i==2){//单个行走轨迹
			JSONObject json2=new JSONObject();
			json2=json.getJSONObject("message");
			this.username=json.getString("name");
			this.TripId=json.getInt("tripId");
			this.TripPlanId=json.getInt("tripPlanId");
			this.TripWalkTrackTime=json.getString("time");
			this.TripWalkTrackName=json2.getString("TripWalkTrackName");
			this.TripWalkTrackLat=json2.getDouble("TripWalkTrackLat");
			this.TripWalkTrackLon=json2.getDouble("TripWalkTrackLon");
			this.TripWalkTrackContent=json2.getString("TripWalkTrackContent");
			this.tripParticipantId=json.getInt("tripParticipantId");
		}
	}
	
	
	
	
	
	public JSONObject ToJSON(int tag,int type,int tripplanid,TripWalkTrackEntity walktrack) throws JSONException{
		JSONObject json=new JSONObject();
		JSONObject json1=new JSONObject();
		json.put("tag", tag);
		json.put("tripPlanId", tripplanid);
		json.put("TripWalkTrackType", type);
		//if(json.has("TripWalkTrackName")){
		json1.put("TripWalkTrackName", walktrack.getTripWalkTrackName());
		//}
		//if(json.has("TripWalkTrackLon")){
		json1.put("TripWalkTrackLon", walktrack.getTripWalkTrackLon());
		//}
		//if(json.has("TripWalkTrackLat")){
		json1.put("TripWalkTrackLat", walktrack.getTripWalkTrackLat());
		//}
		json1.put("TripWalkTrackContent", walktrack.getTripWalkTrackContent());
		//json1.put("tripId", walktrack.getTripId());
		json1.put("userId", walktrack.getUserid());
		
		json.put("tripId", walktrack.getTripId());
		json.put("tripTack", json1);
		return json;
	}
	
	public JSONObject ToJSON(int tag,int tripplanid,int userid) throws JSONException{
		JSONObject json=new JSONObject();
		json.put("tag", tag);
		json.put("tripPlanId", tripplanid);
		json.put("userId", userid);
		
		return json;
	}
	

	public int getTripWalkTrackState() {
		return TripWalkTrackState;
	}

	public void setTripWalkTrackState(int tripWalkTrackState) {
		TripWalkTrackState = tripWalkTrackState;
	}
	public int getTripWalkTrackId() {
		return TripWalkTrackId;
	}

	public void setTripWalkTrackId(int tripWalkTrackId) {
		TripWalkTrackId = tripWalkTrackId;
	}

	public String getTripWalkTrackName() {
		return TripWalkTrackName;
	}

	public void setTripWalkTrackName(String tripWalkTrackName) {
		TripWalkTrackName = tripWalkTrackName;
	}

	public double getTripWalkTrackLon() {
		return TripWalkTrackLon;
	}

	public void setTripWalkTrackLon(double tripWalkTrackLon) {
		TripWalkTrackLon = tripWalkTrackLon;
	}

	public double getTripWalkTrackLat() {
		return TripWalkTrackLat;
	}

	public void setTripWalkTrackLat(double tripWalkTrackLat) {
		TripWalkTrackLat = tripWalkTrackLat;
	}

	public String getTripWalkTrackImage() {
		return TripWalkTrackImage;
	}

	public void setTripWalkTrackImage(String tripWalkTrackImage) {
		TripWalkTrackImage = tripWalkTrackImage;
	}

	public String getTripWalkTrackContent() {
		return TripWalkTrackContent;
	}

	public void setTripWalkTrackContent(String tripWalkTrackContent) {
		TripWalkTrackContent = tripWalkTrackContent;
	}

	public String getTripWalkTrackTime() {
		return TripWalkTrackTime;
	}

	public void setTripWalkTrackTime(String tripWalkTrackTime) {
		TripWalkTrackTime = tripWalkTrackTime;
	}

	public int getTripWalkTrackType() {
		return TripWalkTrackType;
	}

	public void setTripWalkTrackType(int tripWalkTrackState) {
		TripWalkTrackType = tripWalkTrackState;
	}

	public String getTripInfoComeFrom() {
		return TripInfoComeFrom;
	}

	public void setTripInfoComeFrom(String tripInfoComeFrom) {
		TripInfoComeFrom = tripInfoComeFrom;
	}

	public int getTripId() {
		return TripId;
	}

	public void setTripId(int tripId) {
		TripId = tripId;
	}

	public int getTripPlanId() {
		return TripPlanId;
	}

	public void setTripPlanId(int tripPlanId) {
		TripPlanId = tripPlanId;
	}

	public int getTripParticipantId() {
		return tripParticipantId;
	}

	public void setTripParticipantId(int tripParticipantId) {
		this.tripParticipantId = tripParticipantId;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
