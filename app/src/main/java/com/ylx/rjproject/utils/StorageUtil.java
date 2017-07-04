package com.ylx.rjproject.utils;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;
import com.ylx.rjproject.base.BaseApp;

import java.io.File;
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
 * 描 述：App应用目录管理
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
@SuppressWarnings("unused")
public class StorageUtil {
    /**
     * 判断是否是文件路径
     */
    public static boolean isFilePath(String path) {
        return new File(path).isDirectory();
    }

    /**
     * 获取应用的根目录
     */
    public static String getRoot() {
        String rootPath = BaseApp.getAppContext().getAppRootPath();
        return Environment.getExternalStorageDirectory() + "/" + rootPath + "/";
    }

    /**
     * 在应用根路径下创建文件夹（可以多级）
     */
    public static String create(@NonNull String dirName) {
        return createDir(getRoot() + dirName);
    }

    /**
     * 在某个路径下创建文件夹（可以多级）
     */
    public static String create(@NonNull String root, @NonNull String dirName) {
        return createDir(root + dirName);
    }

    private static String createDir(String path) {
        File dir = new File(path);
        if (!dir.exists() && dir.mkdirs()) {
            return path;
        }
        return path;
    }

    /**
     * 存储对象到SharePreferences文件中
     */
    public static void saveObjectToFile(String key, Object obj, boolean isEncrypt) {
        SharedPreferences sp = Utils.getDefaultSharedPrefrences();
        //本地化账户信息
        String json = new Gson().toJson(obj);
        if (isEncrypt) {
            json = Base64.encodeToString(json.getBytes(), Base64.DEFAULT);
        }
        sp.edit().putString(key, json).apply();
    }

    /**
     * 从SharePreferences文件中获取对象
     */
    public static Object parseObjectFromFile(String key, Class clazz, boolean isEncrypt) {
        SharedPreferences sp = Utils.getDefaultSharedPrefrences();
        String json = sp.getString(key, null);
        if (TextUtils.isEmpty(json)) return null;

        if (isEncrypt) {
            json = new String(Base64.decode(json.getBytes(), Base64.DEFAULT));
        }
        return new Gson().fromJson(json, clazz);
    }
}
