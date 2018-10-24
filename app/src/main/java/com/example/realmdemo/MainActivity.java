package com.example.realmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private Button tv_add;
    private Realm mRealm;
    RealmResults<StudentInfoBean> realmResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRealm = Realm.getDefaultInstance();
        mRecyclerView = findViewById(R.id.recyclerview);
        mMainAdapter = new MainAdapter();

        mRecyclerView.setAdapter(mMainAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tv_add = findViewById(R.id.tv_add);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUserActivity.intentAddUserActivity(MainActivity.this, true, "");
            }
        });

        mMainAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                StudentInfoBean data = (StudentInfoBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.btn_update:
                        AddUserActivity.intentAddUserActivity(MainActivity.this, false, data.getId());
                        break;
                    case R.id.btn_delete:
//                        mMainAdapter.getData().remove(position);

                        mRealm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realmResults.deleteFromRealm(position);
                                mMainAdapter.notifyItemRemoved(position);
                            }
                        });
                        break;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        realmResults = mRealm.where(StudentInfoBean.class)
                .findAllAsync();


        realmResults.addChangeListener(new RealmChangeListener<RealmResults<StudentInfoBean>>() {
            @Override
            public void onChange(RealmResults<StudentInfoBean> studentInfoBeans) {

                mMainAdapter.setNewData(realmResults);

            }
        });


    }
}
