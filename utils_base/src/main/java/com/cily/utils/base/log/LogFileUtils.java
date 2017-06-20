package com.cily.utils.base.log;

import com.cily.utils.base.file.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * user:cily
 * time:2017/6/13
 * desc:
 */
public class LogFileUtils {
    private final static class Util{
        final static LogFileUtils utils = new LogFileUtils();
    }

    public static LogFileUtils getInstance(){
        return Util.utils;
    }

    private ExecutorService writeLogThread;
    private LogRunnable logRunnable;

    private LogFileUtils(){
        inited = false;
        writeLogThread = Executors.newSingleThreadExecutor();
    }

    private boolean inited = false;
    public void init(String filePath, String fileName, String fileType){
        if (inited){
            return;
        }

        File fs = FileUtils.getFs(filePath);
        if (!FileUtils.fileExist(fs)){
            fs.mkdirs();
        }

        if (!FileUtils.fileExist(filePath)){
            throw new RuntimeException("The filePath {" + filePath + "} is not exists!");
        }

        if (!inited) {
            logRunnable = new LogRunnable(filePath, fileName, fileType);
            writeLogThread.execute(logRunnable);
            inited = true;
        }
    }

    public void saveLog(String msg){
        saveLogNow(msg, false);
    }

    public void stop(){
        if (logRunnable != null){
            logRunnable.stop();
        }
    }

    public void saveLogNow(String msg, boolean writeNow){
        if (!inited || logRunnable == null || !logRunnable.isRunning()){
            throw new RuntimeException("It's must be init before used!");
        }
        logRunnable.writeLog(msg, writeNow);
    }
}
