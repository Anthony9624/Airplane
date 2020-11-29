package com.airticket.client.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class desc :
 *
 * @author :
 */
public class StringUtil {

    public static boolean isPhoneNum(String phoneNum) {
        Pattern p = Pattern.compile("^[1][3-9]\\d{9}$");
        Matcher m = p.matcher(phoneNum);
        return m.matches();
    }
}
