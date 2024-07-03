package com.llf.utils;

public class StringUtil {
	
	public static boolean isEmpty(Object obj) {
		
		if(obj == null || "".equals(obj) || obj.toString().length() == 0) {
			return false;
		}
		
		return true;
		
	}

}
