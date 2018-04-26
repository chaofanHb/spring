package com.cn.util;

/**
 * Created by Administrator on 2018/1/2.
 */
public class RestAuthUtils {
    private static final ThreadLocal<String> threadSession = new ThreadLocal();

    public RestAuthUtils() {
    }

    public static void cleanThreadLocalSession() {
        threadSession.remove();
    }


    public static String getSession() {
        return threadSession.get();
    }

    public static void setSession(String s) {
        threadSession.set(s);
    }


}
