package com.caelan.superadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/11.
 * 此处的泛型 Model 与 DataSource 的泛型一致,用来限定DataSource的Type
 */
public class SuperAdapter<Model> extends RecyclerView.Adapter<SuperViewHolder> {

    private static final String TAG = SuperAdapter.class.getSimpleName();

    private Context mContext;

    private DataSource<Model> mDataSource;

    private SparseArray<ItemBinder> mItemAdapters = new SparseArray<>();

    public SuperAdapter(Context context) {
        mContext = context;
    }

    public void setDataSource(@NonNull DataSource<Model> dataSource) {
        mDataSource = dataSource;
        mDataSource.setSuperAdapter(this);
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
    public SuperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull SuperViewHolder holder, int position) {
        ItemBinder itemBinder = mItemAdapters.get(holder.getItemViewType());
        if (itemBinder != null) {
            itemBinder.onBindViewHolder(holder, mDataSource.getData(position));
        } else {
            Log.d(TAG, "can not find the itemBinder needed when call onBindViewHolder()");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull SuperViewHolder holder, int position, @NonNull List<Object> payloads) {
        ItemBinder itemBinder = mItemAdapters.get(holder.getItemViewType());
        if (itemBinder != null) {
            itemBinder.onBindViewHolder(holder, mDataSource.getData(position), payloads);
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

    public DataSource<Model> getDataSource() {
        return mDataSource;
    }

    @Override
    public void onViewRecycled(@NonNull SuperViewHolder holder) {
        ItemBinder itemBinder = mItemAdapters.get(holder.getItemViewType());
        if (itemBinder != null) {
            itemBinder.onViewRecycled(holder);
        } else {
            Log.d(TAG, "can not find the itemBinder needed when call onBindViewHolder()");
            super.onViewRecycled(holder);
        }
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull SuperViewHolder holder) {
        ItemBinder itemBinder = mItemAdapters.get(holder.getItemViewType());
        if (itemBinder != null) {
            return itemBinder.onFailedToRecycleView(holder);
        } else {
            Log.d(TAG, "can not find the itemBinder needed when call onFailedToRecycleView()");
            return super.onFailedToRecycleView(holder);
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull SuperViewHolder holder) {
        ItemBinder itemBinder = mItemAdapters.get(holder.getItemViewType());
        if (itemBinder != null) {
            itemBinder.onViewAttachedToWindow(holder);
        } else {
            Log.d(TAG, "can not find the itemBinder needed when call onViewAttachedToWindow()");
            super.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull SuperViewHolder holder) {
        ItemBinder itemBinder = mItemAdapters.get(holder.getItemViewType());
        if (itemBinder != null) {
            itemBinder.onViewDetachedFromWindow(holder);
        } else {
            Log.d(TAG, "can not find the itemBinder needed when call onViewDetachedFromWindow()");
            super.onViewDetachedFromWindow(holder);
        }
    }
}
