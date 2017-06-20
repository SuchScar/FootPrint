package Entity;

import org.json.JSONException;
import org.json.JSONObject;

public class TripBriefEntity {

	private int trip_id;        //行程id
	private String trip_title;     //行程标题
	private String trip_abstract;  //行程简洁
	private String trip_time;      //行程时间
	private String trip_author;    //行程创建者
	private String trip_image="";     //行程图片
	private String trip_state;      //行程状态
	private String collecttime;     //收藏时间
	private int collestid;          //收藏id
	
	public TripBriefEntity() {}
	public TripBriefEntity(int ti,String tt,String ta,String tti,String tau) {
		
		this.trip_id=ti;
		this.trip_title=tt;
		this.trip_abstract=ta;
		this.trip_time=tti;
		this.trip_author=tau;
	}
	

	public TripBriefEntity(JSONObject jsonObject) {
		// TODO Auto-generated constructor stub
		try {
			this.trip_id=jsonObject.getInt("tripId");
			this.trip_title=jsonObject.getString("tripName");
			this.trip_abstract=jsonObject.getString("TripSummary");
			if(jsonObject.has("TripReleaseTime")){
				this.trip_time=jsonObject.getString("TripReleaseTime");
			}
			
			this.trip_author=jsonObject.getString("TripCreateMan");
			this.trip_image=jsonObject.getString("TripImage");
			this.trip_state=jsonObject.getString("TripState");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	}
	public TripBriefEntity(int i,JSONObject jsonObject) {
		// TODO Auto-generated constructor stub
		try {
			this.collestid=jsonObject.getInt("collectId");
			this.collecttime=jsonObject.getString("collectTime");
			this.trip_id=jsonObject.getInt("collectStyleId");
			this.trip_title=jsonObject.getString("collectName");
			this.trip_abstract=jsonObject.getString("collectSummary");
			if(jsonObject.has("collectTime")){
				this.trip_time=jsonObject.getString("collectTime");
			}
			
			//this.trip_author=jsonObject.getString("TripCreateMan");
			this.trip_image=jsonObject.getString("collectImage");
			//this.trip_state=jsonObject.getString("TripState");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	}

	public JSONObject ToJSON(int tag,int page) {
		JSONObject json=new JSONObject();
		try {
			json.put("tag", tag);
			json.put("page", page);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
		
	}
	
	public JSONObject ToJSON(int tag,int page,int userId) {
		// TODO Auto-generated method stub
    	JSONObject json=new JSONObject();
    	try {
			json.put("tag", tag);
			json.put("page", page);
			json.put("userId", userId);
//			json.put("tripState", tripState);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
		return json;
	}
	
	








	public String getTrip_state() {
		return trip_state;
	}
	public void setTrip_state(String trip_state) {
		this.trip_state = trip_state;
	}
	public int getTrip_id() {
		return trip_id;
	}

	public String getCollecttime() {
		return collecttime;
	}
	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}
	public int getCollestid() {
		return collestid;
	}
	public void setCollestid(int collestid) {
		this.collestid = collestid;
	}
	public void setTrip_id(int trip_id) {
		this.trip_id = trip_id;
	}

	public String getTrip_title() {
		return trip_title;
	}

	public void setTrip_title(String trip_title) {
		this.trip_title = trip_title;
	}

	public String getTrip_abstract() {
		return trip_abstract;
	}

	public void setTrip_abstract(String trip_abstract) {
		this.trip_abstract = trip_abstract;
	}

	public String getTrip_time() {
		return trip_time;
	}

	public void setTrip_time(String trip_time) {
		this.trip_time = trip_time;
	}

	public String getTrip_author() {
		return trip_author;
	}

	public void setTrip_author(String trip_author) {
		this.trip_author = trip_author;
	}


	public String getTrip_image() {
		return trip_image;
	}


	public void setTrip_image(String trip_image) {
		this.trip_image = trip_image;
	}
	
	
	
}
