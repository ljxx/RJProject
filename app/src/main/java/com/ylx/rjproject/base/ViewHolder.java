package com.ylx.rjproject.base;

import android.view.View;

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
 * 创建日期：2017/7/3  下午6:08
 * <p/>
 * 描 述：
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public abstract class ViewHolder {

    public ViewHolder(View view){
        ButterKnife.bind(this, view);
    }

    public abstract void initData(int position);
}
