package com.caelan.superrecycle;

import java.util.ArrayList;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public interface DataSource<T> {

    int getDataType(int position);

    int getDataCount();

    T getData(int position);

    void setDataList(ArrayList<T> dataList);

    ArrayList<T> getDataList();
}
