package com.cily.utils.base.time;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.log.Logs;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtils {
	private final static String TAG = "TimeUtils";

	public final static String milToStr(long time, String format){
//		Logs.sysOut(StrUtils.join(TAG, "-->", "milToStr: time = ", time, "<--->format = ", format));
		if(StrUtils.isEmpty(format)){
			format = TimeType.SECONDLINE_COLON;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			return sdf.format(time);
		}catch (IllegalArgumentException e) {
			Logs.printException(e);
		}
		return null;
	}

	public final static SimpleDateFormat getSdf(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf;
	}
	
	public final static long strToMil(String d, String format, long defValue){
//		Logs.sysOut(StrUtils.join(TAG, "-->", "milToStr: date = ", d, "<--->format = ", format, "<--->defValue = ", defValue));
		
		if(StrUtils.isEmpty(format)){
			format = TimeType.SECONDLINE_COLON;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			return sdf.parse(d).getTime();
		}catch (ParseException e) {
			Logs.printException(e);
		}
		return defValue;
	}
}