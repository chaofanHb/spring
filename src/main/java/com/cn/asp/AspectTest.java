package com.cn.asp;

import com.cn.spring_rabbitMQ.MQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/28.
 */
@Component
public class AspectTest{
    static MQProducer mqProducer;
    @Autowired
    public void setMqProducer(MQProducer mqProducer){
        this.mqProducer=mqProducer;
    }
    public void test() {
        System.out.println(mqProducer);
        System.out.println("111111111111111111111111");
    }


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        AspectTest aspectTest= (AspectTest) applicationContext.getBean("aspectTest");//必须由spring容器创建并代理
        aspectTest.test();
    }
}
