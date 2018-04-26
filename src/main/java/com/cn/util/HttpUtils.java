package com.cn.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/5.
 */
public class HttpUtils {
    /**
     * 基于java原生的URL和URLConnection发送get请求
     * @param urlPath     请求地址
     * @return response data
     */
    public static String sendGet(final String urlPath) {
        // 响应数据流
        InputStream inputStream = null;
        // 请求返回数据
        StringBuilder stringBuilder = new StringBuilder();
        try {
            // 新建一个URL对象
            URL url = new URL(urlPath);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            // 设置请求方法
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setRequestProperty("connection", "Keep-Alive");
            // 设置一系列请求头 ...
            httpUrlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            // httpUrlConnection.setRequestProperty(key, value);//设置请求头
            int responseCode = httpUrlConnection.getResponseCode();
            if (responseCode != 200) {
                return "request fail code" + responseCode;
            }
            inputStream = httpUrlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放流资源
            releaseResource(inputStream, null);
        }
        return stringBuilder.toString();
    }
    /**
     * 基于java原生的URL和URLConnection发送form表单类型post请求
     * @param urlPath     请求地址
     * @param params     请求提交参数
     * @return response data
     */
    public static String sendFormPost(final String urlPath, final Map<String, String> params) {
        // 响应数据流
        InputStream inputStream = null;
        // 请求输出流
        OutputStream outputStream = null;
        // 请求返回数据
        StringBuilder stringBuilder = new StringBuilder();
        try {
            // 新建一个URL对象
            URL url = new URL(urlPath);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            // 设置请求方法post请求
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("connection", "Keep-Alive");
            // 使用post请求数据时,最好要设置以下请求头
            httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpUrlConnection.setDoOutput(true);// 默认为false,如果使用post请求的话则一定要设置为true否则获取输出流救护失败。
            // httpUrlConnection.setDoInput(true);//默认已经为true,所以不需要设置
            // 赋值提交的post表单数据
            outputStream = httpUrlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            bufferedWriter.write(ConverterMapToParams(params));
            bufferedWriter.flush();

            int responseCode = httpUrlConnection.getResponseCode();
            if (responseCode != 200) {
                return "request fail code" + responseCode;
            }

            inputStream = httpUrlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放流资源
            releaseResource(inputStream, outputStream);
        }
        return stringBuilder.toString();
    }

    /**
     * 发送json格式的post请求
     * @param urlPath     请求地址
     * @param params     提交的json数据
     * @return
     */
    public static String sendJsonPost(final String urlPath, final String params) {

        // 响应数据流
        InputStream inputStream = null;
        // 请求输出流
        OutputStream outputStream = null;
        // 请求返回数据
        StringBuilder stringBuilder = new StringBuilder();

        try {
            // 新建一个URL对象
            URL url = new URL(urlPath);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            // 设置请求方法post请求
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("connection", "Keep-Alive");
            // 使用post请求数据时,最好要设置以下请求头
            httpUrlConnection.setRequestProperty("Content-Type", "application/json");
            httpUrlConnection.setDoOutput(true);// 默认为false,如果使用post请求的话则一定要设置为true否则获取输出流救护失败。
            // httpUrlConnection.setDoInput(true);//默认已经为true,所以不需要设置
            // 赋值提交的post表单数据
            outputStream = httpUrlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            bufferedWriter.write(params);
            bufferedWriter.flush();

            int responseCode = httpUrlConnection.getResponseCode();
            if (responseCode != 200) {
                return "request fail code" + responseCode;
            }

            inputStream = httpUrlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放流资源
            releaseResource(inputStream, outputStream);
        }
        return stringBuilder.toString();
    }

    /**
     * Map对象转为key=value&key2=value2字符串格式
     * @param params
     * @return
     */
    private static String ConverterMapToParams(final Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append(entry.getValue());
                stringBuilder.append("&");
            }
        }
        String result = stringBuilder.substring(0, stringBuilder.lastIndexOf("&"));
        return result;
    }

    /**
     * 释放流资源
     * @param in     输入流
     * @param out     输出流
     */
    private static void releaseResource(InputStream in, OutputStream out) {
        if (in != null) {
            try {
                in.close();
                in = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
                out = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 备注:
     * httpUrlConnection.setDoOutput(true);以后就可以使用conn.getOutputStream().write()
     * httpUrlConnection.setDoInput(true);以后就可以使用conn.getInputStream().read();
     * get请求用不到conn.getOutputStream()，因为参数直接追加在地址后面，因此默认是false。
     */
}
