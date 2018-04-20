package com.caelan.superadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public interface DataSource<Model> {

    int getDataType(int position);

    int getDataCount();

    Model getData(int position);

    List<Model> getDataList();

    void setDataList(@NonNull List<Model> dataList);

    void setSuperAdapter(@NonNull SuperAdapter superAdapter);

    void setNewDataList(@NonNull List<Model> newDataList);

    void removeData(int position);

    void removeDataRange(int position, int dataCount);

    void insertData(@NonNull Model data, int position);

    void insertDataRange(@NonNull List<Model> insertDataList, int position);

    void addAll(@NonNull List<Model> addedDataList, int position);

    void addAll(@NonNull List<Model> addedDataList);

    void moveData(int fromPosition, int toPosition);

    void notifyItemChanged(int position, @Nullable Object payload);

    void notifyItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload);
}
