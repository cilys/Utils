package com.cily.utils.base.log;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.file.FileType;
import com.cily.utils.base.file.FileUtils;
import com.cily.utils.base.io.StreamToStr;

public class Logs {
	private static int level = LogType.ALL;
	private static boolean writeLog = false;
	private static boolean consoleLog = true;
	
	public static void sysOut(String msg){
		if(getLevel() >= LogType.DEBUG && msg != null){
			if (consoleLog) {
				System.out.println(msg);
			}
			writeLog(LogType.TYPE_DEBUG, LogType.TAG_SYS_OUT, msg);
		}
	}
	
	public static void printException(Throwable e){
		if(getLevel() >= LogType.EXCEPTION && e != null){
			if (consoleLog) {
				e.printStackTrace();
			}
			writeLog(LogType.TYPE_EXCEPTION, LogType.TAG_THROWABLE, StreamToStr.throwableToStr(e));
		}
	}

	public static void sysErr(String msg){
		if (getLevel() >= LogType.ERROR && msg != null){
			if (consoleLog) {
				System.err.println(msg);
			}
			writeLog(LogType.TYPE_ERROR, LogType.TAG_SYS_ERR, msg);
		}
	}
	
	public static int getLevel() {
		return level;
	}

	public static void setLevel(int l) {
		if(l < LogType.NONE){
			l = LogType.NONE;
		}else if(l > LogType.ALL){
			l = LogType.ALL;
		}
	}
	public static void setWriteLog(boolean w){
		writeLog = w;
	}
	public static boolean isWriteLog(){
		return writeLog;
	}

	public static void setConsoleLog(boolean c){
		consoleLog = c;
	}
	public static boolean isConsoleLog(){
		return consoleLog;
	}

	protected static void writeLog(String logType, String tag, String msg){
		if (!isWriteLog()){
			return;
		}

		if (StrUtils.isEmpty(filePath)){
			return;
		}
		if (FileUtils.fileExist(filePath)){
			return;
		}

		try{
			LogFileUtils.getInstance().init(filePath, fileName, FileType.TXT);
			LogFileUtils.getInstance().saveLog(FileUtils.formcatLog(logType, tag, msg));
		}catch (RuntimeException e){
			printException(e);
		}
	}

	private static String filePath, fileName;
	public static void setLogFile(String filePath, String fileName){
		Logs.filePath = filePath;
		Logs.fileName = fileName;
	}
}