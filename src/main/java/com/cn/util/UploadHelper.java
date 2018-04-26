package com.cn.util;


import okhttp3.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/9/6.
 */
public class UploadHelper {
    private static final Logger logger=Logger.getLogger("UploadHelper");
    public static final String TAG = "UploadHelper";
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/jpeg");
    private static final MediaType MEDIA_TYPE_WORD = MediaType.parse("application/msword");
    private static  final OkHttpClient client = new OkHttpClient();
    //   头信息+文件+尾部
    //--标识符+"\r\n"+"Content-Disposition: form-data;name=\"file\";filename=\"+ file.getName() + "\"\r\nContent-Type:application/octet-stream\r\n\r\n"
    // +文件流
    // +"\r\n--标识符--\r\n"
    public static void main(String[] args) {
        System.out.println(upload("D:\\soft\\Tencent\\QQ\\969752912\\FileRecv\\flow_channel.sql","http://127.0.0.1:9801/recharge/callback/upload"));
    }
    public static String upload(String filed,String url){
        if(filed==null||filed.trim().equals("")){
            return "file is null";
        }else if (!new File(filed).exists()){
            return "file is not exists";
        }
        File file=new File(filed);
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_WORD, file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"file\"; filename=\"" + file.getName() + "\""),
                        fileBody)
                .addFormDataPart("imagetype", file.getName().substring(file.getName().indexOf(".")))
/*                .addFormDataPart("file", "head_image", fileBody)
                .addFormDataPart("imagetype", "png")
                .addFormDataPart("userphone", "1234")*/
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            String jsonString = response.body().string();
            logger.info(TAG+" upload jsonString ="+jsonString);
            if(!response.isSuccessful()){
                logger.info("请求失败");
                System.out.println("upload error code "+response);
            }else{
                logger.info("请求成功");
            }

        } catch (IOException e) {
            logger.info(TAG + "upload IOException " + e);
        }
        return null;
    }
}
