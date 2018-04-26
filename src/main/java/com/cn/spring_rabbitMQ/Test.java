package com.cn.spring_rabbitMQ;


import com.cn.test.Temp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/6/9.
 */
public class Test {
    final String queue_key = "k1";


    public void send(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        MQProducer mqProducer= (MQProducer) applicationContext.getBean("mqProducer");
/*        Map<String,Object> msg = new HashMap<String,Object>();
        //msg.put("data","hello,rabbmitmq!");
        Map<String,String> map=new HashMap<String, String>();
        map.put("name", "HeBin");
        map.put("password", "123456");
        map.put("age","100");
        msg.put("id","0001");
        msg.put("test",map);*/
        Temp msg=new Temp();
        msg.setId(1);
        msg.setTest(null);
        Temp msg1=new Temp();
        msg1.setId(2);
        msg1.setTest(null);
        mqProducer.sendDataToQueue(queue_key, msg);
        mqProducer.sendDataToQueue("k2",msg1);
    }
    public static String getBody() {
        String body = " {" +
                " \" name\": \"$name$\"," +
                " \" password\": \"$password$\"," +
                "  \"age\": \"$age$\"" +
                "  }";
        return body;
    }
}
