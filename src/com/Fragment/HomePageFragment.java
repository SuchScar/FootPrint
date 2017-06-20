package com.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.FocusBriefEntity;
import Entity.SetFocusBriefListEntity;
import Entity.SetTripBriefListEntity;
import Entity.TripBriefEntity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.Activity.ActivityFoucsDetail;
import com.Activity.ActivityHomeSearch;
import com.Activity.ActivityTripDetail;
import com.Activity.R;
import com.Adapter.FoucsBreifListAdapter;
import com.Adapter.FragmentTripPageListAdapter;
import com.Bll.MainBindService;
import com.Bll.MinaSocket;
import com.Common.FileUploadRequest;
import com.Tool.ToastUtil;
import com.muli_image_selector.onee.MultiImageSelector;

public class HomePageFragment extends ListFragment implements OnScrollListener{
	
	private LinearLayout search;
	//自定义变量
	private FragmentTripPageListAdapter adapter;
	private SetTripBriefListEntity list;
	
	//数据获取
	public static Handler mhander;
	
	//刷新数据变量
	private View footview;
	private TextView tv_tip;
	private LinearLayout vis;
	private int i=1;
	private int visibleLastIndex;   //用来可显示的最后一条数据的索引值
	private ProgressBar progressBar;
	private boolean moredata = true;
	private boolean firstdata = true;
	
	
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
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
		    View view = inflater.inflate(R.layout.fragment_home, null);
		    footview=inflater.inflate(R.layout.loading, null);
		    
		    findview(view);
		    
			return view;
						
		}
	
	
	
	private void findview(View view) {
		// TODO Auto-generated method stub
		search=(LinearLayout)view.findViewById(R.id.search_lin);
		tv_tip=(TextView) footview.findViewById(R.id.textView1);
		progressBar=(ProgressBar) footview.findViewById(R.id.progressBar1);
		vis=(LinearLayout) footview.findViewById(R.id.vis);
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		getListView().addFooterView(footview);
		getListView().setOnScrollListener(this);
		
		updateUI();
		setlisten();
		
	}

	private void updateUI() {
		// TODO Auto-generated method stub
		mhander=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==0){
					if(MainBindService.sTripBriefList.isFlag()){
						if(firstdata){
						list=(SetTripBriefListEntity) MainBindService.sTripBriefList.getData();
						if(list.getSize()<15){
							vis.setVisibility(View.GONE);
						}
						adapter = new FragmentTripPageListAdapter(getActivity(), list);
						setListAdapter(adapter);
						
						firstdata=false;
						}else{
							List<TripBriefEntity>l=new ArrayList<TripBriefEntity>();
							List<TripBriefEntity>nl=new ArrayList<TripBriefEntity>();
							
							nl=((SetTripBriefListEntity) MainBindService.sTripBriefList.getData()).getTripBriefList();
							if(!nl.isEmpty()){
							l=list.getTripBriefList();
							l.addAll(nl);
							list=new SetTripBriefListEntity(l);
							adapter.notifyDataSetChanged();
							}else{
								progressBar.setVisibility(View.GONE);
								tv_tip.setText("没有更多");
								moredata=false;
							}
						}
					}
				}

			}
		};
	}



	private void setlisten() {
		// TODO Auto-generated method stub
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),ActivityHomeSearch.class);
				startActivity(intent);
				//choseHeadImageFromGallery();
			}
		});
	}

	  @Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Intent intent=new Intent(getActivity(),ActivityTripDetail.class);
		intent.putExtra("tripid",list.getItem(position).getTrip_id());
		startActivity(intent);
		
		
	}



	private void choseHeadImageFromGallery() {
	        Intent intentFromGallery = new Intent();
	        // 设置文件类型
	        intentFromGallery.setType("image/*");
	        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
//	        File bitmapFile = new File(Environment
//	                .getExternalStorageDirectory(), IMAGE_FILE_NAME);
//	        Uri uritempFile = Uri.fromFile(bitmapFile);
//	        // Uri uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" +IMAGE_FILE_NAME);  
//	        intentFromGallery.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
	        
	        
	        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
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
	        intent.putExtra("aspectX", 3);
	        intent.putExtra("aspectY", 4);
	 
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

	    @Override
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	// TODO Auto-generated method stub
//	    	if (resultCode == RESULT_CANCELED) {
//				ToastUtil.show(UserInfoActivity.this, "取消");
//				return;
//			}
	    	switch (requestCode) {
	    	case CODE_GALLERY_REQUEST:

	            cropRawPhoto(data.getData());
	    		//setImageToHeadView(data);
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
	    private void setImageToHeadView(Intent intent) {
	        Bundle extras = intent.getExtras();
	        if (extras != null) {
	            // Bitmap photo = extras.getParcelable("data");
	        	File bitmapFile = new File(Environment
	                    .getExternalStorageDirectory(), IMAGE_FILE_NAME);
	        	
	            Bitmap photo =  BitmapFactory.decodeFile(bitmapFile.getPath());
	            
	            
	        	File file1 = saveImage(photo);
	        	
	        	
	        	try {
	        		JSONObject json=new JSONObject();
	        		List<FileUploadRequest> l=getFileUploadRequest(file1.getPath());
	        		JSONArray jsonsrray=new JSONArray();
	        		
	        		for(int i=0;i<l.size();i++){
	        			JSONObject jsonn=new JSONObject();

	        			jsonn.put("fileName", l.get(i).getFileName());
	        			jsonn.put("bytes",new String(l.get(i).getBytes(), "ISO-8859-1"));
	        			System.out.println("1111111111111"+l.get(i).getBytes().toString());
	        			jsonsrray.put(jsonn);
	        			}
	        		
	        		json.put("tag",100);
	        		json.put("pic", jsonsrray);
					MinaSocket.SendMessage(json);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	
//	        	System.out.println("file1-------" +file1.getName());
//				com.upload.FormFile ff1 = new com.upload.FormFile(
//						file1.getName(), file1);
//				files.add(ff1);
//				
//	        	HashMap<String, String> map = new HashMap<String, String>();
//				map.put("UserId", SPUtils.get(UserInfoActivity.this, MyOpecode.UserInfo.USERID, 
//								-1,MyOpecode.Operation.USER_DATA).toString());
//				new UploadFiles(MyURL.UPLOAD, files, mHandler, map).excute();
	        }
	    }
	    private File saveImage(Bitmap bmp) {
			File appDir = new File(Environment.getExternalStorageDirectory(),
					"downloadPicture");
			if (!appDir.exists()) {
				appDir.mkdir();
			}
			String fileName = "11111111111111111" + ".jpg";
			System.out.println("filename-----------" + fileName);
			File file = new File(appDir, fileName);
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				bmp.compress(CompressFormat.JPEG, 100, fileOutputStream);
				fileOutputStream.flush();
				fileOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return file;
		}
	    
	    private static List<FileUploadRequest> getFileUploadRequest(String fileName) throws Exception  
	    {  
	    	List<FileUploadRequest> fileList = new ArrayList<FileUploadRequest>();   //定义图片list
	    	String []fileSplit = fileName.split(",");
	    	System.out.println(fileSplit.length);
	    	
	    	for (int i = 0; i < fileSplit.length; i++) {
				
		        FileUploadRequest fileUploadRequest = new FileUploadRequest();  
		          
		        fileUploadRequest.setFileName(fileSplit[i]);  
		    	
		        File file = new File(fileSplit[i]);
		          
		        InputStream is = new FileInputStream(file);  
		          
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		          
		        byte [] bytes = new byte[1024];  
		          
		        int length = 0;   
		          
		        while (-1 !=(length = is.read(bytes)))  
		        {  
		            baos.write(bytes, 0, length);  
		        } 
		        
		        fileUploadRequest.setBytes(baos.toByteArray());  
		          
//		        JSONObject j=new JSONObject();
//		        j.put("fileName", fileUploadRequest);
//		        j.put("bytes", fileUploadRequest.getBytes());
		        is.close();  
		        baos.close(); 
		        fileList.add(fileUploadRequest);
	    	}
	          
	        return fileList;  
	    }



		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub
			if(scrollState == SCROLL_STATE_IDLE && moredata &&
					adapter.getCount() == visibleLastIndex){
				i=i+1;	
				requestServer(i);
				
			}
		}



		private void requestServer(int i) {
			// TODO Auto-generated method stub
			try {
				JSONObject json= new JSONObject();
				json.put("tag",5);
				json.put("page", i);
				MinaSocket.SendMessage(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}



		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
			visibleLastIndex = firstVisibleItem + visibleItemCount-1;
		}
}
