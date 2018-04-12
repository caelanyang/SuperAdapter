package com.caelan.superrecycle.superadapter;

import android.support.annotation.NonNull;

import com.caelan.superrecycle.superadapter.SuperAdapter;

import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public interface DataSource {

    int getDataType(int position);

    int getDataCount();

    Object getData(int position);

    void setDataList(@NonNull List<?> dataList);

    void setSuperAdapter(@NonNull SuperAdapter superAdapter);


}
