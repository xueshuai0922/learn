package com.xs.thread.t04_threadlocal;

/**
 * @author xueshuai
 * @date 2021/3/4 9:18
 * @description
 */
public class ThreadLocalUtil {
    private static ThreadLocal<User> local = new ThreadLocal<User>();

    public static User getLocal() {
       return local.get();
    }

    public static void setLocal(User user) {
        local.set(user);
    }

    public static  void  removeLocal(){
        local.remove();
    }
    static class User{
        private int age;
        private String name;

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
    }
}
