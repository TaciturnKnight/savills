package com.bamboo.savills.base.utils;

public class StringUtil {

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }


    public static String getHtmlString(String color, String text) {
        return "<font color=" + color + ">" + text + "</font>";
    }
}
