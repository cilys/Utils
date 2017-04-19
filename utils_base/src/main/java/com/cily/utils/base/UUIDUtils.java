package com.cily.utils.base;

import java.util.UUID;

public class UUIDUtils {
	
	public final static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}