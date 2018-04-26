package com.cn.thread;

import com.cn.util.PropertyConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/1/9.
 */
@Service
public class ThreadService {
    private ExecutorService es= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);;
    @Autowired
    PropertyConfigurer propertyConfigurer;

    public void testPZ(){
        TestThread t=new TestThread();
        es.submit(t);
    }

    class TestThread implements Runnable{

        public void run() {
            System.out.println(propertyConfigurer.getProperty("hebin"));
        }
    }

}
