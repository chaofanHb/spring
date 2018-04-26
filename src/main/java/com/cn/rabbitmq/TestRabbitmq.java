package com.cn.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/6/7.
 */
public class TestRabbitmq {
    public static void main(String[] args) {
        try {
            Resver resver=new Resver("queue");
            Thread recverThread=new Thread(resver);
            recverThread.start();
            Sender sender=new Sender("queue");
            for (int i = 0; i <5 ; i++) {
                MessageInfo info=new MessageInfo();
                info.setChannel("test");
                info.setContent("msg"+i);
                sender.sendMessage(info);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
