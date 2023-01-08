package com.gogym.apiserver.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static boolean validPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$");
        Matcher matcher = pattern.matcher(number);
        if (matcher.matches()) {
            System.out.println("Valid phone number: " + number);
            return true;
        } else {
            System.out.println("Invalid. Not the form XXX-XXXX-XXX: " + number);
            return false;
        }
    }
}
