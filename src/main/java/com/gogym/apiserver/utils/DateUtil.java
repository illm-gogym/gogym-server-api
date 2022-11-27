package com.gogym.apiserver.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date stringToDate(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(time);

        } catch (ParseException parseException) {
            System.out.println("parse err : " + parseException.getMessage());
            return null;
        }
    }

    public static Date stringToFullDate(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return formatter.parse(time);

        } catch (ParseException parseException) {
            System.out.println("parse err : " + parseException.getMessage());
            return null;
        }
    }

}
