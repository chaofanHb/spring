package com.cn.test;

/**
 * Created by Administrator on 2017/6/9.
 */
public class Temp {
    private int id;
    private Test test;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "id=" + id +
                ", test=" + test +
                '}';
    }
}
