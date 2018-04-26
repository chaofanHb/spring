package com.cn.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.stringtemplate.v4.ST;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/6/7.
 */
public class Send {
    private static final String QUEUE_NAME = "queue";

    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("127.0.0.1");
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //String Message = "hello,-------------------0-0";
            String message = getBody();
            ST st = new ST(message, '$', '$');
            st.add("id", 1);
            st.add("name", "hb");
            st.add("password", "123456");
            st.add("age", "10");

            channel.basicPublish("", QUEUE_NAME, null, st.render().getBytes());

            System.out.println("send Over");


            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    public static String getBody() {
        String body = "{" +
                "  \"id\": \"$id$\"," +
                "  \"test\": {" +
                "  \"name\": \"$name$\"," +
                "  \"password\": \"$password$\"," +
                "  \"age\": \"$age$\"" +
                "  }\n" +
                "}";
        return body;
    }

}
