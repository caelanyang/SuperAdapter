package com.caelan.superadapter;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public interface DataSource {

    int getDataType(int position);

    int getDataCount();

    Object getData(int position);

    void setDataList(@NonNull List<Object> dataList);

    void setSuperAdapter(@NonNull SuperAdapter superAdapter);

    void setNewDataList(@NonNull List<Object> newDataList);

    void removeData(int position);

    void removeDataRange(int position, int dataCount);

    void insertData(@NonNull Object data, int position);

    void insertDataRange(@NonNull List<Object> insertDataList, int position);

    void addAll(@NonNull List<Object> addedDataList, int position);

    void addAll(@NonNull List<Object> addedDataList);

    void moveData(int fromPosition, int toPosition);

}
