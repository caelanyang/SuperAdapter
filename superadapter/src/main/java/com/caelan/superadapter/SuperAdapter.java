package com.caelan.superadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public class SuperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = SuperAdapter.class.getSimpleName();

    private Context mContext;

    private DataSource mDataSource;

    private SparseArray<ItemBinder> mItemAdapters = new SparseArray<>();

    public SuperAdapter(Context context) {
        mContext = context;
    }

    public void setDataSource(@NonNull DataSource dataSource) {
        mDataSource = dataSource;
        mDataSource.setSuperAdapter(this);
    }

    public SuperAdapter with(int viewType, ItemBinder itemBinder) {
        itemBinder.setSuperAdapter(this);
        mItemAdapters.put(viewType, itemBinder);
        return this;
    }

    public SuperAdapter with(ItemBinder... itemBinders) {
        for (int i = 0; i < itemBinders.length; i++) {
            itemBinders[i].setSuperAdapter(this);
            mItemAdapters.put(i, itemBinders[i]);
        }
        return this;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinder itemBinder = mItemAdapters.get(viewType);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (itemBinder != null) {
            return itemBinder.onCreateViewHolder(parent, inflater);
        } else {
            throw new IllegalArgumentException("before use SuperAdapter ,should add at least one ItemBinder by call the method witt()");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemBinder itemBinder = mItemAdapters.get(holder.getItemViewType());
        if (itemBinder != null) {
            itemBinder.onBindViewHolder((SuperViewHolder) holder, mDataSource.getData(position));
        } else {
            Log.d(TAG, "can not find the itemBinder needed when call onBindViewHolder()");
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

    public DataSource getDataSource() {
        return mDataSource;
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        ItemBinder itemBinder = mItemAdapters.get(holder.getItemViewType());
        if (itemBinder != null) {
            itemBinder.onViewRecycled((SuperViewHolder) holder);
        } else {
            Log.d(TAG, "can not find the itemBinder needed when call onBindViewHolder()");
        }
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        ItemBinder itemBinder = mItemAdapters.get(holder.getItemViewType());
        if (itemBinder != null) {
            return itemBinder.onFailedToRecycleView((SuperViewHolder) holder);
        } else {
            Log.d(TAG, "can not find the itemBinder needed when call onFailedToRecycleView()");
            return super.onFailedToRecycleView(holder);
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        ItemBinder itemBinder = mItemAdapters.get(holder.getItemViewType());
        if (itemBinder != null) {
            itemBinder.onViewAttachedToWindow((SuperViewHolder) holder);
        } else {
            Log.d(TAG, "can not find the itemBinder needed when call onViewAttachedToWindow()");
            super.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        ItemBinder itemBinder = mItemAdapters.get(holder.getItemViewType());
        if (itemBinder != null) {
            itemBinder.onViewDetachedFromWindow((SuperViewHolder) holder);
        } else {
            Log.d(TAG, "can not find the itemBinder needed when call onViewDetachedFromWindow()");
            super.onViewDetachedFromWindow(holder);
        }
    }
}
