package com.cn;

import com.cn.util.HttpMessageSender;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/7/28.
 */
public class Test {
    private static Logger logger= LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        System.out.println("1111");
        //MailService mailService= (MailService) applicationContext.getBean("mailServiceImpl");
        //mailService.sendSimpleMail("hebin@mye.hk", "主题：这是HTML邮件","www.baidu.com");
/*        System.out.println(System.getenv("hebin"));


        MQProducer mqProducer= (MQProducer) applicationContext.getBean("mqProducer");
        Temp msg=new Temp();
        msg.setId(1);
        msg.setTest(null);
        Temp msg1=new Temp();
        msg1.setId(2);
        msg1.setTest(null);
        mqProducer.sendDataToQueue("k1", msg);
        mqProducer.sendDataToQueue("k2",msg1);*/


        HttpMessageSender httpMessageSender= (HttpMessageSender) applicationContext.getBean("httpMessageSender");
        HttpResponse response = httpMessageSender.send("hebin@cike.com","1019@cike.com","{\"fileName\":\"Koala.jpg\",\"fileSize\":780831,\"from\":1,\"id\":\"20180300000005\",\"month\":201803,\"offset\":-1,\"type\":2,\"url\":\"https://files.chinamye.com.cn/M00/01/57/CgoD-Fq5ouSAeJLUAAvqH_kipG8880.jpg\"}"
                ,"1019","text/netdisk");

        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println("发送云盘文件共享提醒错误");
        }
    }

}
