package com.ylx.rjproject.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

/**
 * ========================================
 * <p/>
 * 版 权：蓝吉星讯 版权所有 （C） 2017
 * <p/>
 * 作 者：yanglixiang
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2017/7/3  下午5:12
 * <p/>
 * 描 述：
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public final class Utils {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static boolean debug;

    private Utils(){
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     * @param context
     */
    public static void init(@NonNull Context context){
        init(context, false);
    }

    /**
     * 初始化工具类
     * @param context
     * @param debug
     */
    public static void init(@NonNull Context context, boolean debug){
        Utils.mContext = context.getApplicationContext();
        Utils.debug = debug;
        if(debug){
//            Logger.init().methodCount(1).hideThreadInfo();
        }
    }

    /**
     * 获取App Context
     * @return
     */
    public static Context getContext(){
        if(mContext != null) return mContext;
        throw new NullPointerException("u should init first");
    }

    /**
     * 判断是否在Debug模式
     */
    public static boolean isDebug(){
        return debug;
    }

    /**
     * 获取默认的SharedPreferences
     */
    public static SharedPreferences getDefaultSharedPrefrences(){
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    /**
     * 获取App名称
     */
    public static String getAppName(){
        try{
            PackageManager packageManager = Utils.getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            return packageInfo != null ? packageInfo.applicationInfo.loadLabel(packageManager).toString() : null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
