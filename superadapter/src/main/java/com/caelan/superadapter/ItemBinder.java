package com.caelan.superadapter;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public abstract class ItemBinder<Model> implements ItemClickListener<Model> {

    private SuperAdapter mSuperAdapter;

    @LayoutRes
    private int layoutId;

    public ItemBinder(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    /**
     * 为了减少OnBindViewHolder()的执行时间，避免初始化页面是可能出现的卡顿，建议重写此方法，并调用
     * {@link SuperViewHolder#holderChildViewByIds(int...)} 方法提前把需要用到的子View 保存在SuperViewHolder中
     * 一些比较复杂的View的初始化比如RecycleView，放在这里比较好
     */
    public SuperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull LayoutInflater inflater) {
        View view = inflater.inflate(layoutId, parent, false);
        return new SuperViewHolder(view);
    }

    /**
     * 为整个Item或者其子View注册监听事件，暂时默认注册click以及longClick
     * 重写 {@link #onClick(View, int, Object)},{@link #onLongClick(View, int, Object)}处理点击事件
     * 事件注册放在{@link #onCreateViewHolder(ViewGroup, LayoutInflater)}比较好
     *
     * @param viewHolder        SuperViewHolder
     * @param isEnableItemClick holder.ItemView 的click开关,不影响子View的点击事件注册
     * @param ids               需要注册click的子View的id
     */
    protected void registerClickListener(SuperViewHolder viewHolder, boolean isEnableItemClick, @IdRes int... ids) {
        ArrayList<View> views = new ArrayList<>();
        if (isEnableItemClick) {
            views.add(viewHolder.itemView);
        }
        for (int id : ids) {
            views.add(viewHolder.get(id));
        }
        if (views.size() == 0) {
            return;
        }
        View[] viewArray = new View[views.size()];
        initClickListener(viewHolder, views.toArray(viewArray));
    }

    @SuppressWarnings("unchecked")
    private void initClickListener(final SuperViewHolder viewHolder, View... views) {
        final ItemBinder that = this;
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                that.onClick(v, position, mSuperAdapter.getDataSource().getData(position));
            }
        };
        View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = viewHolder.getAdapterPosition();
                that.onLongClick(v, position, mSuperAdapter.getDataSource().getData(position));
                return true;
            }
        };
        for (View view : views) {
            if (view != null) {
                view.setOnClickListener(clickListener);
                view.setOnLongClickListener(longClickListener);
            }
        }
    }

    @Override
    public void onClick(View v, int position, Model model) {

    }

    @Override
    public void onLongClick(View v, int position, Model model) {

    }

    public abstract void onBindViewHolder(@NonNull SuperViewHolder holder, Model model);

    public void onBindViewHolder(@NonNull SuperViewHolder holder, Model model, @NonNull List<Object> payloads) {
        onBindViewHolder(holder, model);
    }

    public void onViewRecycled(@NonNull SuperViewHolder holder) {

    }

    public boolean onFailedToRecycleView(@NonNull SuperViewHolder holder) {
        return false;
    }

    public void onViewAttachedToWindow(@NonNull SuperViewHolder holder) {

    }

    public void onViewDetachedFromWindow(@NonNull SuperViewHolder holder) {

    }

    protected SuperAdapter getSuperAdapter() {
        return mSuperAdapter;
    }

    public void setSuperAdapter(@NonNull SuperAdapter superAdapter) {
        mSuperAdapter = superAdapter;
    }

}
