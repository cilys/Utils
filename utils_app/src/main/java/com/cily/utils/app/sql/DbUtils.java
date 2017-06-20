package com.cily.utils.app.sql;

import android.content.Context;
import android.os.Environment;

import com.litesuits.orm.LiteOrm;

import java.io.File;
import java.util.List;

/**
 * user:cily
 * time:2017/6/15
 * desc:
 */

public class DbUtils {
    private static LiteOrm liteOrm;
    private static boolean saveLog = false;
    public static void init(Context cx){
        init(cx, false);
    }

    public static void init(Context cx, boolean saveLog){
        DbUtils.saveLog = saveLog;

        if (liteOrm == null){
            liteOrm = LiteOrm.newSingleInstance(cx, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "db_log.db");
        }
    }

    public static void setSaveLog(boolean saveLog){
        DbUtils.saveLog = saveLog;
    }

    public static boolean isSaveLog(){
        return saveLog;
    }

    public static boolean insert(LogBean b){
        if (!saveLog){
            return false;
        }
        return b != null && liteOrm != null && liteOrm.insert(b) > -1;
    }

    public static boolean update(LogBean b){
        if (!saveLog){
            return false;
        }

        return b != null && liteOrm != null && liteOrm.update(b) > -1;
    }

    public static boolean del(LogBean b){
        if (!saveLog){
            return false;
        }

        return b != null && liteOrm != null && liteOrm.delete(b) > -1;
    }

    public static List<LogBean> searchAll(){
        return liteOrm == null ? null : liteOrm.query(LogBean.class);
    }
}
