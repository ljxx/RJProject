package com.ylx.rjproject.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.ylx.rjproject.core.eventbus.MsgEvent;
import com.ylx.rjproject.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * ========================================
 * <p/>
 * 版 权：蓝吉星讯 版权所有 （C） 2017
 * <p/>
 * 作 者：yanglixiang
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2017/7/3  上午11:10
 * <p/>
 * 描 述：
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class BaseActivity extends RxAppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        EventBus.getDefault().register(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 显示Toast消息
     */
    protected void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 跳转到指定到Activity
     * 下文获取传参请使用getIntent().getIntExtra()方式,getIntent().getExtras()可能引起空指针
     */
    protected void skipToActivity(Class<?> clazz, Bundle bundle){
        Intent intent = new Intent(this, clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转到指定的Activity并结束当前Activity
     * 下文获取传参请使用getIntent().getIntExtra()方式,getIntent().getExtras()可能引起空指针
     */
    protected void skipToActivityAndFinish(Class<?> clazz, Bundle bundle){
        skipToActivity(clazz, bundle);
        finish();
    }

    /**
     * 带返回的跳转到指定Activity
     * 下文获取传参请使用getIntent().getIntExtra()方式,getIntent().getExtras()可能引起空指针
     */
    protected void skipToActivityForResult(Class<?> clazz, Bundle bundle, int requestCode){
        Intent intent = new Intent(this, clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 关闭Activity，并返回参数
     */
    protected void closeActivity(Bundle bundle){
        if(bundle != null){
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    /**
     * 订阅消息事件 ???
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void onMessageEvent(MsgEvent event){
        Logger.t("onMessageEvent").d("code = " + event.getCode() + ", message = " + event.getMsg());
    }

    /**
     * 获取SharedPreferences
     */
    protected SharedPreferences getSharedPreferences(){
        return Utils.getDefaultSharedPrefrences();
    }

    /**
     * 保存SharedPreferences ,根据传入类型自动转换
     */
    protected void setSharedPreferences(String keyName, Object value) {
        if(value instanceof Boolean){
            getSharedPreferences().edit().putBoolean(keyName, (Boolean) value).apply();
        } else if(value instanceof Integer) {
            getSharedPreferences().edit().putInt(keyName, (Integer) value).apply();
        } else if(value instanceof String) {
            getSharedPreferences().edit().putString(keyName, (String) value).apply();
        } else if(value instanceof Float) {
            getSharedPreferences().edit().putFloat(keyName, (Float) value).apply();
        } else if(value instanceof Long) {
            getSharedPreferences().edit().putLong(keyName, (Long) value).apply();
        }
    }

    /**
     * 移除SharedPreferences
     */
    protected void removeSharedPreferences(String key){
        getSharedPreferences().edit().remove(key).apply();
    }

    /**
     * 清空SharedPreferences
     */
    protected void clearSharedPreferences(){
        getSharedPreferences().edit().clear().apply();
    }

    /**
     * 关闭软盘
     */
    protected void closeKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null){
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
