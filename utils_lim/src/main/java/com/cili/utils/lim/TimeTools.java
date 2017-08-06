package com.cili.utils.lim;

import com.cily.utils.base.RandomUtils;
import com.cily.utils.base.time.TimeType;
import com.cily.utils.base.time.TimeUtils;

public class TimeTools {
	private final static long getOutTime(String outTime){
		return TimeUtils.strToMil(outTime, TimeType.SECONDLINE_COLON, System.currentTimeMillis());
	}
	
	private final static long d2(){
//		return getOutTime("2018-01-01 00:00:02");
//		Logs.sysOut("" + getOutTime("2018-01-01 00:00:02"));
		return 1514736002000L;
	}
	
	private final static long d1(){
//		return getOutTime("2017-05-01 02:22:22");
//		Logs.sysOut("" + getOutTime("2017-05-01 02:22:22"));
		return 1493576542000L;
	}
	
	private final static long d0(){
//		return getOutTime("2017-04-20 12:21:56");
//		Logs.sysOut("" + getOutTime("2017-04-20 12:21:56"));
		return 1492662116000L;
	}
	
	public static void main(String[] args){
//		d2();
//		d1();
//		d0();
	}
	
	//��Ƶ��
	protected final static boolean isHigh(){
		return System.currentTimeMillis() >= d2() && getRandomInt(100) <= 80;
	}
	
	//��Ƶ��
	protected final static boolean isNormal(){
		return System.currentTimeMillis() >= d1() && getRandomInt(100) <= 50 ;
	}
	
	//��Ƶ��
	protected final static boolean isSmall(){
		return System.currentTimeMillis() >= d0() && getRandomInt(100) <= 10;
	}
	
	//����ʱ��
	protected final static boolean isAll(){
		return getRandomInt(100) <= 1;
	}
	
	private final static int getRandomInt(int bound){
		return RandomUtils.getRandomInt(bound);
	}
}