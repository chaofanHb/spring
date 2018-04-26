package com.cn.rabbitmq;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/6/7.
 */
public class Sender extends  BaseConnector {
    public Sender(String queue) throws IOException, TimeoutException {
        super(queue);
    }
    public void sendMessage(Serializable object) throws IOException {
        this.channel.basicPublish("",QUEUE_NAME,null, SerializationUtils.serialize(object));
    }
}
