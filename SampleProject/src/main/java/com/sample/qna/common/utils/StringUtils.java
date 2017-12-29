package com.sample.qna.common.utils;

public class StringUtils {
	public static String isEmpty(String val, String rtnVal){
		return isEmpty(val) ? rtnVal : val;
	}
	
	public static boolean isEmpty(String val){
		boolean rtn = true;
		if(val != null){
			if(!val.equals("")){
				rtn = false;
			}
		}
		return rtn;
	}
}
