package com.rss.feedhub.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/5 20:43
 * @Description:
 */
@Component
public class DateUtil {
    public static String dealDateFormatReverse(String oldDateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date date = (Date) sdf.parse(oldDateStr);
        String formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return formatStr;
    }
}
