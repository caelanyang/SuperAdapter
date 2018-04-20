package com.caelan.superadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public class DefaultDataSource<Model> implements DataSource<Model> {

    private List<Model> mDataList;

    private Intercept mIntercept = null;

    private SuperAdapter superAdapter;

    public DefaultDataSource(@NonNull ArrayList<Model> dataList) {
        this(dataList, null);
    }

    public DefaultDataSource(@NonNull ArrayList<Model> dataList, Intercept intercept) {
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
    public Model getData(int position) {
        return mDataList.get(position);
    }

    @Override
    public List<Model> getDataList() {
        return mDataList;
    }

    @Override
    public void setDataList(@NonNull List<Model> dataList) {
        diffData(dataList);
    }

    public void setSuperAdapter(@NonNull SuperAdapter fastAdapter) {
        this.superAdapter = fastAdapter;
    }

    public void diffData(@NonNull List<Model> newDataList) {
        DefaultDiffCallback diffCallback = new DefaultDiffCallback<>(mDataList, newDataList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback, true);
        mDataList = newDataList;
        diffResult.dispatchUpdatesTo(superAdapter);
    }

    @Override
    public void setNewDataList(@NonNull List<Model> newDataList) {
        diffData(newDataList);
    }

    @Override
    public void removeData(int position) {
        mDataList.remove(position);
        superAdapter.notifyItemRemoved(position);
    }

    @Override
    public void removeDataRange(int position, int dataCount) {
        ArrayList<Model> newDataList = new ArrayList<>(mDataList);
        int endIndex = Math.min(position + dataCount, mDataList.size());
        newDataList.subList(position, endIndex).clear();
        diffData(newDataList);
    }

    @Override
    public void insertData(@NonNull Model data, int position) {
        mDataList.add(position, data);
        superAdapter.notifyItemInserted(position);
    }

    @Override
    public void insertDataRange(@NonNull List<Model> insertDataList, int position) {
        mDataList.addAll(position, insertDataList);
        superAdapter.notifyItemRangeInserted(position, insertDataList.size());
    }

    @Override
    public void addAll(@NonNull List<Model> addedDataList, int position) {
        mDataList.addAll(position, addedDataList);
        superAdapter.notifyItemRangeInserted(position, addedDataList.size());
    }

    @Override
    public void addAll(@NonNull List<Model> addedDataList) {
        int countBefore = mDataList.size();
        mDataList.addAll(addedDataList);
        superAdapter.notifyItemRangeInserted(countBefore, addedDataList.size());
    }

    @Override
    public void moveData(int fromPosition, int toPosition) {
        Model targetData = mDataList.get(fromPosition);
        mDataList.remove(fromPosition);
        mDataList.add(toPosition, targetData);
        superAdapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void notifyItemChanged(int position, @Nullable Object payload) {
        superAdapter.notifyItemChanged(position, payload);
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
        superAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
    }

    public SuperAdapter getSuperAdapter() {
        return superAdapter;
    }

    public interface Intercept {
        int onIntercept(int position, Object data);
    }
}
