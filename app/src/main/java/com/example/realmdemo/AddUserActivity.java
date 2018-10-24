package com.example.realmdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class AddUserActivity extends AppCompatActivity {


    Realm mRealm;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_age)
    EditText mEtAge;
    @BindView(R.id.et_sex)
    EditText mEtSex;
    @BindView(R.id.btn_update)
    Button mBtnUpdate;
    @BindView(R.id.btn_add)
    Button mBtnAdd;

    private String id;

    private boolean isAdd;

    public static void intentAddUserActivity(Context context, boolean isAdd,  String id) {
        context.startActivity(new Intent(context, AddUserActivity.class).putExtra("isAdd", isAdd).putExtra("id",id));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        ButterKnife.bind(this);

        isAdd = getIntent().getBooleanExtra("isAdd", false);
        mRealm = Realm.getDefaultInstance();
        if (isAdd) {
            mBtnAdd.setVisibility(View.VISIBLE);
            mBtnUpdate.setVisibility(View.GONE);


        } else {

            id = getIntent().getStringExtra("id");
            StudentInfoBean studentInfoBean=mRealm.where(StudentInfoBean.class).equalTo("id",id).findFirst();

            mEtName.setText(studentInfoBean.getName());
            mEtAge.setText(studentInfoBean.getAge());
            mEtSex.setText(studentInfoBean.getSex());
            mBtnAdd.setVisibility(View.GONE);
            mBtnUpdate.setVisibility(View.VISIBLE);
        }


    }

    @OnClick({R.id.btn_update, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                upData();
                break;
            case R.id.btn_add:
                add();

                break;

        }
        finish();
    }

    private void upData() {

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                StudentInfoBean studentInfoBean = realm.where(StudentInfoBean.class).equalTo("id", id).findFirst();
                studentInfoBean.setAge(mEtAge.getText().toString());
                studentInfoBean.setName(mEtName.getText().toString());
                studentInfoBean.setSex(mEtSex.getText().toString());

            }
        });

    }


    private void add() {

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                StudentInfoBean studentInfoBean = new StudentInfoBean();
                studentInfoBean.setAge(mEtAge.getText().toString());
                studentInfoBean.setName(mEtName.getText().toString());
                studentInfoBean.setSex(mEtSex.getText().toString());
                realm.insertOrUpdate(studentInfoBean);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null)
            mRealm.close();
    }


}
