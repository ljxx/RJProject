package com.ylx.rjproject.core.result;


import com.ylx.rjproject.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

public abstract class ResultCallBack<T> implements Callback<Result<T>> {
    @Override public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {
        if (response != null) {
            if (response.body().getCode() == Constants.NET_CODE_SUCCESS) {
                handlerResult(true, null, response.body().getData());
            } else {
                handlerResult(false, new Throwable(response.body().getDetail()), null);
            }
        } else {
            handlerResult(false, new Throwable(Constants.EMPTY_RESPONSE_EXCEPTION), null);
        }
    }

    @Override public void onFailure(Call<Result<T>> call, Throwable t) {
        handlerResult(false, t, null);
    }

    public abstract void handlerResult(boolean success, Throwable throwable, T t);
}
