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
     * ����javaԭ����URL��URLConnection����get����
     * @param urlPath     �����ַ
     * @return response data
     */
    public static String sendGet(final String urlPath) {
        // ��Ӧ������
        InputStream inputStream = null;
        // ���󷵻�����
        StringBuilder stringBuilder = new StringBuilder();
        try {
            // �½�һ��URL����
            URL url = new URL(urlPath);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            // �������󷽷�
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setRequestProperty("connection", "Keep-Alive");
            // ����һϵ������ͷ ...
            httpUrlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            // httpUrlConnection.setRequestProperty(key, value);//��������ͷ
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
            // �ͷ�����Դ
            releaseResource(inputStream, null);
        }
        return stringBuilder.toString();
    }
    /**
     * ����javaԭ����URL��URLConnection����form������post����
     * @param urlPath     �����ַ
     * @param params     �����ύ����
     * @return response data
     */
    public static String sendFormPost(final String urlPath, final Map<String, String> params) {
        // ��Ӧ������
        InputStream inputStream = null;
        // ���������
        OutputStream outputStream = null;
        // ���󷵻�����
        StringBuilder stringBuilder = new StringBuilder();
        try {
            // �½�һ��URL����
            URL url = new URL(urlPath);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            // �������󷽷�post����
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("connection", "Keep-Alive");
            // ʹ��post��������ʱ,���Ҫ������������ͷ
            httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpUrlConnection.setDoOutput(true);// Ĭ��Ϊfalse,���ʹ��post����Ļ���һ��Ҫ����Ϊtrue�����ȡ������Ȼ�ʧ�ܡ�
            // httpUrlConnection.setDoInput(true);//Ĭ���Ѿ�Ϊtrue,���Բ���Ҫ����
            // ��ֵ�ύ��post������
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
            // �ͷ�����Դ
            releaseResource(inputStream, outputStream);
        }
        return stringBuilder.toString();
    }

    /**
     * ����json��ʽ��post����
     * @param urlPath     �����ַ
     * @param params     �ύ��json����
     * @return
     */
    public static String sendJsonPost(final String urlPath, final String params) {

        // ��Ӧ������
        InputStream inputStream = null;
        // ���������
        OutputStream outputStream = null;
        // ���󷵻�����
        StringBuilder stringBuilder = new StringBuilder();

        try {
            // �½�һ��URL����
            URL url = new URL(urlPath);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            // �������󷽷�post����
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("connection", "Keep-Alive");
            // ʹ��post��������ʱ,���Ҫ������������ͷ
            httpUrlConnection.setRequestProperty("Content-Type", "application/json");
            httpUrlConnection.setDoOutput(true);// Ĭ��Ϊfalse,���ʹ��post����Ļ���һ��Ҫ����Ϊtrue�����ȡ������Ȼ�ʧ�ܡ�
            // httpUrlConnection.setDoInput(true);//Ĭ���Ѿ�Ϊtrue,���Բ���Ҫ����
            // ��ֵ�ύ��post������
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
            // �ͷ�����Դ
            releaseResource(inputStream, outputStream);
        }
        return stringBuilder.toString();
    }

    /**
     * Map����תΪkey=value&key2=value2�ַ�����ʽ
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
     * �ͷ�����Դ
     * @param in     ������
     * @param out     �����
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
     * ��ע:
     * httpUrlConnection.setDoOutput(true);�Ժ�Ϳ���ʹ��conn.getOutputStream().write()
     * httpUrlConnection.setDoInput(true);�Ժ�Ϳ���ʹ��conn.getInputStream().read();
     * get�����ò���conn.getOutputStream()����Ϊ����ֱ��׷���ڵ�ַ���棬���Ĭ����false��
     */
}
