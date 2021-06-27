package com.cn.jasmine.demo.entity;

import java.lang.reflect.Field;

public class ObjectToString {

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        //获取class对象a中声明的所有字段
        Field[] field = this.getClass().getDeclaredFields();
        for(int i=0;i<field.length;i++){
            //设置是否允许访问，不是修改原来的访问权限修饰词。
            field[i].setAccessible(true);
            //返回输出指定对象a上此 Field表示的字段名和字段值
            try {
                stringBuilder.append("\""+field[i].getName()+"\":"+field[i].get(this)+",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(stringBuilder.length()>2){
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

}
