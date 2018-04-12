package com.caelan.superrecycle.superadapter;

import android.support.annotation.NonNull;

import com.caelan.superrecycle.superadapter.DefaultDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public class SimpleDataSource extends DefaultDataSource {

    private List<?> mDataList;

    private Intercept mIntercept = null;

    public SimpleDataSource(@NonNull ArrayList<?> dataList) {
        this.mDataList = dataList;
    }

    public SimpleDataSource(@NonNull ArrayList<?> dataList, Intercept intercept) {
        this.mDataList = dataList;
        this.mIntercept = intercept;
    }

    @Override
    public int getDataType(int position) {
        if (mIntercept == null) {
            return 0;
        }
        return mIntercept.onIntercept(position, mDataList.get(position));
    }

    @Override
    public int getDataCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public Object getData(int position) {
        return mDataList.get(position);
    }

    @Override
    public void setDataList(@NonNull List<?> dataList) {
        mDataList = dataList;
    }


    public interface Intercept {
        int onIntercept(int position, Object data);
    }

}
