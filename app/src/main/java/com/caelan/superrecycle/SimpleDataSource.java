package com.caelan.superrecycle;

import android.support.annotation.NonNull;

import com.caelan.superrecycle.data.DataSource;

import java.util.ArrayList;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public class SimpleDataSource<T> implements DataSource<T> {

    ItemAdapter mItemAdapter;

    SuperAdapter mAdapter;



    public SimpleDataSource(SuperAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    public int getDataType(int position) {
        return 0;
    }

    @Override
    public int getDataCount() {
        return 0;
    }

    @Override
    public T getDataList(int position) {
        return null;
    }

    @Override
    public void setDataList(@NonNull ArrayList<T> dataList) {

    }

    @Override
    public ArrayList<T> getItemList() {
        return null;
    }
}
