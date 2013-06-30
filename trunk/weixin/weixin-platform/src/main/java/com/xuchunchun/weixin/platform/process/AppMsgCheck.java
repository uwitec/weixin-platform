package com.xuchunchun.weixin.platform.process;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.log4j.Logger;

import com.xuchunchun.base.util.PWD;
import com.xuchunchun.weixin.platform.msg.ApplicationMsg;

public class AppMsgCheck {
	private static Logger logger = Logger.getLogger(AppMsgCheck.class);
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static ArrayList<String> dictSort(ApplicationMsg msg) {
		logger.info("sort by dictionery");

		ArrayList<String> msgArray = new ArrayList<String>();
		msgArray.add(PWD.dec(msg.getToken()));
		msgArray.add(msg.getTimestamp());
		msgArray.add(msg.getNonce());

		Collections.sort(msgArray, new Comparator<String>() {
			public int compare(String src, String tar) {
				return src.compareTo(tar);
			}
		});

		return msgArray;
	}

	public static String encBySha1(ArrayList<String> msg)
			throws NoSuchAlgorithmException {
		logger.info("enc the str by sha1.....");
		MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
		StringBuffer src = new StringBuffer();
		for (int i = 0; i < msg.size(); i++) {
			src.append(msg.get(i));
		}
		messageDigest.update(src.toString().getBytes());
		return getFormattedText(messageDigest.digest());
	}
	
	public static boolean check(ApplicationMsg msg) throws NoSuchAlgorithmException{
		
		if(msg.getSignature() == null)return false;
		String checkResult = encBySha1(dictSort(msg));
		logger.info("check result:"+checkResult);
		if(msg.getSignature().equals(checkResult)){
			return true;
		}else return false;
	}

	private static String getFormattedText(byte[] bytes) {

		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);

		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}

		return buf.toString();

	}

}
