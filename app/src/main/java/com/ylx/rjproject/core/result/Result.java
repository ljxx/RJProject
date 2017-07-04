package com.ylx.rjproject.core.result;

import java.io.Serializable;

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
 * 描 述：网络请求结果
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
@SuppressWarnings("unused")
public class Result<T> implements Serializable {
    private int code;
    private String detail;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
