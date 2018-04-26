package com.cn.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/7.
 */
public class RedisClient {
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    //��ȡJedis
    public Jedis getResource() throws UnsupportedEncodingException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            System.out.println(new String("can't  get  the redis resource".getBytes(), "utf-8"));
        }
        return jedis;
    }
    //�ر�����
    public void disconnect(Jedis jedis) {
        jedis.disconnect();
    }
    //��redis���ظ����ӳ�
    public void returnResource(Jedis jedis) throws UnsupportedEncodingException {
        if (null != jedis) {
            try {
                jedisPool.returnResource(jedis);
            } catch (Exception e) {
                System.out.println(new String("can't return jedis to jedisPool".getBytes(), "utf-8"));
            }
        }
    }
    //�ͷ�jedis�ͻ��˶���
    public void brokenResource(Jedis jedis) throws UnsupportedEncodingException {
        if (jedis != null) {
            try {
                jedisPool.returnBrokenResource(jedis);
            } catch (Exception e) {
                System.out.println(new String("can't release jedis Object".getBytes(), "utf-8"));
            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        RedisTemplate redisTemplate= (RedisTemplate) applicationContext.getBean("redisTemplate");
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        System.out.println(redisUtil.get("orderIndex_201709040941171001"));
    }
}
