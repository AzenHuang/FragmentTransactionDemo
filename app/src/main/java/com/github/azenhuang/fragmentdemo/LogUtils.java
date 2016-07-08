package com.github.azenhuang.fragmentdemo;

import android.util.Log;

/**
 * Created by huangyongzheng on 6/16/16.
 */
public class LogUtils {
    private static boolean DEBUGED = true;
    public static void d (Object tagObject, String msg){
        if (DEBUGED) {
            Log.d(tagObject.toString(), msg);
        }
    }

    public static void d (Object tagObject, String msg, Throwable tr){
        if (DEBUGED) {
            Log.d(tagObject.toString(), msg, tr);
        }
    }
}
