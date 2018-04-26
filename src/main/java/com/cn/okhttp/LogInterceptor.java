package com.cn.okhttp;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/12.
 */
public class LogInterceptor implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        System.out.println(request.toString());

        Response response = chain.proceed(request);

        System.out.println(response);

        return response;
    }
}
