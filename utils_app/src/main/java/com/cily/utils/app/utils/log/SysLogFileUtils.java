package com.cily.utils.app.utils.log;

import android.content.Context;
import android.os.Environment;

import com.cily.utils.app.Init;
import com.cily.utils.base.StrUtils;
import com.cily.utils.base.time.TimeType;
import com.cily.utils.base.time.TimeUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * user: cily
 * time: 2016/11/15
 * desc: 日志生成文件
 */

public class SysLogFileUtils {
    private static SysLogFileUtils util = null;
    private static String PATH_LOGCAT;
    private LogDumper mLogDumper = null;
    private int mPId;

    public static SysLogFileUtils getInstance() {
        if (util == null) {
            synchronized (SysLogFileUtils.class){
                if (util == null) {
                    util = new SysLogFileUtils();
                }
            }
        }
        return util;
    }

    private SysLogFileUtils() {
        mPId = android.os.Process.myPid();
    }

    private void startLogcatToFile(Context context, String cmd) {
        String folderPath = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            folderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                    + "Logcat";
        } else {
            folderPath = StrUtils.join(context.getFilesDir().getAbsolutePath(), File.separator, "Logcat");
        }
        SysLogFileUtils.getInstance().start(folderPath,cmd);
    }

    public void startLogcatToFile(Context context){
        if (!Init.isDebug()) {
            return;
        }

        startLogcatToFile(context, null);
    }

    public void stopLogcatToFile() {
        if (!Init.isDebug()) {
            return;
        }

        SysLogFileUtils.getInstance().stop();
    }

    private void setFolderPath(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("The logcat folder path is not a directory: " + folderPath);
        }

        PATH_LOGCAT = folderPath.endsWith("/") ? folderPath : folderPath + "/";
    }

    private void start(String saveDirectoy,String cmd) {
        setFolderPath(saveDirectoy);
        if (mLogDumper == null) {
            mLogDumper = new LogDumper(String.valueOf(mPId), PATH_LOGCAT, cmd);
        }
        mLogDumper.start();
    }

    private void stop() {
        if (mLogDumper != null) {
            mLogDumper.stopLogs();
            mLogDumper = null;
        }
    }

    private class LogDumper extends Thread {
        private Process logcatProc;
        private BufferedReader mReader = null;
        private boolean mRunning = true;
        String cmds = null;
        private String mPID;
        private FileOutputStream out = null;

        public LogDumper(String pid, String dir, String cmd) {
            mPID = pid;
            try {
                out = new FileOutputStream(new File(dir, StrUtils.join("logcat-", TimeUtils.milToStr(
                        System.currentTimeMillis(), TimeType.SECOND_LINE_UNDER), ".log")), true);
            } catch (FileNotFoundException e) {
                L.printException(e);
            }

            /**
             * * * log level：*:v , *:d , *:w , *:e , *:f , *:s * * Show the
             * current mPID process level of E and W log. * *
             */
            // cmds = "logcat *:e *:w | grep \"(" + mPID + ")\"";
            if( cmd == null)
                cmds = "logcat | grep \\(" + mPID + "\\)";
            else
                cmds = cmd;
        }

        public void stopLogs() {
            mRunning = false;
        }

        @Override
        public void run() {
            try {
                logcatProc = Runtime.getRuntime().exec(cmds);
                mReader = new BufferedReader(new InputStreamReader(logcatProc.getInputStream()), 1024);
                String line = null;
                while (mRunning && (line = mReader.readLine()) != null) {
                    if (!mRunning) {
                        break;
                    }
                    if (line.length() == 0) {
                        continue;
                    }
                    if (out != null && line.contains(mPID)) {
                        out.write((TimeUtils.milToStr(System.currentTimeMillis(), TimeType.SECONDLINE_COLON) + "  " + line + "\n").getBytes());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (logcatProc != null) {
                    logcatProc.destroy();
                    logcatProc = null;
                }
                if (mReader != null) {
                    try {
                        mReader.close();
                        mReader = null;
                    } catch (IOException e) {
                        L.printException(e);
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        L.printException(e);
                    }
                    out = null;
                }
            }
        }
    }
}
