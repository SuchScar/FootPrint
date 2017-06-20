package com.Tool;

import android.graphics.Bitmap;

public class CallList{
	int pos;//position
	CallBack<Bitmap> entity;
	
	public CallList(int pos,CallBack<Bitmap> entity) {
		this.pos=pos;
		this.entity=entity;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public CallBack<Bitmap> getEntity() {
		return entity;
	}
	public void setEntity(CallBack<Bitmap> entity) {
		this.entity = entity;
	}
}
