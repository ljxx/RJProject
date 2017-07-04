package com.ylx.rjproject.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.ylx.rjproject.utils.Utils;

import io.realm.Realm;

/**
 * ========================================
 * <p/>
 * 版 权：蓝吉星讯 版权所有 （C） 2017
 * <p/>
 * 作 者：yanglixiang
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2017/7/3  下午5:49
 * <p/>
 * 描 述：
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public abstract class BaseApp extends Application {

    private static BaseApp appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        /**
         * 初始化工具类
         */
        Utils.init(appContext);

        /**
         * 初始化数据库
         */
        Realm.init(appContext);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(appContext);
    }

    public static BaseApp getAppContext(){
        return appContext;
    }

    /**
     * 设置debug模式,推荐返回app的BuildConfig.DEBUG
     */
    public abstract boolean debugMoe();

    /**
     * 配置应用文件保存的根路径
     */
    public abstract String getAppRootPath();
}
