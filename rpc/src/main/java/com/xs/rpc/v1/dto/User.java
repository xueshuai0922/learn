package com.xs.rpc.v1.dto;

import java.io.Serializable;

/**
 * @author xueshuai
 * @date 2021/5/3 14:32
 * @description
 */
public class User implements Serializable {
    private int age;
    private  String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
