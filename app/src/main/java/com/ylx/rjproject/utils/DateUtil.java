package com.ylx.rjproject.utils;

import android.content.Context;
import com.ylx.rjproject.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
 * 描 述：日期时间工具类
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
@SuppressWarnings("unused")
public class DateUtil {

    /**
     * 按照格式获取当前的日期
     */
    public static String currentDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(new Date().getTime());
    }

    /**
     * 格式化Long型日期,secondLevel为true时会转换为毫秒级再格式化
     */
    public static String formatDate(String pattern, long date, boolean secondLevel) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(new Date(date * (secondLevel ? 1000L : 1L)));
    }

    /**
     * 获取今天星期几
     */
    public static String getDayOfWeek(Context context) {
        String[] weekDays = context.getResources().getStringArray(R.array.week);
        Calendar calendar = Calendar.getInstance();
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w < 0 ? 0 : w];
    }
}
