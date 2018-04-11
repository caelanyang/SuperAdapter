package com.caelan.superrecycle.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public interface DataSource<DATA> {

    int getDataType(int position);

    int getDataCount();

    DATA getDataList(int position);

    void setDataList(@NonNull ArrayList<DATA> dataList);

    ArrayList<DATA> getItemList();
}
