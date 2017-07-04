package com.ylx.rjproject;

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
@SuppressWarnings("unused")
public class Constants {
    public static final int NET_CODE_SUCCESS = 0;
    public static final int NET_CODE_ERROR = 1;

    public static final int NET_CODE_CONNECT = 400;
    public static final int NET_CODE_UNKNOWN_HOST = 401;
    public static final int NET_CODE_SOCKET_TIMEOUT = 402;

    public static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    public static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    public static final String UNKNOWN_HOST_EXCEPTION = "与服务器连接失败";
    public static final String EMPTY_RESPONSE_EXCEPTION = "无效的返回";
}
