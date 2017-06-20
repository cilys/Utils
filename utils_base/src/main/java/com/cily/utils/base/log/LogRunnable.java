package com.cily.utils.base.log;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.file.FileUtils;

/**
 * user:cily
 * time:2017/6/13
 * desc:
 */

public class LogRunnable implements Runnable{
    private boolean running = false;
    private StringBuffer su;
    private String filePath, fileName, fileType;
    private boolean writeNow = true;

    public LogRunnable(String filePath, String fileName, String fileType){
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileType = fileType;
        running = true;
        su = new StringBuffer();
    }

    public LogRunnable(String filePath, String fileName, String fileType, boolean writeNow){
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileType = fileType;
        running = true;
        this.writeNow = writeNow;

        su = new StringBuffer();
    }

    public void writeLog(String msg){
        System.out.println("log data-" + msg);
        if (StrUtils.isEmpty(msg)){
            return;
        }
        System.out.println("LogRunnable = " + this + "---su = " + su +  "---size = " + su.length());
        su.append(msg);
//        synchronized (this) {
//            this.notify();
//        }
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
                String str = su.toString();
                System.out.println("writeNow = " + writeNow + "<--->str = " + str);
                int res = FileUtils.saveToFile(str, filePath, fileName, fileType);
                System.out.println("res = " + res);
                if (0 == res){
//                    su.delete(0, su.length());
                }else if (res == -2 || res == -3){
//                    su.delete(0, su.length());
                    running = false;
                }
            }else {
                if (su.length() >= 1024) {
                    String str = su.toString();
                    System.out.println("writeNow = " + writeNow + "<--->str = " + str);

                    int res = FileUtils.saveToFile(su.toString(), filePath, fileName, fileType);
                    System.out.println("save file res = " + res);
                    System.out.println("res = " + res);
                    if (0 == res) {
//                        su.delete(0, su.length());
                    } else if (res == -2 || res == -3) {
//                        su.delete(0, su.length());
                        running = false;
                    }
                }
            }

            synchronized (this){
                try {
                    wait(500);
                } catch (InterruptedException e) {
                    Logs.printException(e);
                }
            }
        }
    }
}
