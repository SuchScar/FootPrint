package com.Activity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.Lru.DiskLruCache;
import com.Lru.LruCacheUtils;
import com.Tool.CircleImageView;
import com.Tool.SPUtils;
import com.Tool.ToastUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityChangeUserInfo extends Activity{

	private ImageView back;
	private CircleImageView userimage;
	private RelativeLayout myimage,myname,mysex;
	private TextView nickname,sex;
	private String imageurl;
	private Bitmap photo;
	
	//数据更新获取
	public static Handler mhander;
	private LruCacheUtils lruCache;
	
	/* 头像文件 */
	private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
	/* 请求识别码 */
	private static final int CODE_GALLERY_REQUEST = 0xa0;
	private static final int CODE_CAMERA_REQUEST = 0xa1;
	private static final int CODE_RESULT_REQUEST = 0xa2;
	// 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
	private static int output_X = 480;
	private static int output_Y = 480;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_changeuserinfo);
		lruCache = LruCacheUtils.getInstance();
		imageurl=(String) SPUtils.get(this, "userPicture", "");
		initView();
		setListen();
		updateUI();
	}


	private void setListen() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		mysex.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(ActivityChangeUserInfo.this);
                builder.setTitle("请选择性别");		
                final String[] sexxx = {"男", "女"};
                builder.setItems(sexxx, new DialogInterface.OnClickListener() {
  					@Override
  					public void onClick(DialogInterface dialog, int which) {
  						// TODO Auto-generated method stub
  						switch (which) {
						case 0:
							sex.setText("男");
							break;
						case 1:
							sex.setText("女");
							break;
						default:
							break;
						}
  						
  					}
  		});	
                builder.show();
			}
		});
		myname.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				   final EditText et=new EditText(ActivityChangeUserInfo.this);
				   et.setBackgroundColor(Color.TRANSPARENT);
	  				AlertDialog.Builder builder = new AlertDialog.Builder(ActivityChangeUserInfo.this);
	  			    builder.setTitle("请输入昵称")
	  			    .setView(et)
	  			    .setNegativeButton("取消", null)
	  			    .setPositiveButton("确定", new DialogInterface.OnClickListener(){
	  			    @Override
	  			    
	  			public void onClick(DialogInterface dialog,int which){
	  			    	 String str = et.getText().toString();
	  			    	
	  			    	if(str.equals("")){
	  			    		Toast.makeText(ActivityChangeUserInfo.this, "您输入的为空",Toast.LENGTH_SHORT).show();
	  			    	}
	  			    	else
	  			    		nickname.setText(str);
	  			    }
	  			    }).show();	
			}
		});
		myimage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String[] arrayContestLevel = new String[] {
						"相机拍摄", "手机相册" };

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						ActivityChangeUserInfo.this).setTitle("选择头像")
						.setItems(arrayContestLevel,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(
											DialogInterface dialog,
											int which) { // TODO
															// Auto-generated
															// method
															// stub
										switch (which) {
										case 0:
											Toast.makeText(
													ActivityChangeUserInfo.this,
													"相机拍摄",
													Toast.LENGTH_SHORT)
													.show();
											choseHeadImageFromCameraCapture();
											break;
										case 1:
											Toast.makeText(
													ActivityChangeUserInfo.this,
													"手机相册",
													Toast.LENGTH_SHORT)
													.show();
											choseHeadImageFromGallery();
										default:
											break;
										}
										dialog.cancel();
									}
								});
				alertDialog.create().show();
			}
		});
		
	}


	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView) findViewById(R.id.back_image);
		userimage=(CircleImageView) findViewById(R.id.userhead);
		myimage=(RelativeLayout) findViewById(R.id.myimage);
		myname=(RelativeLayout) findViewById(R.id.myname);
		mysex=(RelativeLayout) findViewById(R.id.mysex);
		nickname=(TextView) findViewById(R.id.nickname);
		sex=(TextView) findViewById(R.id.sex);
		
		nickname.setText(SPUtils.get(this, "userNickname", "").toString());
		lruCache.loadBitmap(userimage,imageurl,10);
		
		
	}
	
	private void updateUI() {
		// TODO Auto-generated method stub
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==1){
					Bitmap bitmap=(Bitmap) msg.obj;
					
					String url=imageurl;
					String key = lruCache.hashKeyForDisk(url);
	                DiskLruCache.Editor editor = null;
	                userimage.setImageBitmap(bitmap);
					lruCache.addBitmapToCache(url,bitmap);
					try {
						editor = lruCache.diskLruCache.edit(key);
						//位图压缩后输出（参数：压缩格式，质量(100表示不压缩，30表示压缩70%)，输出流）
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, editor.newOutputStream(0));
                        editor.commit();//提交
					} catch (IOException e) {
						 try {
		                        editor.abort();//放弃写入
		                    } catch (IOException e1) {
		                        e1.printStackTrace();
		                    }
					}	
				}
				
			}
		};
	}
	private void choseHeadImageFromGallery() {
	        Intent intentFromGallery = new Intent();
	        // 设置文件类型
	        intentFromGallery.setType("image/*");
	        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
	        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
	    }
	// 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
 
        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }
 
        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }
	 
	 
	 @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			// 用户没有进行有效的设置操作，返回
			if (resultCode == RESULT_CANCELED) {
				ToastUtil.show(ActivityChangeUserInfo.this, "取消");
				return;
			}
			switch (requestCode) {
			case CODE_GALLERY_REQUEST:

	            cropRawPhoto(data.getData());
	            break;

			case CODE_CAMERA_REQUEST:
				if (hasSdcard()) {
					File tempFile = new File(
							Environment.getExternalStorageDirectory(),
							IMAGE_FILE_NAME);
					cropRawPhoto(Uri.fromFile(tempFile));
				} else {
					Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
							.show();
				}
				break;

			case CODE_RESULT_REQUEST:
				if (data != null) {
					setImageToHeadView(data);
				}
				break;

			default:
				break;
			}
			super.onActivityResult(requestCode, resultCode, data);
		}
	 /**
	     * 检查设备是否存在SDCard的工具方法
	     */
	    public static boolean hasSdcard() {
	        String state = Environment.getExternalStorageState();
	        if (state.equals(Environment.MEDIA_MOUNTED)) {
	            // 有存储的SDCard
	            return true;
	        } else {
	            return false;
	        }
	    }
	    /**
	     * 裁剪原始的图片
	     */
	    public void cropRawPhoto(Uri uri) {
	 
	        Intent intent = new Intent("com.android.camera.action.CROP");
	        intent.setDataAndType(uri, "image/*");
	 
	        // 设置裁剪
	        intent.putExtra("crop", "true");
	 
	        // aspectX, aspectY:宽高的比例
	        intent.putExtra("aspectX", 1);
	        intent.putExtra("aspectY", 1);
	 
	        // outputX , outputY : 裁剪图片宽高
	        intent.putExtra("outputX", output_X);
	        intent.putExtra("outputY", output_Y);
	       // intent.putExtra("return-data", true);
	        File bitmapFile = new File(Environment
	                .getExternalStorageDirectory(), IMAGE_FILE_NAME);
	        Uri uritempFile = Uri.fromFile(bitmapFile);
	        // Uri uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" +IMAGE_FILE_NAME);  
	        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);  
	        //intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString()); 
	        startActivityForResult(intent, CODE_RESULT_REQUEST);
	    }
	    /**
	     * 提取保存裁剪之后的图片数据，并设置头像部分的View
	     */
	    private void setImageToHeadView(Intent intent) {
	        Bundle extras = intent.getExtras();
	        if (extras != null) {
	        	File bitmapFile = new File(Environment
	                    .getExternalStorageDirectory(), IMAGE_FILE_NAME);
	        	
	        	photo =  BitmapFactory.decodeFile(bitmapFile.getPath());
	        	System.out.println("-------------------"+photo.getByteCount());
	        	
	        	
	        	
	        	userimage.setImageBitmap(photo);
	        }
	    }

}
