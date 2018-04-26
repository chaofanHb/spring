package com.cn.timeTask;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/18.
 */
public class OutputJob {
    public void print(){
        for (int i = 0; i <10 ; i++) {
            System.out.println(new Date());
        }
    }
}
