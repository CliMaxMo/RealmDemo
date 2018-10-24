package com.example.realmdemo;


import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * 项目名     SQLDemo
 * 包名       com.example.sqldemo
 * 文件名     StudentInfoBean
 * 创建者     CMW
 * 创建时间   2018/10/8
 * 描述      TODO
 */

@RealmClass
public class StudentInfoBean implements RealmModel {


    @PrimaryKey
    public String id = UUID.randomUUID().toString();
    public String name;
    public String age;
    public String sex;


    public String getId() {
        return id;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}
