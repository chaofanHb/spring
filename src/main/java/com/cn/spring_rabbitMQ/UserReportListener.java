package com.cn.spring_rabbitMQ;

import com.cn.test.Temp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/9.
 */
@Component
public class UserReportListener implements MessageListener {

    public void onMessage(Message message) {
        try {
            String channeMessage = new String(message.getBody(), "utf-8");
            System.out.println("收到MQ消息："+new String(channeMessage));
            ObjectMapper mapper=new ObjectMapper();
            Temp temp= mapper.readValue(channeMessage, Temp.class);
            System.out.println("收到MQ对象消息"+temp.toString());
        } catch (Exception e) {
            System.out.println("MQ接受异常");
            e.printStackTrace();
        }
    }
}
