package com.cn.util;

import org.dozer.DozerBeanMapper;

/**
 * Created by Administrator on 2017/9/11.
 */
public class BeanMapper {
    private static DozerBeanMapper dozer=new DozerBeanMapper();

    public static void copy(Object sounrce,Object destiObject){
        dozer.map(sounrce,destiObject);
    }




    public static void main(String[] args) {

    }
}
