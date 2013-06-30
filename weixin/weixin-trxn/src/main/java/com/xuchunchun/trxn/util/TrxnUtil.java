package com.xuchunchun.trxn.util;

public class TrxnUtil {

	public static byte[] toByteArray(int iSource) {
		byte[] bLocalArr = new byte[4];
		for (int i = 0; i < 4; i++) {
			bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
		}
		return bLocalArr;
	}

	public static int byteToInt(byte[] bRefArr) {
		int iOutcome = 0;
		byte bLoop;

		for (int i = 0; i < bRefArr.length; i++) {
			bLoop = bRefArr[i];
			iOutcome += (bLoop & 0xFF) << (8 * i);
		}
		return iOutcome;
	}
}
