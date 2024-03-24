package com.gogym.apiserver.utils;

import java.util.UUID;

public class CommonUtil {
//    public static void main(String args[]) {
//        System.out.println(NewResourceId("res"));
//    }

    public static String NewResourceId(String prefix) {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replace("-", "");


        return prefix + "-0" + id;
    }
}
