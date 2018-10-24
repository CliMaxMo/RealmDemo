package com.example.realmdemo;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * 项目名     RealmDemo
 * 包名       com.example.realmdemo
 * 文件名     Person
 * 创建者     CMW
 * 创建时间   2018/10/24
 * 描述      TODO
 */

//public class Person extends RealmObject {
public class Person  {


    @Required
    private String name;

    private RealmList<Dog> mDog;

    private Dog mDogObject;



    public Dog getDogObject() {
        return mDogObject;
    }

    public void setDogObject(Dog dogObject) {
        mDogObject = dogObject;
    }

    public RealmList<Dog> getDog() {
        return mDog;
    }

    public void setDog(RealmList<Dog> dog) {
        mDog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
