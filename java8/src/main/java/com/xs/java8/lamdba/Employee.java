package com.xs.java8.lamdba;

import java.util.List;
import java.util.Objects;

/**
 * @author xueshuai
 * @date 2021/1/24 20:01
 * @description
 */

public class Employee {
    private int age;
    private String name;
    private List sons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                name.equals(employee.name) &&
                sons.equals(employee.sons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, sons);
    }

    public List getSons() {
        return sons;
    }

    public void setSons(List sons) {
        this.sons = sons;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Employee(int age, String name, List sons) {
        this.age = age;
        this.name = name;
        this.sons = sons;
    }

    public Employee(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Employee(int age) {
        this.age = age;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
