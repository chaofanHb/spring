package com.cn.reflect;

import com.cn.test.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
public class ReflectParse<T> {
    public T pasre(List<Object> values, T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                Field field = fields[i];
                Object fieldValue = FieldReflectionUtil.parseValue(field, values.get(i).toString());
                field.setAccessible(true);
                field.set(t, fieldValue);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    public static void main(String[] args) {
        ReflectParse<Test> reflectParse=new ReflectParse<Test>();
        List<Object> list=new ArrayList<Object>();
        list.add("zhangsan");
        list.add("123456");
        list.add(110);
        Test test= reflectParse.pasre(list, new Test());
        System.out.println(test.toString());
    }
}
