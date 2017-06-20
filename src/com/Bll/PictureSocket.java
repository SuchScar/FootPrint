package com.Bll;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.Factory.MyProtocalCodecFactory;
import com.KeepAlive.ClientKeepAliveImp;

public class PictureSocket {
	
	private static String HOST = "192.168.0.198";
	//private static String HOST = "120.24.49.3";

	private static int PORT = 3387;
	//private static int PORT = 59102;
	
	private static int HEARTBEATRATE = 10;
	
	private static IoSession session;

	private static boolean result;
	
	public static void connect(){           //��������
		
		// ����һ���������Ŀͻ��˳���
		IoConnector connector = new NioSocketConnector();
		// �������ӳ�ʱʱ��
		connector.setConnectTimeout(30000);
//		ObjectSerializationCodecFactory factory = new ObjectSerializationCodecFactory();   
//        //�趨�������ֵ   
//        factory.setDecoderMaxObjectSize(Integer.MAX_VALUE);// �趨����������Խ��մ�����   
//        factory.setEncoderMaxObjectSize(Integer.MAX_VALUE); 
		
		// ���ӹ�����
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new MyProtocalCodecFactory("utf-8")));
		KeepAliveMessageFactory heartBeatFactory = new ClientKeepAliveImp();        //ʵ����,������������
		//KeepAliveRequestTimeoutHandler����ΪCLOS�����������������������ڹ涨ʱ����û�н��ܵ�������ʱ�������CLOSE��ʽ �ر����� 
		KeepAliveFilter kaf = new KeepAliveFilter(heartBeatFactory, IdleStatus.BOTH_IDLE,KeepAliveRequestTimeoutHandler.CLOSE);

		 // �Ƿ�ط� ,��session����idle״̬��ʱ�� ��Ȼ����handler�е�idled����
			kaf.setForwardEvent(true);
	        // ����Ƶ�� ,ÿ10�뷢��һ����������  ��������ӽ������״̬ ���ҷ���idled�����ص�
			kaf.setRequestInterval(HEARTBEATRATE);
			kaf.setRequestTimeout(10);    //��ʱ
			connector.getFilterChain().addLast("heartbeat", kaf);       //�����������뵽������������
		
		connector.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));

		// ����ҵ���߼���������
		connector.setHandler(new PictureHander());
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(
					HOST, PORT));// ��������
			future.awaitUninterruptibly();// �ȴ����Ӵ������
			if(future.isConnected()){
			session = future.getSession();// ���session
			result=true;
			
			//session.write("bye");// ������Ϣ
//			try {
//				JSONObject json= new JSONObject();
//				json.put("tag",1);
//				json.put("page", 1);
//				//MinaSocket.SendMessage(json);
//				session.write(json.toString());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
//			if(!SPUtils.get(context,"userAccount", "").equals("")){
//			if(!SPUtils.get(context,"userPassword", "").equals("")){
//				JSONObject json=new JSONObject();
//				json=new UserEntity().ToJSON(11,SPUtils.get(context,"userAccount", "").toString(), SPUtils.get(context,"userPassword", "").toString());
//				try {
//					MinaSocket.SendMessage(json);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
			
			
//			session.getCloseFuture().awaitUninterruptibly();// �ȴ����ӶϿ�
//			connector.dispose();

			}else{
				result=false;
			}
		} catch (Exception e) {
			System.out.println("�ͻ��������쳣..."+e);
		}

		
			session.getCloseFuture().awaitUninterruptibly();// �ȴ����ӶϿ�
			connector.dispose();
		
		
	}
	
	//������Ϣ
	public static void SendMessage(Object message) throws Exception {
        session.write(message);
    }
	//�ж�session�Ƿ�Ϊ��
	public static  boolean isTrue(){
		return result;  
    }

    }