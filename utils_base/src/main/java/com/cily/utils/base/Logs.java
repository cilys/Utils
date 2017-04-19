package com.cily.utils.base;

public class Logs {
	public final static int NONE = -1;
	public final static int ALL = 6;
	public final static int VERBOSE = 6;
	public final static int DEBUG = 5;
	public final static int INFO = 4;
	public final static int WARN = 3;
	public final static int ERROR = 2;
	public final static int EXCEPTION = 1;
	
	private static int level = ALL;
	
	public static void sysOut(String msg){
		if(getLevel() >= INFO && msg != null){
			System.out.println(msg);
		}
	}
	
	public static void printException(Throwable e){
		if(getLevel() >= EXCEPTION && e != null){
			e.printStackTrace();
		}
	}

	public static void sysErr(String msg){
		if (getLevel() >= ERROR && msg != null){
			System.err.println(msg);
		}
	}
	
	public static int getLevel() {
		return level;
	}

	public static void setLevel(int l) {
		if(l < NONE){
			l = NONE;
		}else if(l > ALL){
			l = ALL;
		}
	}
}