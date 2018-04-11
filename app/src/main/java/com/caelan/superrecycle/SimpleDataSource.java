package com.caelan.superrecycle;

import java.util.ArrayList;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public class SimpleDataSource<T> implements DataSource<T> {

    ItemAdapter mItemAdapter;

    @Override
    public int getDataType(int position) {
        return 0;
    }

    @Override
    public int getDataCount() {
        return 0;
    }

    @Override
    public T getData(int position) {
        return null;
    }

    @Override
    public void setDataList(ArrayList<T> dataList) {

    }

    @Override
    public ArrayList<T> getDataList() {
        return null;
    }
}
