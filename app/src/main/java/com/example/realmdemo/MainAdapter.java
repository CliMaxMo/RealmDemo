package com.example.realmdemo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * 项目名     SQLDemo
 * 包名       com.example.sqldemo
 * 文件名     MainAdapter
 * 创建者     CMW
 * 创建时间   2018/10/8
 * 描述      TODO
 */

public class MainAdapter extends BaseQuickAdapter<StudentInfoBean, BaseViewHolder> {
    public MainAdapter() {
        super(R.layout.item);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudentInfoBean item) {
        helper.setText(R.id.tv_name,  item.getName());
        helper.setText(R.id.tv_age, item.getAge());
        helper.setText(R.id.tv_sex, item.getSex());

        helper.addOnClickListener(R.id.btn_update);
        helper.addOnClickListener(R.id.btn_delete);
    }
}
