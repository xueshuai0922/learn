package com.xs.javabase.reflect;


import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xueshuai
 * @date 2021/5/13 15:46
 * @description 将map置换为实体（实体可以包含list.map 其他实体）
 */
public class MapToBeanUtils {

    public static <T> T mapToBean(Map map, Class<T> clazz) {
        if (map == null) return null;

        T t = null;
        try {
            //反射出对象
            t = clazz.newInstance();
            Field[] declaredFields = clazz.getDeclaredFields();

            for (Field field : declaredFields) {
                field.setAccessible(true);
                //属性的类型（List  实体类型，或者基本数据类型）
                Class<?> fieldType = field.getType();
                String name = field.getName();
                //如果存在 实体字段的key
                if (map.containsKey(name)) {
                    Object fieldMap = map.get(name);
                    //如果返回的是个map,递归
                    if (fieldMap instanceof Map) {
                        Object o = mapToBean((Map) fieldMap, fieldType);
                        //进行set map
                        field.set(t, o);
                    }
                    //如果返回的是list
                    else if (fieldMap instanceof List && fieldType.getName().contains("List")) {
                        List<Map> filedList = (List<Map>) fieldMap;
                        T finalT = t;
                        ArrayList<Object> list = new ArrayList<>();
                        filedList.forEach((s) -> {
                            try {
                                //获取list<entity> 中的class name
                                String typeName = ((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0].getTypeName();
                                Class<?> aClass = Class.forName(typeName);
                                //递归
                                Object result = mapToBean(s, aClass);
                                list.add(result);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });
                        //进行set  list
                        field.set(finalT, list);
                    }
                    //正常赋值
                    else {
                        field.set(t, fieldMap);
                    }
                }
            }


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static void main(String[] args) {

        HashMap<Object, Object> cardMap1 = new HashMap<>();
        cardMap1.put("brand", "CMCC");
        HashMap<Object, Object> cardMap2 = new HashMap<>();
        cardMap2.put("brand", "CMMM");

        ArrayList<Object> carList = new ArrayList<>();
        carList.add(cardMap1);
        carList.add(cardMap2);


        HashMap<Object, Object> phoneMap1 = new HashMap<>();
        phoneMap1.put("num", 123333333);
        phoneMap1.put("cards", carList);
        HashMap<Object, Object> phoneMap2 = new HashMap<>();
        phoneMap2.put("num", 22222233);
        phoneMap2.put("cards", carList);

        ArrayList<Object> phoneList = new ArrayList<>();
        phoneList.add(phoneMap1);
        phoneList.add(phoneMap2);

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("age", 12);
        hashMap.put("phones", phoneList);


        HashMap<Object, Object> shoneMap = new HashMap<>();
        shoneMap.put("size", 233);
        shoneMap.put("name", "xsss");

        hashMap.put("shone", shoneMap);

        System.out.println(hashMap);

        Student student = mapToBean(hashMap, Student.class);
        System.out.println(student);
    }


}

class Student {
    private int age;
    private List<Phone> phones;
    private shone shone;

    public shone getShone() {
        return shone;
    }

    public void setShone(shone shone) {
        this.shone = shone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

}

class shone {
    private int size;
    private String name;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Phone {
    private int num;
    private List<Card> cards;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}

class Card {
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
