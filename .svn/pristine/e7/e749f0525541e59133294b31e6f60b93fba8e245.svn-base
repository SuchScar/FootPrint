package Entity;

import org.json.JSONException;
import org.json.JSONObject;

public class FocusBriefEntity {

	private int focus_id;        //关注点id
	private String focus_title;     //关注点标题
	private String focus_abstract;  //关注点简洁
	private String focus_author;    //关注点创建者
	private String focus_address;   //关注点地址
	private String focus_image="";     //关注点图片
	private String collecttime;     //收藏时间
	private int collestid;          //收藏id
	
//	public Focus(){
//      super();
//	}
//	
	public FocusBriefEntity(int fi,String ft,String fa, String fau,String fad){
		//super();
		this.focus_id=fi;
		this.focus_title = ft;
		this.focus_abstract = fa;
		this.focus_author = fau;
		this.focus_address=fad;
		}
	
	public FocusBriefEntity(JSONObject json){
		//super();
		try {
			this.focus_id=json.getInt("foucsId");
			this.focus_title = json.getString("foucsName");
			if(json.has("foucsSummary")){
			this.focus_abstract = json.getString("foucsSummary");}
			if(json.has("foucsCreateMan")){
			this.focus_author = json.getString("foucsCreateMan");}
			if(json.has("foucsCity")){
			this.focus_address=json.getString("foucsCity");}
			if(json.has("foucsImage")){
			this.focus_image=json.getString("foucsImage");}
			if(json.has("foucsAdress")){
				this.focus_address=json.getString("foucsAdress");
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	public FocusBriefEntity(int i,JSONObject json) throws JSONException{
		this.collestid=json.getInt("collectId");
		this.focus_id=json.getInt("collectStyleId");
		this.focus_title = json.getString("collectName");
		this.collecttime=json.getString("collectTime");
		if(json.has("collectSummary")){
		this.focus_abstract = json.getString("collectSummary");}
		if(json.has("foucsCreateMan")){
		this.focus_author = json.getString("foucsCreateMan");}
		if(json.has("foucsCity")){
		this.focus_address=json.getString("foucsCity");}
		if(json.has("collectImage")){
		this.focus_image=json.getString("collectImage");}
		if(json.has("foucsAdress")){
			this.focus_address=json.getString("foucsAdress");
		}
		
		
		
	}

	public String getFocus_image() {
		return focus_image;
	}

	public void setFocus_image(String focus_image) {
		this.focus_image = focus_image;
	}

	public int getCollestid() {
		return collestid;
	}

	public void setCollestid(int collestid) {
		this.collestid = collestid;
	}

	public int getFocus_id() {
		return focus_id;
	}

	public String getCollecttime() {
		return collecttime;
	}

	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}

	public String getFocus_title() {
		return focus_title;
	}

	public void setFocus_title(String focus_title) {
		this.focus_title = focus_title;
	}

	public String getFocus_abstract() {
		return focus_abstract;
	}

	public void setFocus_abstract(String focus_abstract) {
		this.focus_abstract = focus_abstract;
	}

	public String getFocus_author() {
		return focus_author;
	}

	public void setFocus_author(String focus_author) {
		this.focus_author = focus_author;
	}

	public String getFocus_address() {
		return focus_address;
	}

	public void setFocus_address(String focus_address) {
		this.focus_address = focus_address;
	}

	public void setFocus_id(int focus_id) {
		this.focus_id = focus_id;
	}
	
	
}
