package com.cn.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/6/7.
 */
public class BaseConnector {
    protected Connection connection;
    protected Channel channel;
    protected String QUEUE_NAME;

    public  BaseConnector(String queue) throws IOException, TimeoutException {
        this.QUEUE_NAME=queue;
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connection=connectionFactory.newConnection();
        channel=connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
    }
}
