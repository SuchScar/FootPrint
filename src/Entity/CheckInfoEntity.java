package Entity;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckInfoEntity {

	private	int	CheckId	;//	踩点id
	private	String	CheckInfo	;//	踩点内容
	private	String	CheckPicture	;//	踩点图片
	private	int	CheckState	;//	踩点状态
	private	String	CheckTime	;//	踩点时间
	private	int	CheckGoodNumber	;//	踩点点赞数量
	private	int	FoucsId	;//	关注点id
	private	int	UserId	;//	用户id
	private String UserName;//	用户名字
	private String userimage="";//用户头像
	
	
	public CheckInfoEntity() {}
	
	public CheckInfoEntity(JSONObject json) throws JSONException {
		this.CheckId=json.getInt("checkId");
		this.CheckInfo=json.getString("checkInfo");
		this.CheckPicture=json.getString("checkPicture");
		this.CheckTime=json.getString("checkTime");
		this.CheckGoodNumber=json.getInt("checkGoodNumber");
		this.UserName=json.getString("checkCreateMan");
		this.userimage=json.getString("checkCreateManImage");
	}

	
	public JSONObject ToJSON(int tag,CheckInfoEntity entity) throws JSONException {
		JSONObject json=new JSONObject();
		JSONObject json1=new JSONObject();
		json.put("tag",tag);
		json.put("userId", entity.getUserId());
		json1.put("checkInfo", entity.getCheckInfo());
		json1.put("foucsId",entity.getFoucsId());
		
		json.put("jsonObject", json1);
		return json;
	}


	public String getUserimage() {
		return userimage;
	}

	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
	public int getCheckId() {
		return CheckId;
	}


	public void setCheckId(int checkId) {
		CheckId = checkId;
	}


	public String getCheckInfo() {
		return CheckInfo;
	}


	public void setCheckInfo(String checkInfo) {
		CheckInfo = checkInfo;
	}


	public String getCheckPicture() {
		return CheckPicture;
	}


	public void setCheckPicture(String checkPicture) {
		CheckPicture = checkPicture;
	}


	public int getCheckState() {
		return CheckState;
	}


	public void setCheckState(int checkState) {
		CheckState = checkState;
	}


	public String getCheckTime() {
		return CheckTime;
	}


	public void setCheckTime(String checkTime) {
		CheckTime = checkTime;
	}


	public int getCheckGoodNumber() {
		return CheckGoodNumber;
	}


	public void setCheckGoodNumber(int checkGoodNumber) {
		CheckGoodNumber = checkGoodNumber;
	}


	public int getFoucsId() {
		return FoucsId;
	}


	public void setFoucsId(int foucsId) {
		FoucsId = foucsId;
	}


	public int getUserId() {
		return UserId;
	}


	public void setUserId(int userId) {
		UserId = userId;
	}

}
