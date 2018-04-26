package com.cn.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Created by Administrator on 2017/6/7.
 */
public class Recv {
    public static final String QUEUE_NAME="queue";

    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory=new ConnectionFactory();
            connectionFactory.setHost("127.0.0.1");
            Connection connection=connectionFactory.newConnection();
            Channel channel=connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            System.out.println("waiting for message!!!");

            QueueingConsumer consumer=new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME,true,consumer);
            int i=1;
            while (true){
                QueueingConsumer.Delivery delivery=consumer.nextDelivery();
                String mes=new String(delivery.getBody());
                System.out.println(i+"-"+mes);
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
