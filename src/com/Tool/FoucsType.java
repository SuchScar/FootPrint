package com.Tool;

import org.json.JSONException;
import org.json.JSONObject;

public class FoucsType {

	int id;
	String name;
	
	public FoucsType(int i,String name){
		this.id=i;
		this.name=name;
	}
	public FoucsType(JSONObject json) throws JSONException{
		this.id=json.getInt("foucsId");
		this.name=json.getString("foucsType");
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
