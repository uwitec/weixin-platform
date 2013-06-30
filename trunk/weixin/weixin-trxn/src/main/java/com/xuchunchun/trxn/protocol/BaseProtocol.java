package com.xuchunchun.trxn.protocol;

import com.xuchunchun.trxn.msg.TrxnMessage;
import com.xuchunchun.trxn.msg.cfg.TrxnConfig;
import com.xuchunchun.trxn.protocol.cfg.ProtocolConfig;

public interface BaseProtocol {
	
	public byte[] pack(TrxnMessage msgObj,ProtocolConfig protocolConfig,String encoding,TrxnConfig trxnConfig) throws Exception;
	
	public TrxnMessage unpack(byte[] msg,ProtocolConfig protocolConfig,String encoding,TrxnConfig trxnConfig) throws Exception;
}
