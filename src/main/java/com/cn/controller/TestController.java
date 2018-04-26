package com.cn.controller;

import org.apache.james.user.api.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
@Controller
@RequestMapping(value = "/hebin/")
public class TestController {



    @RequestMapping(value = "getEnv")
    @ResponseBody
    public String getEnv(){
        return "test controller";
    }

    public static void main(String[] args) {
        List<User> list=new ArrayList<User>();
        for (User user: list){
            System.out.println(user);
        }
    }
}
