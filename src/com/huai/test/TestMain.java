package com.huai.test;

/**
 * Created by liangyh on 3/12/16.
 */
public class TestMain {

    public static void main(String[] args) {
        Singleton singleton = Singleton.instance();
        System.out.println(singleton.getAge());
        singleton.setAge(3);
        System.out.println(singleton.getAge());

        Singleton singleton1 = Singleton.instance();
        System.out.println(singleton1.getAge());
        singleton1.setAge(4);
        System.out.println(singleton1.getAge());
    }
}
