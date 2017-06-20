package com.Factory;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MyProtocalCodecFactory   implements ProtocolCodecFactory {  
    private final MyProtocalEncoder encoder;  //±àÂë
    private final MyProtocalDecoder decoder;  //½âÂë
      
    public MyProtocalCodecFactory(String charset) {  
        encoder=new MyProtocalEncoder(charset);  
        decoder=new MyProtocalDecoder(charset);  
    }  
       
    public ProtocolEncoder getEncoder(IoSession session) {  
        return encoder;  
    }  
    public ProtocolDecoder getDecoder(IoSession session) {  
        return decoder;  
    }  
      
} 
