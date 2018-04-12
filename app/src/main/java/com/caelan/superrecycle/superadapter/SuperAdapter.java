package com.caelan.superrecycle.superadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public class SuperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = SuperAdapter.class.getSimpleName();

    private Context mContext;

    public SuperAdapter(Context context) {
        mContext = context;
    }

    public void setDataSource(@NonNull DataSource dataSource) {
        mDataSource = dataSource;
        mDataSource.setSuperAdapter(this);
    }

    private DataSource mDataSource;

    private SparseArray<ItemAdapter> mItemAdapters = new SparseArray<>();

    public SuperAdapter with(int viewType, ItemAdapter itemAdapter) {
        itemAdapter.setSuperAdapter(this);
        mItemAdapters.put(viewType, itemAdapter);
        return this;
    }

    public SuperAdapter with(ItemAdapter... itemAdapters) {
        for (int i = 0; i < itemAdapters.length; i++) {
            itemAdapters[i].setSuperAdapter(this);
            mItemAdapters.put(i, itemAdapters[i]);
        }
        return this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAdapter itemAdapter = mItemAdapters.get(viewType);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (itemAdapter != null) {
            return itemAdapter.onCreateViewHolder(parent, inflater);
        } else {
            throw new IllegalArgumentException("before use SuperAdapter ,should add at least one ItemAdapter by call the method witt()");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemAdapter itemAdapter = mItemAdapters.get(getItemViewType(position));
        if (itemAdapter != null) {
            itemAdapter.onBindViewHolder((SuperViewHolder) holder, mDataSource.getData(position));
        } else {
            Log.d(TAG, "can not find the itemAdapter needed when call onBindViewHolder()");
        }
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
            return 0;
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        ItemAdapter itemAdapter = mItemAdapters.get(getItemViewType(holder.getAdapterPosition()));
        if (itemAdapter != null) {
            itemAdapter.onViewRecycled((SuperViewHolder) holder);
        } else {
            Log.d(TAG, "can not find the itemAdapter needed when call onBindViewHolder()");
        }
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        ItemAdapter itemAdapter = mItemAdapters.get(getItemViewType(holder.getAdapterPosition()));
        if (itemAdapter != null) {
            return itemAdapter.onFailedToRecycleView((SuperViewHolder) holder);
        } else {
            Log.d(TAG, "can not find the itemAdapter needed when call onFailedToRecycleView()");
            return super.onFailedToRecycleView(holder);
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        ItemAdapter itemAdapter = mItemAdapters.get(getItemViewType(holder.getAdapterPosition()));
        if (itemAdapter != null) {
            itemAdapter.onViewAttachedToWindow((SuperViewHolder) holder);
        } else {
            Log.d(TAG, "can not find the itemAdapter needed when call onViewAttachedToWindow()");
            super.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        ItemAdapter itemAdapter = mItemAdapters.get(getItemViewType(holder.getAdapterPosition()));
        if (itemAdapter != null) {
            itemAdapter.onViewDetachedFromWindow((SuperViewHolder) holder);
        } else {
            Log.d(TAG, "can not find the itemAdapter needed when call onViewDetachedFromWindow()");
            super.onViewDetachedFromWindow(holder);
        }
    }
}
