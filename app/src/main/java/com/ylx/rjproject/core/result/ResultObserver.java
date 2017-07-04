package com.ylx.rjproject.core.result;

import com.ylx.rjproject.Constants;
import com.ylx.rjproject.core.eventbus.MsgEvent;

import org.greenrobot.eventbus.EventBus;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
 * 描 述：网络请求结果处理
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
@SuppressWarnings("unused")
public abstract class ResultObserver<T> implements Observer<Result<T>> {
    @Override public void onSubscribe(Disposable d) {

    }

    @Override public void onNext(Result<T> result) {
        if (result != null) {
            if (result.getCode() == Constants.NET_CODE_SUCCESS) {
                handlerResult(result.getData());
            } else {
                handlerError(result.getCode(), result.getDetail());
            }
        } else {
            handlerError(Constants.NET_CODE_ERROR, Constants.EMPTY_RESPONSE_EXCEPTION);
        }
    }

    @Override public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            handlerError(Constants.NET_CODE_SOCKET_TIMEOUT, Constants.SOCKET_TIMEOUT_EXCEPTION);
        } else if (e instanceof ConnectException) {
            handlerError(Constants.NET_CODE_CONNECT, Constants.CONNECT_EXCEPTION);
        } else if (e instanceof UnknownHostException) {
            handlerError(Constants.NET_CODE_UNKNOWN_HOST, Constants.UNKNOWN_HOST_EXCEPTION);
        } else {
            handlerError(Constants.NET_CODE_ERROR, e.getMessage());
        }
    }

    @Override public void onComplete() {

    }

    /**
     * 返回正常数据
     */
    public abstract void handlerResult(T t);

    /**
     * 返回业务错误
     */
    public void handlerError(int code, String msg) {
        EventBus.getDefault().post(new MsgEvent(code, msg));
    }

}
