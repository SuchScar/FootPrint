package com.Tool;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONtoDataSet {

	private int tag;
	private Object data=null;
	private Object data1=null;
	private Object data2=null;
	
	private boolean flag=false;

	public JSONtoDataSet(){}
	
	public JSONtoDataSet(JSONObject json){
		
		try {
			this.tag=json.getInt("tag");
			this.flag=json.getBoolean("boolean");
			this.data=json;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Object getData1() {
		return data1;
	}

	public void setData1(Object data1) {
		this.data1 = data1;
	}

	public Object getData2() {
		return data2;
	}

	public void setData2(Object data2) {
		this.data2 = data2;
	}
	
	
}
