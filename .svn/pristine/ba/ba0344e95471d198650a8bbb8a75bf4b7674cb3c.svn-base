package Entity;

import org.json.JSONException;
import org.json.JSONObject;

import com.Tool.SPUtils;

import android.content.Context;


public class UserEntity {

	private	int	UserId	;//	用户id
	private	String	UserName	;//	用户名称
	private	String	UserPicture	;//	用户头像
	private	String	UserPhone	;//	用户电话
	private String  UserAccount ;// 用户账户
	private	String	UserPassword	;//	用户密码
	private	String	UserNickname	;//	用户昵称
	private	String	UserSex	;//	用户性别
	private	String	Userbirth	;//	用户生日
	private	String	UserCity	;//	用户居住城市
	private	String	UserAddress	;//	用户地址
	private	String	UserRegisterTime	;//	用户注册时间
	private	String	UserEmail	;//	用户邮箱
	private	int	UserIntegral	;//	用户积分
	private	String	UserSignature	;//	用户个性签名
	private	int	UserState	;//	用户状态
	
	public UserEntity() {}

	public UserEntity(JSONObject json) {
		try {
			this.UserState=json.getInt("userState");
			this.UserIntegral=json.getInt("userIntegral");
			this.UserNickname=json.getString("userNickname");
			if(json.has("userPicture")){
				this.UserPicture=json.getString("userPicture");
			}
			this.UserId=json.getInt("userId");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public JSONObject ToJSON(int tag,String account,String password){
		JSONObject json=new JSONObject();
		try {
			json.put("tag", tag);
			json.put("userAccount", account);
			json.put("userPassword", password);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public void saveUserInfo(Context context, UserEntity UserEntity) {
		SPUtils.put(context, "userId", UserEntity.getUserId());
		SPUtils.put(context, "userAccount", UserEntity.getUserAccount());
		SPUtils.put(context, "userPassword", UserEntity.getUserPassword());
		SPUtils.put(context, "userNickname", UserEntity.getUserNickname());
		SPUtils.put(context, "userState", UserEntity.getUserState());
		SPUtils.put(context, "userIntegral", UserEntity.getUserIntegral());
		SPUtils.put(context, "userPicture", UserEntity.getUserPicture());

	}
	
	
	
	
	
	
	
	
	
	
	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPicture() {
		return UserPicture;
	}

	public void setUserPicture(String userPicture) {
		UserPicture = userPicture;
	}

	public String getUserPhone() {
		return UserPhone;
	}

	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}

	public String getUserAccount() {
		return UserAccount;
	}

	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public String getUserNickname() {
		return UserNickname;
	}

	public void setUserNickname(String userNickname) {
		UserNickname = userNickname;
	}

	public String getUserSex() {
		return UserSex;
	}

	public void setUserSex(String userSex) {
		UserSex = userSex;
	}

	public String getUserbirth() {
		return Userbirth;
	}

	public void setUserbirth(String userbirth) {
		Userbirth = userbirth;
	}

	public String getUserCity() {
		return UserCity;
	}

	public void setUserCity(String userCity) {
		UserCity = userCity;
	}

	public String getUserAddress() {
		return UserAddress;
	}

	public void setUserAddress(String userAddress) {
		UserAddress = userAddress;
	}

	public String getUserRegisterTime() {
		return UserRegisterTime;
	}

	public void setUserRegisterTime(String userRegisterTime) {
		UserRegisterTime = userRegisterTime;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public int getUserIntegral() {
		return UserIntegral;
	}

	public void setUserIntegral(int userIntegral) {
		UserIntegral = userIntegral;
	}

	public String getUserSignature() {
		return UserSignature;
	}

	public void setUserSignature(String userSignature) {
		UserSignature = userSignature;
	}

	public int getUserState() {
		return UserState;
	}

	public void setUserState(int userState) {
		UserState = userState;
	}
	
	
	
	
	
}
