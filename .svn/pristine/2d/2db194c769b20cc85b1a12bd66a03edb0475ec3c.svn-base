package com.Common;

//文件格式
import java.io.Serializable;  


/** 
 * 文件上传对象 
 * @author dujinkai 
 * 
 */  
public class FileUploadRequest implements Serializable  
{  
    /** 
     * 序列号 
     */  
    private static final long serialVersionUID = 1547212123169330600L;  
      
    private String fileName;        //文件名
    
    private byte [] bytes;          //文件字节 
    
    private int number;             //事务标志
    
    private int tripPlanId;        //行程安排id
    
    private int tripId ;           //行程id
    
    private int tagpage;           //判断哪个页面的handler
    
    private int alonght;           //文件总长度
	private int imagenamelongth;   //图片名字长度
	private long imagelongth;      //图片长度
    
    public int getAlonght() {
		return alonght;
	}

	public void setAlonght(int alonght) {
		this.alonght = alonght;
	}

	public int getImagenamelongth() {
		return imagenamelongth;
	}

	public void setImagenamelongth(int imagenamelongth) {
		this.imagenamelongth = imagenamelongth;
	}

	public long getImagelongth() {
		return imagelongth;
	}

	public void setImagelongth(long imagelongth) {
		this.imagelongth = imagelongth;
	}

    public int getTagPage() {
		return tagpage;
	}

	public void setTagPage(int tagpage) {
		this.tagpage = tagpage;
	}

	public String getFileName()   
    {  
        return fileName;  
    }  
  
    public void setFileName(String fileName)   
    {  
        this.fileName = fileName;  
    }  
  
    public byte[] getBytes()   
    {  
        return bytes;  
    }  
  
    public void setBytes(byte[] bytes)   
    {  
        this.bytes = bytes;  
    }  
    
    public int getNumber()   
    {  
        return number;  
    }  
  
    public void setNumber(int number)   
    {  
        this.number = number;  
    } 
    
    public int getTripPlanId()   
    {  
        return tripPlanId;  
    }  
  
    public void setTripPlanId(int tripPlanId)   
    {  
        this.tripPlanId = tripPlanId;  
    } 
    
    public int getTripId()   
    {  
        return tripId;  
    }  
  
    public void setTripId(int tripId)   
    {  
        this.tripId = tripId;  
    }  
//    public JSONObject toJson(){
//    	
//    	JSONObject json = new JSONObject();
//    	json.put("fileName", fileName);
//    	json.put("bytes", bytes);
//    	return json;
//    }
//  
//    @Override  
//    public String toString() {  
//        return "FileUploadRequest [toJson()=" + toJson() + "]";  
//    }  
      
      
  
}  
