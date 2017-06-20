package com.Factory;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.Common.FileUploadRequest;

public class MyProtocalDecoder extends CumulativeProtocolDecoder {     
	private final String charset;
	public MyProtocalDecoder(String charset) {     
        this.charset = charset;     
    }
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		
		System.out.println("进入解码");
		int smsLength = 0;
		int pos = in.position();
		System.out.println("当前位置："+pos);
		System.out.println("limit:"+in.limit());
		int remaining = in.remaining();
		System.out.println("remaining:"+remaining);
		try {

			// 判断长度4位 可配置
			if (remaining < 4) {
				in.position(pos);
				// in.limit(limit);
				return false;
			}
			// 判断是否够解析出的长度
			smsLength = in.getInt();
			System.out.println("将要解析的数据长度:"+smsLength);
			if (remaining < smsLength || smsLength < 0) {
				in.position(pos);
//				in.reset();          //????????????????????????????????????????????????????????
				return false;
			}
			else{
			
				int judgeNumber = in.getInt();       //判断标志
				System.out.println("判断标志："+judgeNumber);
				
				if(judgeNumber == 1){         //图片
					int picNumber = in.getInt();          //图片张数
					
					int alonghtTotl = smsLength;
					System.out.println("数据总长:"+alonghtTotl);
					
					List<FileUploadRequest> messList = new ArrayList<FileUploadRequest>();
					
					for(int i=0;i<picNumber;i++){
						
						FileUploadRequest mes=new FileUploadRequest();
						int alonght = in.getInt();        //单张图片io总长  
						mes.setAlonght(alonght);
						long imagelongth=in.getLong();        //图片大小
						System.out.println("imagelongth:"+imagelongth);
						mes.setImagelongth(imagelongth);
						byte[] image=new byte[(int) imagelongth];       //图片字节
						in.get(image);
						mes.setBytes(image);
						int number =in.getInt();           //number
						mes.setNumber(number);
						int tagPage = in.getInt();          //tagPage
						mes.setTagPage(tagPage);
						
						messList.add(mes);
		//				System.gc(); 
		//				in.free();
					}
					out.write(messList);
					if(in.remaining() > 0){//如果读取内容后还粘了包，就让父类再给 一次，进行下一次解析  
						System.out.println("现在位置："+in.position());
//						in.position(in.position()-1);        
						System.out.println("还有数据"+in.remaining());
					    return true;  
					}
				}else if(judgeNumber == 2){       //字符串
					
					CharsetDecoder decoder = Charset.forName(charset).newDecoder();
					String picName = in.getString(decoder);
					System.out.println("图片名："+picName);
					out.write(picName);
					if(in.remaining() > 0){//如果读取内容后还粘了包，就让父类再给 一次，进行下一次解析  
						System.out.println("现在位置："+in.position());
						in.position(in.position()-1);        
						System.out.println("还有数据"+in.remaining());
					    return true;  
					}
				}
				else{;}
				
			}

		} catch (Exception e) {
			in.position(pos);
//			 in.limit(limit);
			return false;
		}
		
		return true;
	}
    
}  
