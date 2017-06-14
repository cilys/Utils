package com.cily.utils.base.log;

import com.cily.utils.base.file.FileUtils;

import java.io.FileNotFoundException;

/**
 * user:cily
 * time:2017/6/13
 * desc:
 */

public class LogRunnable implements Runnable{
    private boolean running = false;
    private StringBuilder su;
    private String filePath, fileName, fileType;
    private boolean writeNow = false;

    public LogRunnable(String filePath, String fileName, String fileType){
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileType = fileType;
        running = true;
        su = new StringBuilder();
    }

    public LogRunnable(String filePath, String fileName, String fileType, boolean writeNow){
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileType = fileType;
        running = true;
        this.writeNow = writeNow;

        su = new StringBuilder();
    }

    public void writeLog(String msg){
        su.append(msg);
    }

    public void writeLog(String msg, boolean write){
        this.writeNow = write;

        writeLog(msg);
    }

    public boolean isRunning(){
        return running;
    }

    public void stop(){
        running = false;
    }

    @Override
    public void run() {
        while (running){
            if (writeNow){
                int res = FileUtils.saveToFile(su.toString(), filePath, fileName, fileType);
                if (0 == res){
                    su.delete(0, su.length());
                }else if (res == -2 || res == -3){
                    su.delete(0, su.length());
                    running = false;
                }
                writeNow = false;
            }else {
                if (su.length() >= 1024) {
                    int res = FileUtils.saveToFile(su.toString(), filePath, fileName, fileType);
                    if (0 == res) {
                        su.delete(0, su.length());
                    } else if (res == -2 || res == -3) {
                        su.delete(0, su.length());
                        running = false;
                    }
                }
            }

            try {
                wait(500);
            } catch (InterruptedException e) {
                Logs.printException(e);
            }
        }
    }
}
