package com.sample.qna.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String DEFAULT_SHORT_NUMBER_FORMAT = "yyyy-MM-dd";
	public static String DEFAULT_FULL_NUMBER_FORMAT = "yyyyMMddHHmmss";
	public static String DEFAULT_FULL_MM_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	//yyyyMMddHHmmss //yyyy-MM-dd HH:mm:ss
	public static String getDate(){
		return getDate(DEFAULT_FULL_MM_FORMAT);
	}
	
	public static String getDateShort(){
		return getDate(DEFAULT_SHORT_NUMBER_FORMAT);
	}
	
	public static String getDate(String formatter){
		Date date = new Date();
		DateFormat fotmat = new SimpleDateFormat(formatter);
		return fotmat.format(date);
	}
	
	
	
	
}
