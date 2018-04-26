package com.cn.okhttp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/25.
 */
public class IUsername implements Serializable {
    private static final long serialVersionUID = 8090229398854030692L;

    private String username;

    public String getUsername() {
        return username;
    }

    private String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper =new ObjectMapper();
        IUsername u=new IUsername();
        u.setDomain("mye.hk");
        u.setUsername("admin");
        try {
            String str= objectMapper.writeValueAsString(u);
            System.out.println(str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}