package com.cn.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MsgSendListener implements ApplicationListener<MsgSendEvent> {

    public void onApplicationEvent(MsgSendEvent event) {
        System.out.println(event.toString());
    }

}