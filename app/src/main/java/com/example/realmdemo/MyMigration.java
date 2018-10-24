package com.example.realmdemo;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * 项目名     RealmDemo
 * 包名       com.example.realmdemo
 * 文件名     MyMigration
 * 创建者     CMW
 * 创建时间   2018/10/24
 * 描述      TODO
 */

public class MyMigration implements RealmMigration {
    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema realmSchema = realm.getSchema();

        if (oldVersion == 1) {
            realmSchema.create("Person")
                    .addField("name", String.class);

            oldVersion++;
        }

        if (oldVersion == 2) {
            realmSchema.get("Person")
                    .setRequired("name", true)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            DynamicRealmObject person = realm.createObject("Person");
                            person.set("name", "John");


                        }
                    });
            oldVersion++;
        }


        if (oldVersion == 3) {

            RealmObjectSchema realmObjectSchema = realmSchema.create("Dog")
                    .addField("name", String.class);

            realmSchema.get("Person")
                    .addRealmObjectField("mDogObject", realmObjectSchema)
                    .addRealmListField("mDog", realmObjectSchema);
            oldVersion++;
        }
    }
}
