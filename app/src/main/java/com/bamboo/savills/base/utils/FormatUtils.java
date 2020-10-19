package com.bamboo.savills.base.utils;

import java.text.DecimalFormat;

/**
 * 作者： qyj
 * 功能： 格式化工具
 * 创建日期： 2017/5/9
 */

public class FormatUtils {

    /**
     * 保留两位小数
     *
     * @param values
     * @return
     */
    public static String getKeepTwoDecimalPlaces(double values) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(values);
    }

    /**
     * 获取中间部分的时间
     * @param string
     * @return
     */
    public static String getMiddleMessage(String string){
        return string.substring(1,string.length()-1).trim();
    }

    /**
     * 返回要显示的字符串心心
     * @param values
     * @param defaultStr
     * @return
     */
    public static String getShowCustomInfo(int values, String defaultStr){
        return values == -1 ? defaultStr : String.valueOf(values);
    }

    /**
     * 时间
     * @param time
     * @return
     */
    public static String getTime(String time){
        long timeL = Long.parseLong(time);
        long m = timeL/60;
        long s = timeL%60;
        String min = m < 10 ?  "0" + m : String.valueOf(m);
        String sec = s < 10 ?  "0" + s : String.valueOf(s);
        return min + ":" + sec;
    }

    /**
     * 将时分转换
     *
     * @param timeStr
     * @return
     */
    public static int getTimeNumber(String timeStr) {
        if (timeStr.contains(":")) {
            return Integer.parseInt(timeStr.replace(":", ""));
        } else {
            return 0;
        }
    }
}
