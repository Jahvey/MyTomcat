package com.huai.test;

/**
 * Created by liangyh on 3/23/16.
 */
public class Singleton {

    private String name = "name1";
    private int age = 1;

    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    private Singleton(){}

    public static Singleton instance(){
        return Nested.instance;
    }

    public static class Nested{
        public static Singleton instance = new Singleton();
    }
}
