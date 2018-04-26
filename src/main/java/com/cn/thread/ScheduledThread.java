package com.cn.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/17.
 */
public class ScheduledThread {
    private Logger logger= LoggerFactory.getLogger(ScheduledThread.class);
    private ScheduledThreadPoolExecutor exec=new ScheduledThreadPoolExecutor(1);
    private int delay;
    private volatile boolean isRunning = false;
    @Value("${msg}")
    private String msg;

    public synchronized void start() {
        if (exec == null) {
            exec = new ScheduledThreadPoolExecutor(1);
        }
        exec.scheduleWithFixedDelay(new LoadProtocol(), 5, delay, TimeUnit.SECONDS);
        isRunning = true;
    }

    public synchronized void stop() {
        if (isRunning) {
            exec.shutdown();
            exec = null;
            isRunning = false;
        }
    }
    public class LoadProtocol implements Runnable {
        public void run() {
            try {
                System.out.println(msg);
            } catch (Exception e) {
                logger.info("error:{}", e.getMessage());
            }
        }
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
