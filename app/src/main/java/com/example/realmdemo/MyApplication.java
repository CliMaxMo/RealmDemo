package com.example.realmdemo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * 项目名     SQLDemo
 * 包名       com.example.sqldemo
 * 文件名     MyApplication
 * 创建者     CMW
 * 创建时间   2018/10/9
 * 描述      TODO
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm") //文件名
                .schemaVersion(1) //版本
                .build();
       Realm.setDefaultConfiguration(config);

       //其他会用到的参数
       //encryptionKey() 指定数据库的密钥
       //migration(new StudentInfoBean()) 指定迁移操作的迁移类
       //inMemory()  声明数据库只在内存中持久化

    }

}
