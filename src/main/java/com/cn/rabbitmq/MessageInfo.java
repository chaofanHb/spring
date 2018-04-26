package com.cn.rabbitmq;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/7.
 */
public class MessageInfo implements Serializable {

    private  String channel;
    private String content;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
