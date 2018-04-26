package com.cn.redis2;

import com.cn.redis.RedisUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */
public class TestRedis2 {
    public TestRedis2() {

    }

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
    RedisTemplate template = (RedisTemplate) applicationContext.getBean("redisTemplate");
    RedisUtil redisUtil = new RedisUtil();

    @Test
    public void testObject() {
        redisUtil.setRedisTemplate(template);
        List<TestUser> lists = buildTestData();
        TestUser userA = lists.get(0);
        ObjectsTranscoder<TestUser> objTranscoder = new ObjectsTranscoder<TestUser>();
        //对象序列化
        byte[] result = objTranscoder.serialize(userA);
        //redisUtil.set("user",result);
        //获取缓存对象
        byte[] result1= (byte[]) redisUtil.get("user");
        //转为对象
        TestUser userA_userA = (TestUser) objTranscoder.deserialize(result1);
        System.out.println(userA_userA.getName() + "\t" + userA_userA.getAge()+"\t"+userA.getPassword());
    }

    @Test
    public void testList() {
        redisUtil.setRedisTemplate(template);
        List<TestUser> lists = buildTestData();
        ListTranscoder<TestUser> listTranscoder = new ListTranscoder<TestUser>();
        //list转为字节流
        byte[] result = listTranscoder.serialize(lists);
        redisUtil.set("list", result);
        //获取缓存List
        byte[]result1= (byte[]) redisUtil.get("list");
        //转为list
        List<TestUser> results = (List<TestUser>) listTranscoder.deserialize(result1);
        for (TestUser user : results) {
            System.out.println(user.getName() + "\t" + user.getAge()+"\t"+user.getPassword());
        }

    }

    private static List<TestUser> buildTestData() {
        TestRedis2 tst = new TestRedis2();
        TestUser userA = new TestUser();
        userA.setName("lily");
        userA.setAge(25);
        userA.setPassword("110110110");

        TestUser userB = new TestUser();
        userB.setName("Josh Wang");
        userB.setAge(28);
        userB.setPassword("120120120");

        List<TestUser> list = new ArrayList<TestUser>();
        list.add(userA);
        list.add(userB);

        return list;
    }
}
