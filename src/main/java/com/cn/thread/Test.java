package com.cn.thread;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/7/17.
 */
public  class  Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        ThreadService t= (ThreadService) applicationContext.getBean("threadService");
        t.testPZ();
    }

}
