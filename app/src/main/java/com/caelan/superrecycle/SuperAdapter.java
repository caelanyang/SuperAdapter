package com.caelan.superrecycle;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.caelan.superrecycle.data.DataSource;

import java.util.ArrayList;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public abstract class SuperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private DataSource mDataSource;

    private ArrayList<?> items;

    private SparseArray<ItemAdapter> mItemAdapters = new SparseArray<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataSource == null ? 0 : mDataSource.getDataCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataSource != null) {
            return mDataSource.getDataType(position);
        } else {
            return -1;
        }
    }
}
