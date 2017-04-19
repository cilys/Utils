package com.cily.utils.app.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * user:cily
 * time:2017/4
 * desc:SD卡工具类
 */
public class SDCardUtils {

    /**
     * 判断外部存储卡是否可用
     * @return
     */
    public final static boolean isExternalStorageAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    /**
     * 获取SD卡总容量，SD卡不存在，返回0
     * @return
     */
    public final static long getSDCardTotalMemory(){
        if (!isExternalStorageAvailable()){
            return 0L;
        }

        return getTotalMemory(Environment.getExternalStorageDirectory());
    }

    public final static String getSDCardTotalMemoryStr(){
        return formetSize(getSDCardTotalMemory());
    }

    /**
     * 获取SD卡剩余容量，SD卡不存在，返回0
     * @return
     */
    public final static long getSDCardAvailableMemory(){
        if (!isExternalStorageAvailable()){
            return 0L;
        }

        return getAailableMemory(Environment.getExternalStorageDirectory());
    }

    public final static String getSDCardAvailableMemoryStr(){
        return formetSize(getSDCardAvailableMemory());
    }

    /**
     * 获取内部存储总容量
     * @return
     */
    public final static long getInternalTotalMemory(){
        return getTotalMemory(Environment.getDataDirectory());
    }

    public final static String getInternalTotalMemoryStr(){
        return formetSize(getInternalTotalMemory());
    }

    /**
     * 获取内存存储剩余容量
     * @return
     */
    public final static long getInternalAvaillableMemory(){
        return getAailableMemory(Environment.getDataDirectory());
    }

    public final static String getInternalAvaillableMemoryStr(){
        return formetSize(getInternalAvaillableMemory());
    }

    /**
     * 获取磁盘总容量
     * @param path
     * @return
     */
    public final static long getTotalMemory(File path){
        if (path == null){
            return 0L;
        }
        StatFs sf = new StatFs(path.getPath());
        long blockSize = sf.getBlockSize();   // 获得一个扇区的大小
        long totalBlocks = sf.getBlockCount();    // 获得扇区的总数
        return totalBlocks * blockSize;
    }

    /**
     * 获取磁盘可用容量
     * @param path
     * @return
     */
    public final static long getAailableMemory(File path){
        if (path == null){
            return 0L;
        }
        StatFs sf = new StatFs(path.getPath());
        long blockSize = sf.getBlockSize();   // 获得一个扇区的大小
        long availableBlocks = sf.getAvailableBlocks();   // 获得可用的扇区数量
        return availableBlocks * blockSize;
    }

    public final static String formetSize(long size){
        if (size <= 0){
            return "0";
        }
        DecimalFormat df = new DecimalFormat("#.00");
        if (size < 1024){
            return df.format((double)size) + "B";
        }else if (size < 1048576){
            return df.format((double)size / 1024) + "K";
        }else if (size < 1073741824){
            return df.format((double)size / 1048576) + "M";
        }else {
            return df.format((double)size / 1073741824) + "G";
        }
    }

    /**
     * 获取拍照相片存储文件
     * @param context
     * @return
     */
    public static File createFile(Context context){
        File file;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            String timeStamp = String.valueOf(new Date().getTime());
            file = new File(Environment.getExternalStorageDirectory() +
                    File.separator + timeStamp+".jpg");
        }else{
            File cacheDir = context.getCacheDir();
            String timeStamp = String.valueOf(new Date().getTime());
            file = new File(cacheDir, timeStamp+".jpg");
        }
        return file;
    }

    /**
     * 获取磁盘缓存文件
     * @param context
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
}
