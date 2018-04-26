package com.cn.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class HttpMessageSender {
    private String msgUrl="http://msg-api.chinamye.com.cn:8081/send_internal";
    private String smsUrl="http://msg-api.chinamye.com.cn:8081/send_sms";

    public HttpMessageSender() {
    }

    public HttpResponse send(String fromUser, String toUser, String content, String sign, String type) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(this.msgUrl);

        try {
            ArrayList e = new ArrayList();
            e.add(new BasicNameValuePair("fromUser", fromUser));
            e.add(new BasicNameValuePair("toUser", toUser));
            e.add(new BasicNameValuePair("content", content));
            e.add(new BasicNameValuePair("sign", sign));
            e.add(new BasicNameValuePair("type", type));
            httpPost.setEntity(new UrlEncodedFormEntity(e, "UTF-8"));
            CloseableHttpResponse var9 = httpClient.execute(httpPost);
            return var9;
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }

        return null;
    }

    public HttpResponse sendSms(String fromUser, String toUser, String content) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(this.smsUrl);

        try {
            ArrayList e = new ArrayList();
            e.add(new BasicNameValuePair("fromUser", fromUser));
            e.add(new BasicNameValuePair("toUser", toUser));
            e.add(new BasicNameValuePair("content", content));
            e.add(new BasicNameValuePair("type", "text/sms"));
            httpPost.setEntity(new UrlEncodedFormEntity(e, "UTF-8"));
            CloseableHttpResponse var7 = httpClient.execute(httpPost);
            return var7;
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }

        return null;
    }

    public void setMsgUrl(String msgUrl) {
        this.msgUrl = msgUrl;
    }

    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }
}

