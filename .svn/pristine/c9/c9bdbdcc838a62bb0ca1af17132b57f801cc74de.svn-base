package Entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class TripNavigationTrackEntity implements Serializable {

	private static final long serialVersionUID = 7539686469346772286L;
	private	int	TripNavigationTrackId	;//	行程导航轨迹id
	private	double	TripNavigationTracklon	;//	行程导航轨迹经度
	private	double	TripNavigationTracklat	;//	行程导航轨迹纬度
	private	int	TripPlanId	;//	行程内容id
	private	int	TripId	;//	行程id
	private int FocusId; //关注点id
	
	
	private String FocusName;
	private String FocusImage="";
	private String FocusSummsry;
	private String FocusCreateMan;
	private String FocusCreateManPic;
	private String FocusCity;
	
	
	
	
	public TripNavigationTrackEntity() {}	
	
	public TripNavigationTrackEntity(int nti,double ntlat,double ntlon,int fi) {

		this.TripNavigationTrackId=nti;
		this.TripNavigationTracklon=ntlon;
		this.TripNavigationTracklat=ntlat;
		this.FocusId=fi;
	}
	public TripNavigationTrackEntity(JSONObject json) throws JSONException {
		
		
		this.TripNavigationTrackId=json.getInt("TripNavigationTrackId");
		this.TripNavigationTracklon=json.getDouble("TripNavigationTracklon");
		this.TripNavigationTracklat=json.getDouble("TripNavigationTracklat");
		this.FocusId=json.getInt("foucsId");
		this.FocusName=json.getString("foucsName");
		this.FocusImage=json.getString("foucsImage");
		if(json.has("foucsSummary")){
			this.FocusSummsry=json.getString("foucsSummary");
		}
		this.FocusCreateMan=json.getString("foucsCreateMan");
		this.FocusCreateManPic=json.getString("foucsCreatePicture");
		this.FocusCity=json.getString("foucsCity");

	}
	
	
	
	public JSONObject ToJSON(int tag,int tripplanid) throws JSONException{
		JSONObject json=new JSONObject();
		json.put("tag", tag);
		json.put("tripPlanId", tripplanid);

		return json;

	}
	
	
	
	
	
	
	public int getTripNavigationTrackId() {
		return TripNavigationTrackId;
	}
	public void setTripNavigationTrackId(int tripNavigationTrackId) {
		TripNavigationTrackId = tripNavigationTrackId;
	}
	public double getTripNavigationTracklon() {
		return TripNavigationTracklon;
	}
	public void setTripNavigationTracklon(double tripNavigationTracklon) {
		TripNavigationTracklon = tripNavigationTracklon;
	}
	public double getTripNavigationTracklat() {
		return TripNavigationTracklat;
	}
	public void setTripNavigationTracklat(double tripNavigationTracklat) {
		TripNavigationTracklat = tripNavigationTracklat;
	}
	public int getTripPlanId() {
		return TripPlanId;
	}
	public void setTripPlanId(int tripPlanId) {
		TripPlanId = tripPlanId;
	}
	public int getTripId() {
		return TripId;
	}
	public void setTripId(int tripId) {
		TripId = tripId;
	}
	public int getFocusId() {
		return FocusId;
	}
	public void setFocusId(int focusId) {
		FocusId = focusId;
	}

	public String getFocusName() {
		return FocusName;
	}

	public void setFocusName(String focusName) {
		FocusName = focusName;
	}

	public String getFocusImage() {
		return FocusImage;
	}

	public void setFocusImage(String focusImage) {
		FocusImage = focusImage;
	}

	public String getFocusSummsry() {
		return FocusSummsry;
	}

	public void setFocusSummsry(String focusSummsry) {
		FocusSummsry = focusSummsry;
	}

	public String getFocusCreateMan() {
		return FocusCreateMan;
	}

	public void setFocusCreateMan(String focusCreateMan) {
		FocusCreateMan = focusCreateMan;
	}

	public String getFocusCreateManPic() {
		return FocusCreateManPic;
	}

	public void setFocusCreateManPic(String focusCreateManPic) {
		FocusCreateManPic = focusCreateManPic;
	}

	public String getFocusCity() {
		return FocusCity;
	}

	public void setFocusCity(String focusCity) {
		FocusCity = focusCity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
