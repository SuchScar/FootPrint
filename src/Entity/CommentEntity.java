package Entity;

import org.json.JSONException;
import org.json.JSONObject;

public class CommentEntity {

	private int CommentId;         //评论id
	private String CommentInfo;    //评论内容
	private int CommentRank;    //评论星级
	private int UserId;         //用户id
	private String Username;        //用户昵称
	private String UserImage;         //用户头像
	private int TripId;         //行程id
	private String CommentTime;
	
	public CommentEntity() {}

	public CommentEntity(int id,String ci,String ct,String un,String img) {
		this.CommentId=id;
		this.CommentInfo=ci;
		this.CommentTime=ct;
		this.Username=un;
		this.UserImage=img;
		
	}
	
	public CommentEntity(JSONObject json){
		try {
			this.CommentId=json.getInt("tripCommentId");
			this.CommentRank=json.getInt("tripCommentRank");
			this.UserId=json.getInt("userId");
			this.UserImage=json.getString("userImage");
			this.Username=json.getString("userName");
			this.CommentInfo=json.getString("tripCommentInfo");
			this.TripId=json.getInt("TripId");
			this.CommentTime=json.getString("tripCommentTime");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public JSONObject ToJSON(int tag,int tripid,int page){
		JSONObject json=new JSONObject();
		try {
			json.put("tag", tag);
			json.put("tripId", tripid);
			json.put("page", page);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return json;
	}
	
	public JSONObject ToJSON(int tag,int userid,CommentEntity commententity){
		JSONObject json=new JSONObject();
		try {
			json.put("tag", tag);
			json.put("userId", userid);
			JSONObject jsoncomment=new JSONObject();
			jsoncomment.put("TripId", commententity.getTripId());
			jsoncomment.put("tripCommentInfo", commententity.getCommentInfo());
			jsoncomment.put("tripCommentRank", commententity.getCommentRank());
			json.put("comment", jsoncomment);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return json;
	}
	
	
	
	
	
	public String getCommentTime() {
		return CommentTime;
	}

	public void setCommentTime(String commentTime) {
		CommentTime = commentTime;
	}

	public int getCommentId() {
		return CommentId;
	}

	public void setCommentId(int commentId) {
		CommentId = commentId;
	}

	public String getCommentInfo() {
		return CommentInfo;
	}

	public void setCommentInfo(String commentInfo) {
		CommentInfo = commentInfo;
	}

	public int getCommentRank() {
		return CommentRank;
	}

	public void setCommentRank(int commentRank) {
		CommentRank = commentRank;
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
	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}
	public String getUserImage() {
		return UserImage;
	}
	public void setUserImage(String userImage) {
		UserImage = userImage;
	}
	
	
	
	
}
