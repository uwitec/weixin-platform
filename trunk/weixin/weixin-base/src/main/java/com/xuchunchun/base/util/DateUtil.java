package com.xuchunchun.base.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtil {
	private static String DATAFORMAT_STR = "yyyy-MM-dd hh:mm:ss";
	
	public static String formatDate(long time){
		DateFormat df=new SimpleDateFormat(DATAFORMAT_STR);
        Date date=new Date(time);
        
        return df.format(date);
	}
	
	public static void main(String args[]){
		System.out.println(formatDate(1348831860L));
	}
}
