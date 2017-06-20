package com.Activity;

import java.util.ArrayList;
import java.util.List;

import com.Adapter.CommentListAdapter;
import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Tool.ToastUtil;

import Entity.CommentEntity;
import Entity.SetCommentListEntity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

public class ActivityComment extends Activity {

	private ImageView back;
	private ListView listview;

	// 自定义变量
	private SetCommentListEntity list;

	// 数据更新获取
	public static Handler mhander;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_comments);
		Intent intent = getIntent();
		CommentEntity commententity = new CommentEntity();
		try {
			MinaSocket.SendMessage(commententity.ToJSON(7,
					intent.getIntExtra("tripid", -1), 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		getData();

		initView();
		serlisten();

		updateUI();
	}

	private void updateUI() {
		mhander = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 0) {
					if (MainBindService.CommentList.isFlag()) {
						SetCommentListEntity sCommentList = (SetCommentListEntity) MainBindService.CommentList
								.getData();
						listview.setAdapter(new CommentListAdapter(
								ActivityComment.this, sCommentList));
					} else {
						ToastUtil.show(ActivityComment.this, "数据获取异常");
					}
				}
			}
		};
	}

	private void getData() {
		List<CommentEntity> li = new ArrayList<CommentEntity>();
		for (int i = 0; i < 20; i++) {
			li.add(new CommentEntity(i, "这里非常的美，我非常喜欢，希望下次能去这里旅行，期待",
					"2016/05/12", "老王", ""));
		}
		list = new SetCommentListEntity(li);
	}

	private void initView() {
		back = (ImageView) findViewById(R.id.back_image);
		listview = (ListView) findViewById(R.id.listview);

	}

	private void serlisten() {
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
