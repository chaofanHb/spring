package com.cn.asp;


import com.cn.spring_rabbitMQ.MQProducer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Administrator on 2017/7/28.
 */
@Component
@Aspect
public class MyIntercept {



    @Pointcut("execution(* com.cn.asp.AspectTest.*(..))")
    public void test(){

    }
    @Before("execution(* com.cn.asp.AspectTest.*(..))")
    public void before(){
        System.out.println("这是一个前置通知");
    }
    @AfterReturning("test()")
    public void afterReturning(){
        System.out.println("这是一个返回通知");
    }
    @Around("test()")
    public void around(ProceedingJoinPoint joinpoint){
        System.out.println("Around 前:");
        try {
            joinpoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Around 后:");
    }
    @AfterThrowing(value = "execution(* com.cn.asp.AspectTest.*(..))",throwing="e")
    public void exception(Exception e){
        System.out.println("这是一个异常通知");
    }
    @After("test()")
    public void after(){
        System.out.println("这是一个后置通知");
    }
}
