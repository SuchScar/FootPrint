package com.Tool;

public class IconManger {

	private int imageid;
	private boolean flag;
	public IconManger(boolean flag,int imageid){
		this.flag=flag;
		this.imageid=imageid;
	}
	public int getImageid() {
		return imageid;
	}
	public void setImageid(int imageid) {
		this.imageid = imageid;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
	
}
