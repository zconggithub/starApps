package com.starapp.entity;

import java.util.Date;

/*
*
 * @date:2018/10/1710:29
 * @author:Allen
 * @description:
*/

public class userBean {

    private String id;
    private String name;
    private String age;
    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "{" + id + "::" + name + "::" + age + "::" + country + "}";
    }
}

