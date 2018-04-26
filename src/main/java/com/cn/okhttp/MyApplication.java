package com.cn.okhttp;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/6/19.
 */
public class MyApplication {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
    public static final MediaType XML = MediaType.parse("application/xml;charset=utf-8");
    static OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addNetworkInterceptor(new LogInterceptor()).build();

    //okHttpClient.dispatcher().setMaxRequests(1);
    public static void main(String[] args) {
        Map map=new HashMap();
        Request request =postuap();
        okHttpClient.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("请求成功");
                String json = response.body().string();
                System.out.println("返回报文：" + json);
            }
        });
    }

    public static Request postuap(){
        RequestBody body=RequestBody.create(JSON,"{\"id\":\"1\",\"domain\":\"mye.hk\"}");
        Request request = new Request.Builder().url("http://172.16.13.22:8081/uapapi/cache/user/getRoles").post(body)
                .header("Module-Name", "uapapi")
                .build();
        return request;
    }

    public static Request postFile( final Map<String, String> map) {
        String url="http://127.0.0.1:9801/recharge/callback/upload";
        File file=new File("D:\\addMoney.csv");
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("text/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key(随机字符用于前后包夹流) ，文件名称 ， RequestBody
            requestBody.addFormDataPart("avata", filename+";Content-Type:application/octet-stream", body);
        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for (Map.Entry entry : entries) {
                String key = String.valueOf(entry.getKey());
                String value = String.valueOf(entry.getValue());
                //Log.d("HttpUtils", "key=="+key+"value=="+value);
                requestBody.addFormDataPart(key,value);
            }
        }
        Request request = new Request.Builder().url(url).post(requestBody.build())
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Charset", "UTF-8")
                .addHeader("Connection","Keep-Alive")
                .build();
        return request;

    }

    public static Request recharge(String mobie, Integer packet, boolean nationwide) {
        RequestBody requestBody = RequestBody.create(FORM, "mobile=" + mobie + "&packet=" + packet + "&nationwide=" + nationwide);
        return new Request.Builder().url("http://192.168.99.100:8080/accept-order/flowpack/recharge")
                .addHeader("Authorization", getAuthorization())
                .post(requestBody).build();
    }

    public static Request getPackgeList(String mobie, Integer nationwide) {
        return new Request.Builder().url("http://127.0.0.1:8040/flowpack/list/" + mobie + "/" + nationwide)
                .addHeader("Authorization", getAuthorization())
                .get().build();
    }

    public static Request getOrderState(String serial) {
        return new Request.Builder().url("http://127.0.0.1:8040/flowpack/query/" + serial)
                .addHeader("Authorization", getAuthorization())
                .get().build();
    }

    public static Request getBalance() {
        return new Request.Builder().url("http://127.0.0.1:8040/flowpack/balance")
                .addHeader("Authorization", getAuthorization())
                .get().build();
    }

    public static Request mye() {
        return new Request.Builder().url("http://127.0.0.1:9801/recharge/merge")
                .addHeader("Authorization", getAuthorization())
                .addHeader("ceshi","123456")
                .get().build();
    }

    public static String getAuthorization() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY,-8);
        String timestamp = df.format(calendar.getTime());
        String nonce = Encodes.encodeBase64(("test1:" + timestamp).getBytes());
        String needHash = "test19d99207cd9c0404485dd49a7e9e92206" + timestamp;
        byte[] md5result = Digests.md5(needHash.getBytes());
        String sign2 = Encodes.encodeHex(md5result);
        return "sign=\"" + sign2 + "\",nonce=\"" + nonce + "\"";
    }
}
