package com.cn.spring_rabbitMQ;

/**
 * Created by Administrator on 2017/6/9.
 */
public interface MQProducer {
    public void sendDataToQueue(String quen,Object object);
}
