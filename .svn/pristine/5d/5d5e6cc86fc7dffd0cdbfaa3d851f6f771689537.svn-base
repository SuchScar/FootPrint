package Entity;

import org.json.JSONException;
import org.json.JSONObject;


public class FocusEntity {

	private int FocusId;                 //关注点id
	private String FocusTitle;           //关注点标题
	private String FocusBrief;           //关注点简介
	private String FocusTopPicture;      //关注点封面图片
	private String FocusCreateTime;      //关注点创建时间
	private int FocusCreateUserId;    //关注点创建者id
	private String FocusCreateUserName;  //关注点创建者昵称
	private String FocusAddress;         //关注点地址
	private double FocusLongitude;       //经度
	private double FocusLatitude;        //纬度
	private String FocusCity;            //关注点城市
	private String FocusStyle;           //关注点风格
	private String FocusType;            //关注点类型
	private	String FoucsLogo	;        //关注点周围标志物
	private	String FoucsLogoPicture	;    //关注点周围标志物图片
	private	String FoucsModifyMan	;    //	关注点修改人
	private	String FoucsModifyTime	;    //	关注点修改时间
	private	int    FoucsState	;        //	关注点状态
	private String FoucsContent;         //关注点内容
	private int TopFoucs;                //上一级关注点
	private String TopFoucsname;                //上一级关注点名称
	
	public FocusEntity() {}

	public FocusEntity(JSONObject json){
		try {
			this.FocusTitle=json.getString("foucsName");
			this.FocusTopPicture=json.getString("foucsImage");
			if(json.has("foucsInfo")){
				this.FoucsContent=json.getString("foucsInfo");
			}
			this.FocusAddress=json.getString("foucsAdress");
			this.FocusCreateUserName=json.getString("foucsCreateMan");
			this.FocusCreateTime=json.getString("foucsCreateTime");
			this.FocusType=json.getString("foucsType");
			this.FocusLongitude=json.getDouble("foucslong");
			this.FocusLatitude=json.getDouble("foucsLat");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public JSONObject ToJSON(int tag,int foucs,int userid) throws JSONException{
		JSONObject json=new JSONObject();
		json.put("focusId", foucs);
		json.put("tag", tag);
		json.put("userId", userid);
		return json;
		
	}
	
	public JSONObject ToJSON(int tag,FocusEntity entity) throws JSONException{
		JSONObject json=new JSONObject();
		JSONObject json1=new JSONObject();
		json.put("tag", tag);
		json.put("userId", entity.getFocusCreateUserId());
		
		json1.put("foucsInfo",entity.getFoucsContent());
		json1.put("foucsType", entity.getFocusType());
		json1.put("foucsStyle", entity.getFocusStyle());
		json1.put("foucslong", entity.getFocusLongitude());
		json1.put("foucsLat", entity.getFocusLatitude());
		json1.put("foucsAdress", entity.getFocusAddress());
		json1.put("foucsCity", entity.getFocusCity());
		json1.put("foucsName", entity.getFocusTitle());
		json1.put("bigFocusId", entity.getTopFoucs());
		json1.put("bigFocusName", entity.getTopFoucsname());
		json.put("jsonObject", json1);
		return json;
	}
	


	public String getFoucsContent() {
		return FoucsContent;
	}

	public void setFoucsContent(String foucsContent) {
		FoucsContent = foucsContent;
	}

	public int getFocusId() {
		return FocusId;
	}

	public String getFoucsModifyMan() {
		return FoucsModifyMan;
	}
	
	public void setFoucsModifyMan(String foucsModifyMan) {
		FoucsModifyMan = foucsModifyMan;
	}

	public String getFoucsModifyTime() {
		return FoucsModifyTime;
	}

	public int getTopFoucs() {
		return TopFoucs;
	}

	public void setTopFoucs(int topFoucs) {
		TopFoucs = topFoucs;
	}

	public String getTopFoucsname() {
		return TopFoucsname;
	}

	public void setTopFoucsname(String topFoucsname) {
		TopFoucsname = topFoucsname;
	}

	public void setFoucsModifyTime(String foucsModifyTime) {
		FoucsModifyTime = foucsModifyTime;
	}
	
	public int getFoucsState() {
		return FoucsState;
	}

	public void setFoucsState(int foucsState) {
		FoucsState = foucsState;
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

	public String getFocusBrief() {
		return FocusBrief;
	}

	public void setFocusBrief(String focusBrief) {
		FocusBrief = focusBrief;
	}

	public String getFocusTopPicture() {
		return FocusTopPicture;
	}

	public void setFocusTopPicture(String focusTopPicture) {
		FocusTopPicture = focusTopPicture;
	}

	public String getFocusCreateTime() {
		return FocusCreateTime;
	}

	public void setFocusCreateTime(String focusCreateTime) {
		FocusCreateTime = focusCreateTime;
	}

	public int getFocusCreateUserId() {
		return FocusCreateUserId;
	}

	public void setFocusCreateUserId(int focusCreateUserId) {
		FocusCreateUserId = focusCreateUserId;
	}

	public String getFocusCreateUserName() {
		return FocusCreateUserName;
	}

	public void setFocusCreateUserName(String focusCreateUserName) {
		FocusCreateUserName = focusCreateUserName;
	}

	public String getFocusAddress() {
		return FocusAddress;
	}

	public void setFocusAddress(String focusAddress) {
		FocusAddress = focusAddress;
	}

	public double getFocusLongitude() {
		return FocusLongitude;
	}

	public void setFocusLongitude(double focusLongitude) {
		FocusLongitude = focusLongitude;
	}

	public double getFocusLatitude() {
		return FocusLatitude;
	}

	public void setFocusLatitude(double focusLatitude) {
		FocusLatitude = focusLatitude;
	}

	public String getFocusCity() {
		return FocusCity;
	}

	public void setFocusCity(String focusCity) {
		FocusCity = focusCity;
	}

	public String getFocusStyle() {
		return FocusStyle;
	}

	public void setFocusStyle(String focusStyle) {
		FocusStyle = focusStyle;
	}

	public String getFocusType() {
		return FocusType;
	}

	public void setFocusType(String focusType) {
		FocusType = focusType;
	}

	public String getFoucsLogo() {
		return FoucsLogo;
	}

	public void setFoucsLogo(String foucsLogo) {
		FoucsLogo = foucsLogo;
	}

	public String getFoucsLogoPicture() {
		return FoucsLogoPicture;
	}

	public void setFoucsLogoPicture(String foucsLogoPicture) {
		FoucsLogoPicture = foucsLogoPicture;
	}

}
