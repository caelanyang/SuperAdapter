package com.caelan.superadapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public class DefaultDataSource implements DataSource {

    private List<Object> mDataList;

    private Intercept mIntercept = null;

    private SuperAdapter superAdapter;

    public DefaultDataSource(@NonNull ArrayList<Object> dataList) {
        this.mDataList = dataList;
    }

    public DefaultDataSource(@NonNull ArrayList<Object> dataList, Intercept intercept) {
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
    public void setDataList(@NonNull List<Object> dataList) {
        mDataList = dataList;
    }

    public void setSuperAdapter(@NonNull SuperAdapter fastAdapter) {
        this.superAdapter = fastAdapter;
    }

    @Override
    public void setNewDataList(@NonNull List<Object> newDataList) {

    }

    @Override
    public void removeData(int position) {
        mDataList.remove(position);
        superAdapter.notifyItemRemoved(position);
    }

    @Override
    public void removeDataRange(int position, int dataCount) {

    }

    @Override
    public void insertData(@NonNull Object data, int position) {
        mDataList.add(position, data);
        superAdapter.notifyItemInserted(position);
    }

    @Override
    public void insertDataRange(@NonNull List<Object> insertDataList, int position) {
        mDataList.addAll(position, insertDataList);
        superAdapter.notifyItemRangeInserted(position, insertDataList.size());
    }

    @Override
    public void addAll(@NonNull List<Object> addedDataList, int position) {
        mDataList.addAll(position, addedDataList);
        superAdapter.notifyItemRangeInserted(position, addedDataList.size());
    }

    @Override
    public void addAll(@NonNull List<Object> addedDataList) {
        int countBefore = mDataList.size();
        mDataList.addAll(addedDataList);
        superAdapter.notifyItemRangeInserted(countBefore, addedDataList.size());
    }

    @Override
    public void moveData(int fromPosition, int toPosition) {

    }

    public SuperAdapter getSuperAdapter() {
        return superAdapter;
    }

    private void checkItemChangePosition(int position) {
        if (position == 0) {

        }
    }

    public interface Intercept {
        int onIntercept(int position, Object data);
    }

/* ArrayList<Object> dataArrayList = new ArrayList<>(mDataList);
        dataArrayList.add(position, data);
        CommonDiffCallback diffCallback = new CommonDiffCallback(mDataList, dataArrayList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback, true);
        mDataList = dataArrayList;
        diffResult.dispatchUpdatesTo(superAdapter);*/
}
