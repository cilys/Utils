package com.cily.utils.base;

public class StrUtils {
	
	public final static String join(Object... os){
		if(os != null){
			StringBuilder su = getStringBuilder();
			for(Object o : os){
				su.append(o);
			}
			return su.toString();
		}
		return null;
	}
	
	public final static StringBuilder getStringBuilder(){
		return new StringBuilder();
	}
	
	public final static boolean isEmpty(String str){
		return isNull(str) || "".equals(str.trim());
	}
	
	public final static boolean isNull(String str){
		return str == null;
	}
}