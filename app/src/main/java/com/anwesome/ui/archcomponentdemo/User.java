package com.anwesome.ui.archcomponentdemo;

import android.arch.lifecycle.LiveData;

/**
 * Created by anweshmishra on 24/05/17.
 */

public class User {
    private String name;
    private int age;
    public void setName(String name) {
        this.name = "Anwesh";
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public int hashCode() {
        return name.hashCode()+age;
    }
    public User(String name,int age) {
        this.name = name;
        this.age = age;
    }
}
