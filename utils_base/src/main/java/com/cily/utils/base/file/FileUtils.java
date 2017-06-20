package com.cily.utils.base.file;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.log.LogType;
import com.cily.utils.base.time.TimeType;
import com.cily.utils.base.time.TimeUtils;
import com.cily.utils.base.log.Logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtils {

    /**
     * save file
     * @param str
     * @param filePath
     * @param fileName
     * @param fileType
     * @return  0   save file success
     *          -1  The msg to write is null
     *          -2  The filePath is null
     *          -3  The file not found
     *          -4  error
     */
    public static int saveToFile(String str, String filePath, String fileName,
                                  String fileType){
        if (StrUtils.isEmpty(str)){
            return -1;
        }
        if (StrUtils.isEmpty(filePath)) {
            return -2;
        }

        if (StrUtils.isEmpty(fileName)){
            fileName = createFileNameByDate(null, fileType);
        }

        File fs = new File(filePath);
        if (!fs.exists()){
            fs.mkdirs();
        }

        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(fileName, true);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(str);
            return 0;
        }catch (FileNotFoundException e){
            e.printStackTrace();
//            Logs.printException(e);
            return -3;
        }catch (IOException e){
//            Logs.printException(e);
            e.printStackTrace();
            return -4;
        }finally {
            try{
                if (fos != null){
                    fos.close();
                }

                if (bw != null){
                    bw.close();
                }
            }catch (IOException e){
//                Logs.printException(e);
                e.printStackTrace();
            }
        }
    }

    public static File getFs(String filePath){
        if (StrUtils.isEmpty(filePath)){
            return null;
        }
        return new File(filePath);
    }

    public static boolean fileExist(File f){
        return f != null && f.exists();
    }

    public static boolean fileExist(String filePath){
        if (StrUtils.isEmpty(filePath)){
            return false;
        }
        File f = new File(filePath);
        return f.exists();
    }

    public static String createFileNameByDate(String timeType, String fileType){
        return String.format("%1s%2s", TimeUtils.milToStr(System.currentTimeMillis(),
                StrUtils.isEmpty(timeType) ? TimeType.SECOND_LINE_UNDER : timeType),
                StrUtils.isEmpty(fileType) ? FileType.TXT : fileType);
    }

    public static void main(String[] args){
        Logs.sysOut(createFileNameByDate(null, null));
    }

    public static int saveLog(String logType, String tag, String msg, String filePath,
                               String fileName, String fileType){
        if (StrUtils.isEmpty(logType)){
            logType = LogType.TYPE_ALL;
        }
        if (StrUtils.isEmpty(tag)){
            tag = "";
        }

        String log = formcatLog(logType, tag, msg);

        return saveToFile(log, filePath, fileName, fileType);
    }

    public static String formcatLog(String logType, String tag, String msg){
        return String.format("%1s [%2s, %3s] %4s", TimeUtils.milToStr(
                System.currentTimeMillis(), null), logType, tag, msg);
    }
}
