package com.cily.utils.base;

import com.cily.utils.base.log.Logs;

import java.util.Random;

public class RandomUtils {
	private final static String TAG = "RandomUtils";
	
	public final static String NUM = "0123456789";
	public final static String CHAR_SMALL = "abcdefghijklmnopqrstuvwxyz";
	public final static String MIX = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public final static String getRandomStr(int length, String base){
		Logs.sysOut(StrUtils.join(TAG, ":", "getRandomStr: length = ", length, "<---> base = ", base));
		if(StrUtils.isEmpty(base)){
			base = MIX;
		}
		
		if(length < 1){
			length = 1;
		}else if(length > 100){
			length = 10;
		}
		
		Random r = getRandom();
		StringBuilder su = StrUtils.getStringBuilder();
		for(int i = 0; i < length; i++){
			int num = getInt(r, base.length());
			su.append(base.charAt(num));
		}
		return su.toString();
	}
	
	public final static int getRandomInt(int bound){
		return getInt(getRandom(), bound);
	}
	
	public final static Random getRandom(){
		return new Random();
	}
	
	private final static int getInt(Random r, int bound){
		if(r == null){
			return -1;
		}
		
		if(bound < 1){
			bound = 1;
		}
		
		return r.nextInt(bound);
	}
}