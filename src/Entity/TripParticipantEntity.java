package Entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class TripParticipantEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2575198925544629784L;
	private	int	TripParticipantId	;//	行程参与者id
	private	String	UserNickname	;//	用户昵称
	private	int	UserId	;//	用户id
	private	int	TripId	;//	行程id
	
	public TripParticipantEntity() {}

	public TripParticipantEntity(JSONObject json) {
		try {
			this.TripParticipantId=json.getInt("tripParticipantId");
			this.TripId=json.getInt("tripId");
			this.UserId=json.getInt("userId");
			this.UserNickname=json.getString("UserNickname");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public JSONObject ToJSON(int tag,int tripId){
		JSONObject json=new JSONObject();
		try {
			json.put("tag", tag);
			json.put("tripId", tripId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
		
	}
	
	
	
	
	
	
	public int getTripParticipantId() {
		return TripParticipantId;
	}

	public void setTripParticipantId(int tripParticipantId) {
		TripParticipantId = tripParticipantId;
	}

	public String getUserNickname() {
		return UserNickname;
	}

	public void setUserNickname(String userNickname) {
		UserNickname = userNickname;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getTripId() {
		return TripId;
	}

	public void setTripId(int tripId) {
		TripId = tripId;
	}

}
