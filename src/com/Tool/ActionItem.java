package com.Tool;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * 功能描述：弹窗内部子类项（绘制标题和图标）
 */
public class ActionItem {
	// 定义图片对象
	public int mDrawable;
	// 定义文本对象
	public CharSequence mTitle;
	private int Participantsid;

	public ActionItem(int drawable, CharSequence title,int Participantsid) {
		this.mDrawable = drawable;
		this.mTitle = title;
		this.Participantsid=Participantsid;
	}

	public ActionItem(Context context, int titleId, int drawableId,int Participantsid) {
		this.mTitle = context.getResources().getText(titleId);
		this.mDrawable = drawableId;
		this.Participantsid=Participantsid;
	}

	public ActionItem(Context context, CharSequence title, int drawableId,int Participantsid) {
		this.mTitle = title;
		this.mDrawable = drawableId;
		this.Participantsid=Participantsid;
	}

	public ActionItem(Context context, CharSequence title) {
		this.mTitle = title;
		//this.mDrawable = null;
	}

	public int getParticipantsid() {
		return Participantsid;
	}

	public void setParticipantsid(int participantsid) {
		Participantsid = participantsid;
	}
	
}
