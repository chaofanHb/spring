package com.cn.spring_rabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/9.
 */
@Service("mqProducer")
public class MQProducerImpl implements MQProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    String queue="k1";

    public void sendDataToQueue(String quen, Object object) {
        try {
            System.out.println("k1发送消息");
            amqpTemplate.convertAndSend(quen, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
