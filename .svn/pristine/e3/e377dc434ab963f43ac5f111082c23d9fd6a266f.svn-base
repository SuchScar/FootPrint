package com.Factory;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.Common.FileUploadRequest;

public class MyProtocalEncoder extends ProtocolEncoderAdapter {
	private final String charset;
	public MyProtocalEncoder(String charset) {
		this.charset = charset;
	}

	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		
		System.out.println("开始编码：");
		
		try{
			if(message instanceof List){           //是图片
				
				int totalLength = 0;               //所有图片数据总长
				
				List<FileUploadRequest> result = (List<FileUploadRequest>)message; 
				for (int i = 0; i < result.size(); i++) {
					
					totalLength += result.get(i).getAlonght();
				}
				IoBuffer io = IoBuffer.allocate(totalLength +4+4+4);
				io.setAutoExpand(true);            //自动调节大小
				System.out.println("总长："+totalLength);
				io.putInt(totalLength +4+4+4);           //存储数据流总长度
				io.putInt(1);               //1代表传图片
				io.putInt(result.size());            //图片张数
				
				for (int i = 0; i < result.size(); i++) {
					
					io.putInt(result.get(i).getAlonght());         //单个照片总长度
					io.putLong(result.get(i).getImagelongth());    //图片长度
					io.put(result.get(i).getBytes());              //图片数组
					io.putInt(result.get(i).getNumber());             //事务标志
					io.putInt(result.get(i).getTagPage());           //判断是在哪个页面
					System.out.println("send remaining:"+io.limit());
				}
				io.flip();         //重置mask，为了读取做好准备，一般是结束buf操作，将buf写入输出流时调用
				out.write(io);
				io.free();
				io=IoBuffer.allocate(0);
				io.clear();
			}
			
			else if(message instanceof String){           //是字符串
				
				
				String result = (String) message;
				CharsetEncoder encoder = Charset.forName("utf-8").newEncoder();
				IoBuffer io = IoBuffer.allocate(4+result.length()+4);
				io.setAutoExpand(true);
				io.putInt(4+result.length()+4);         //数据总长
				io.putInt(2);               //2代表传字符串
				io.putString(result, encoder);
				io.flip();
				out.write(io);
				io.free();
				io=IoBuffer.allocate(0);
				io.clear();
				//释放io多余的内存空间shink
			}
		}catch (Exception e) {
			System.out.println("发生错误："+e);
		}
		System.out.println("编码完成");
//		ByteBuffer b = null;
//		
//		if (b != null) {
//		
//
//			b.flip();
//			IoBuffer ioBuf = IoBuffer.wrap(b);
//			out.write(ioBuf);
//		}

	}

//	public void dispose() throws Exception {
//	}
}
